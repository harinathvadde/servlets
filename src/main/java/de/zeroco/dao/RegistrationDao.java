package de.zeroco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import de.zeroco.assignment.Utility;
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
	public final static List<String> TABLE_COL = Arrays.asList("name", "email", "phno", "dob", "age", "password");

	/**
	 * This method is used to insert data into db
	 * @author HARINATH
	 * @since 04/01/2023
	 * @param name
	 * @param email
	 * @param phone
	 * @param dob
	 * @param age
	 * @param password
	 * @return id
	 */
	public static int getInsertedId(String name, String email, String phone, Date dob, int age, String password) {
		Connection conn = null;
		int id = 0;
		try {
			conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
			id = DbUtility.getGenerateKey(conn, SCHEMA, TABLE_NAME, TABLE_COL,
					Arrays.asList(name, email, phone, dob, age, password));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtility.closeDbConnection(conn);
		}
		return id;
	}

	/**
	 * This method is used to check email and phno already exist or not in database
	 * @author HARINATH
	 * @since 04/01/2023
	 * @param email
	 * @param phno
	 * @return boolean
	 */
	public static boolean checkEmailPhno(String email, String phno) {
		Connection conn = null;
		try {
			conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
			if (Utility.hasData(DbUtility.list(conn, SCHEMA, TABLE_NAME, Arrays.asList(), "email", email))
					|| Utility.hasData(DbUtility.list(conn, SCHEMA, TABLE_NAME, Arrays.asList(), "phno", phno))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtility.closeDbConnection(conn);
		}
		return false;
	}

	/**
	 * This method is used to get db data by passing email as input parameter
	 * @author HARINATH
	 * @since 04/01/2023
	 * @param email
	 * @return List<Map<String, Object>>
	 */
	public static List<Map<String, Object>> get(String email) {
		Connection conn = null;
		List<Map<String, Object>> res = null;
		try {
			conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
			res = DbUtility.list(conn, SCHEMA, TABLE_NAME, TABLE_COL, REF_COL, email);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtility.closeDbConnection(conn);
		}
		return res;
	}
	/**
	 * This method is used to get all db data 
	 * @author HARINATH
	 * @since 04/01/2023
	 * @return List<Map<String, Object>>
	 */
	public static List<Map<String, Object>> getAll() {
		Connection conn = null;
		List<Map<String, Object>> output = null;
		try {
			conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
			output = DbUtility.get(conn, SCHEMA, TABLE_NAME, TABLE_COL,
					QueryBuilder.getColsDataQuery(SCHEMA, TABLE_NAME, Arrays.asList()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtility.closeDbConnection(conn);
		}
		return output;
	}
	/**
	 * This method is used to delete data from db by passing email as input parameter
	 * @author HARINATH
	 * @since 04/01/2023
	 * @param email
	 * @return String
	 */
	public static String deleteData(String email) {
		Connection conn = null;
		String res = null;
		try {
			conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
			res = DbUtility.deleteData(conn, SCHEMA, TABLE_NAME, REF_COL, email);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtility.closeDbConnection(conn);
		}
		return res;
	}
	/**
	 * This method is used to validate login details
	 * @author HARINATH
	 * @since 04/01/2023
	 * @param email
	 * @param password
	 * @return boolean
	 */
	public static boolean logIn(String email, String password) {
		Connection conn = null;
		boolean output = false;
		PreparedStatement ps;
		try {
			conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
			ps = conn.prepareStatement("select * from " + TABLE_NAME + " where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, DbUtility.encryptPassword(password));
			ResultSet rs = ps.executeQuery();
			output = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtility.closeDbConnection(conn);
		}
		return output;
	}
	/**
	 * This method is used to update db data
	 * @author HARINATH
	 * @since 04/01/2023
	 * @param col
	 * @param data
	 * @return String
	 */
	public static String updateData(List<String> col, List<Object> data) {
		Connection conn = null;
		String res = null;
		try {
			conn = DbUtility.getDbConnect(USER, PASSWORD, SCHEMA);
			res = DbUtility.updateTableData(conn, SCHEMA, TABLE_NAME, col, data, "email");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtility.closeDbConnection(conn);
		}
		return res;
	}
	public static void main(String[] args) {
		System.out.println(checkEmailPhno(null, "9652754858"));
	}
}
