package de.zeroco.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.zeroco.assignment.Utility;

public class DbUtility {

	public final static String WORKBENCH_URL = "jdbc:mysql://localhost:3306/";
	public final static String CLASS_URL = "com.mysql.cj.jdbc.Driver";
	
	
	/***
	 * This method is used to connect to database
	 * @author HARINATH
	 * @since 20/12/2022
	 * @param user
	 * @param password
	 * @return Connection
	 */
	public static Connection getDbConnect(String user, String password, String schema) {
		if (Utility.isBlankWithVar(user,password,schema)) return null;
		Connection conn = null;
		try {
			Class.forName(CLASS_URL);
			conn = DriverManager.getConnection(WORKBENCH_URL+schema, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * This method is used to close the opened connection 
	 * @author HARINATH
	 * @since 22/12/2022
	 * @param conn
	 */
	public static void closeDbConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to insert data to table. 
	 * Taking inputs connection, schema, table name, list of columns to be insert, list of values to be inserted
	 * @author HARINATH
	 * @since 22/12/2022
	 * @param conn
	 * @param schema
	 * @param tableName
	 * @param columns
	 * @param values
	 * @return 
	 */
	public static int getGenerateKey(Connection conn,String schema, String tableName, List<String> columns, List<Object> values) {
		if (Utility.isBlankWithVar(schema,tableName,columns,values)) return 0;
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(QueryBuilder.getInsertQuery(schema, tableName, columns),new String[] { "ID" });
			int count = 1;
			for (Object value : values) {
				psmt.setObject(count ++, value);
			}
			if (psmt.executeUpdate() > 0) {
				ResultSet generatedKeys = psmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * This method used to update record in my sql table 
	 * @author HARINATH
	 * @since 23/12/2022
	 * @param conn
	 * @param schema
	 * @param tableName
	 * @param columns
	 * @param values
	 * @param refClmn
	 * @return
	 */
	public static String updateTableData(Connection conn,String schema, String tableName, List<String> columns, List<Object> values, String refClmn) {
		if (Utility.isBlankWithVar(schema,tableName,columns,values,refClmn)) return "";
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(QueryBuilder.getUpdateQuery(schema, tableName, columns, refClmn));
			int count = 1;
			for (Object value : values) {
				psmt.setObject(count ++, value);
			}
			psmt.executeUpdate();
			return refClmn + " updated successfully.";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * This method is used to get data from database table
	 * @author 	HARINATH
	 * @since 23/12/2022
	 * @param conn
	 * @param needData
	 * @param tableName
	 * @param refColumName
	 * @param refData
	 * @return list
	 */
	public static List<Object> getData(Connection conn, String schema, String tableName, List<String> columns, String query){
		if (Utility.isBlankWithVar(schema,tableName,columns,query)) return null;
		List<Object> list = new ArrayList<Object>();
		Statement smt = null;
		try {
			smt = conn.createStatement();
			ResultSet res = smt.executeQuery(QueryBuilder.getClmnsDataQuery(schema, tableName, columns));
			ResultSetMetaData rsmd = res.getMetaData();
			while (res.next()) {
				for (int i = 1; i < rsmd.getColumnCount()+1; i++) {
					list.add(res.getObject(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * This method is used to get particular data by pasing refcolumn and ref data
	 * @author HARINATH
	 * @since 23/12/2022
	 * @param conn
	 * @param schema
	 * @param tableName
	 * @param columns
	 * @param refColumn
	 * @param refData
	 * @return List
	 */
	public static List<Object> getData(Connection conn, String schema, String tableName, List<String> columns, String refColumn, String refData){
		if (Utility.isBlankWithVar(tableName)) return null;
		return getData(conn, schema, tableName, columns, QueryBuilder.getDataQuery(schema, tableName, columns, refColumn, refData));
	}
	/**
	 * This method is used to delete particular data with using reference details
	 * @author HARINATH
	 * @since 21/12/2022
	 * @param conn
	 * @param tableName
	 * @param refColumName
	 * @param refData
	 * @return String
	 */
	public static String deleteData(Connection conn,String Schema, String tableName, String refColumName, String refData ) {
		if (Utility.isBlankWithVar(tableName,refColumName,refData)) return null;
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(QueryBuilder.getDeleteQuery(Schema, tableName, refColumName, refData));
			psmt.executeUpdate();
			return refData + " data deleted successfully.";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * This method is used to get data from table in list of maps
	 * @author HARINATH
	 * @since 29/12/2022
	 * @param conn
	 * @param schema
	 * @param tableName
	 * @param columns
	 * @return
	 */
	public static List<Map<String, Object>> get(Connection conn, String schema, String tableName, List<String> columns,String query){
		List<Map<String, Object>> list =  new ArrayList<>();
		Statement smt = null;
		try {
			smt = conn.createStatement();
			ResultSet res = smt.executeQuery(query);
			ResultSetMetaData rsmd = res.getMetaData();
			int colCount = rsmd.getColumnCount();
			while (res.next()) {
				Map<String, Object> rows = new LinkedHashMap<>(colCount);
				for (int i = 1; i <= colCount; i++) {
					rows.put(rsmd.getColumnName(i), res.getObject(i));
				}
				list.add(rows);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	public static List<Map<String, Object>> list(Connection conn, String schema, String tableName, List<String> columns, String refClmn, String id){
		return get(conn, schema, tableName, columns, QueryBuilder.getDataQuery(schema, tableName, columns, refClmn, id));
	}
	public final static String TABLE_NAME = "user_details";
	public final static List<String> TABLE_COL = Arrays.asList("name","email","phno","dob","age");

	public static void main(String[] args) {
		String user = "admin";
		String password = "787472@Hari";
		String schema = "zerocode";
		//List<String> column = new ArrayList<String>(Arrays.asList("data_set","source_column","target_column"));
		Connection conn = getDbConnect(user, password, schema);
		System.out.println(deleteData(conn, schema, TABLE_NAME, "pk_id", "2"));
		closeDbConnection(conn);
	}
}
