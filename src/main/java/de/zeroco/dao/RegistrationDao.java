package de.zeroco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import de.zeroco.db.DbUtility;
import de.zeroco.db.QueryBuilder;


public class RegistrationDao {
	
	public final static String WORKBENCH_URL = "jdbc:mysql://localhost:3306/";
	public final static String CLASS_URL = "com.mysql.cj.jdbc.Driver";
	public final static String SCHEMA = "zerocode";
	public final static String USER = "admin";
	public final static String PASSWORD = "787472@Hari";
	public final static String TABLE_NAME = "user_details";
	public final static String REF_COL = "email";
	public final static List<String> TABLE_COL = Arrays.asList("name","email","phno","dob","age","password");

	public static int getInsertedId(String name, String email, String phno, Date dob, int age,String password) {
		Connection conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
		int id = DbUtility.getGenerateKey(conn, SCHEMA, TABLE_NAME, TABLE_COL, Arrays.asList(name,email,phno,dob,age,password));
		DbUtility.closeDbConnection(conn);
		return id;
	}
	
	public static List<Map<String, Object>> get(String email){
		Connection conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
		List<Map<String, Object>> res = DbUtility.get(conn, SCHEMA, TABLE_NAME, TABLE_COL, REF_COL, email);
		DbUtility.closeDbConnection(conn);
		return res;
	}
	public static String deleteData(String email) {
		Connection conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
		String res = DbUtility.deleteData(conn, SCHEMA, TABLE_NAME, REF_COL, email);
		DbUtility.closeDbConnection(conn);
		return res;
	}
	public static boolean logIn(String email, String pass) {
		Connection conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
		boolean output =false;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("select * from "+ TABLE_NAME +" where email=? and password=?");
			ps.setString(1, email);
	        ps.setString(2, pass);
	        ResultSet rs =ps.executeQuery();
	        output = rs.next();
	        DbUtility.closeDbConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return output;
	}
	public static List<Map<String, Object>> getAll(){
		Connection conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
		List<Map<String, Object>> output = DbUtility.get(conn, SCHEMA, TABLE_NAME, TABLE_COL, QueryBuilder.getClmnsDataQuery(SCHEMA, TABLE_NAME, Arrays.asList()));
		return output;
	}
	public static String updateData(List<String> col, List<Object> data) {
		Connection conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
		System.out.println(data);
		String res = DbUtility.updateTableData(conn, SCHEMA, TABLE_NAME, col, data, "pk_id");
		DbUtility.closeDbConnection(conn);
		return res;
	}
}
