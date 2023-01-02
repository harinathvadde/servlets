package de.zeroco.dao;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import de.zeroco.db.DbUtility;


public class RegistrationDao {
	
	public final static String WORKBENCH_URL = "jdbc:mysql://localhost:3306/";
	public final static String CLASS_URL = "com.mysql.cj.jdbc.Driver";
	public final static String SCHEMA = "zerocode";
	public final static String USER = "admin";
	public final static String PASSWORD = "787472@Hari";
	public final static String TABLE_NAME = "user_details";
	public final static String REF_COL = "pk_id";
	public final static List<String> TABLE_COL = Arrays.asList("name","email","phno","dob","age");

	public static int getInsertedId(String name, String email, String phno, Date dob, int age) {
		Connection conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
		int id = DbUtility.getGenerateKey(conn, SCHEMA, TABLE_NAME, TABLE_COL, Arrays.asList(name,email,phno,dob,age));
		DbUtility.closeDbConnection(conn);
		return id;
	}
	public static String updateData(int age, Date date, String userId,String...input) {
		String op = "";
		for (String values : input) {
			op += "," + values;
		}
		Connection conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
		String res = DbUtility.updateTableData(conn, SCHEMA, TABLE_NAME, TABLE_COL, Arrays.asList(op.substring(1),date,age,userId), REF_COL);
		DbUtility.closeDbConnection(conn);
		return res;
	}
	public static List<Map<String, Object>> get(String userId){
		Connection conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
		List<Map<String, Object>> res = DbUtility.get(conn, SCHEMA, TABLE_NAME, TABLE_COL, REF_COL, userId);
		DbUtility.closeDbConnection(conn);
		return res;
	}
	public static String deleteData(String userId) {
		Connection conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
		String res = DbUtility.deleteData(conn, SCHEMA, TABLE_NAME, REF_COL, userId);
		DbUtility.closeDbConnection(conn);
		return res;
	}
}
