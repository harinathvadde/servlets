package de.zeroco.db;

import java.util.Arrays;
import java.util.List;

import de.zeroco.assignment.Utility;

public class QueryBuilder {

	public static final String GRAVE = "`";
	
	public static void main(String[] args) {
	
		List<String> columns = Arrays.asList();
		//System.out.println(getUpdateQuery("zerocode", "emp", columns, "pk_id"));
		//System.out.println(getDeleteQuery("zerocode", "emp", "empin", "ABC@103"));
		System.out.println(getClmnsDataQuery("zerocode", "emp", columns));
		//System.out.println(getDataQuery("zerocode", "emp", columns, "empid", "ABC@109"));
		//System.out.println(getCreateTableQuery("zerocode", "subjects", columns));
		//System.out.println(getInnerJoinQuery("schema", "emp", "location", columns, columns, "name", "name", "INNER"));
		//System.out.println(getCrossJoinQuery("zerocode", "emp", "students", columns, columns));
	}
	/**
	 * This method is used to generate uery for insert data in table
	 * @author HARINATH
	 * @since 22/12/2022
	 * @param schema
	 * @param tableName
	 * @param columns
	 * @return String Query
	 */
	public static String getInsertQuery(String schema, String tableName, List<String> columns) {
		String clmn = "";
		String placeHolders = "";
		for (String column : columns) {
			clmn += "," + GRAVE + column + GRAVE;
			placeHolders += ",?";
		}
		return "INSERT INTO " + GRAVE + schema + GRAVE + "." + GRAVE + tableName + GRAVE + " (" + clmn.substring(1)
		+ ") VALUES (" + placeHolders.substring(1) + ");";
	}
	/**
	 * This method is used to generate query for update data in table
	 * @author HARINATH
	 * @since 22/12/2022
	 * @param schema
	 * @param tableName
	 * @param columns
	 * @param refClm
	 * @return String Query
	 */
	public static String getUpdateQuery(String schema, String tableName, List<String> columns, String refClm) {
		String clmn = "";
		for (String column : columns) {
			clmn += "," + GRAVE + column + GRAVE + " = ?";
		}
		return "UPDATE " + GRAVE + schema + GRAVE + "." + GRAVE + tableName + GRAVE + " SET " + clmn.substring(1) + " WHERE " 
		+ "(" + GRAVE + refClm + GRAVE + " = ? );";  
	}
	/**
	 * This method is used to generate query for delete data in table
	 * @author 	HARINATH
	 * @since 23/12/2022
	 * @param schema
	 * @param tableName
	 * @param refClmn
	 * @param refData
	 * @return String Query
	 */
	public static String getDeleteQuery(String schema, String tableName, String refClmn, String refData) {
		return "DELETE FROM " + GRAVE + schema + GRAVE + "." + GRAVE + tableName + GRAVE 
				+ " WHERE (" + GRAVE + refClmn + GRAVE + " = '" + refData + "');";
	}
	/**
	 * This methos is used to generate query for get total column data in the table
	 * @author HARINATH
	 * @since 23/12/2022
	 * @param schema
	 * @param tableName
	 * @param columns
	 * @return String Query
	 */
	public static String getClmnsDataQuery(String schema, String tableName, List<?> columns) {
		String clmn = "";
		for (Object column : columns) {
			clmn += "," + GRAVE + column + GRAVE;
		}
		return "SELECT " + (Utility.isBlank(columns)?"*":clmn.substring(1)) + " FROM "+ GRAVE + schema + GRAVE + "." + GRAVE + tableName + GRAVE +";";
	}
	/**
	 * This method is used to get perticular data by passing referance and it will give all data by passing * 
	 * @author HARINATH
	 * @since 23/12/2022
	 * @param schema
	 * @param tableName
	 * @param columns
	 * @param refColumn
	 * @return String Query
	 */
	public static String getDataQuery(String schema, String tableName, List<String> columns, String refColumn, String refData) {
		String clmn ="";
		for (String column : columns) {
			clmn += "," + GRAVE + column + GRAVE;
		}
		return "SELECT " + (Utility.isBlank(columns)?"*":clmn.substring(1)) + " FROM " + GRAVE + schema + GRAVE + "." + GRAVE + tableName + GRAVE 
				+ " WHERE (" + GRAVE + refColumn + GRAVE + " = '"+ refData +"' );";
	}
	/**
	 * This method is used to get query to create table 
	 * @author HARINATH
	 * @since 26/12/2022
	 * @param schema
	 * @param tableName
	 * @param columns
	 * @return
	 */
	public static String getCreateTableQuery(String schema, String tableName, List<String> columns) {
		String clmn = "";
		for (String column : columns) {
			clmn += "," + column;
		}
		return "CREATE TABLE " + tableName + " (" + clmn.substring(1) + ");";
	}
	/**
	 * This method will used to get query for 
	 * @param schema
	 * @param firstTable
	 * @param secondTable
	 * @param columnsOfFirstTable
	 * @param columnsOfSecondTable
	 * @param refOnTableOne
	 * @param refOnTableTwo
	 * @return
	 */
	public static String getInnerJoinQuery(String schema, String firstTable, String secondTable,
			List<String> columnsOfFirstTable, List<String> columnsOfSecondTable, String refOnTableOne,
			String refOnTableTwo, String joinType) {
		String col = "";
		for (String column : columnsOfFirstTable)
			col += "," + GRAVE + firstTable + GRAVE + "." + GRAVE + column + GRAVE;
		for (String column : columnsOfSecondTable)
			col += "," + GRAVE + secondTable + GRAVE + "." + GRAVE + column + GRAVE;
		return "SELECT "
				+ (Utility.isBlankWithVar(columnsOfFirstTable, columnsOfSecondTable) ? " * ": col.substring(1))
				+ " FROM " + GRAVE + firstTable + GRAVE + " " + joinType + " JOIN " + GRAVE + secondTable + GRAVE + " ON " + GRAVE
				+ firstTable + GRAVE + "." + GRAVE + refOnTableOne + GRAVE + " = " + GRAVE + secondTable + GRAVE + "."
				+ GRAVE + refOnTableTwo + GRAVE + ";" ;
	}
	/**
	 * This method will used to get query for cross join 
	 * @author HARINATH
	 * @since 26/12/2022
	 * @param schema
	 * @param firstTable
	 * @param secTable
	 * @param columnsOfFirstTable
	 * @param columnsOfSecondTable
	 * @return String Query
	 */
	public static String getCrossJoinQuery(String schema, String firstTable, String secTable,List<String> columnsOfFirstTable, List<String> columnsOfSecondTable) {
		//SELECT * FROM MatchScore CROSS JOIN Departments;  
		String col = "";
		for (String column : columnsOfFirstTable)
			col += "," + GRAVE + firstTable + GRAVE + "." + GRAVE + column + GRAVE;
		for (String column : columnsOfSecondTable)
			col += "," + GRAVE + secTable + GRAVE + "." + GRAVE + column + GRAVE;
		return "SELECT " + (Utility.isBlankWithVar(columnsOfFirstTable,columnsOfSecondTable)? " * " : col.substring(1))
				+ " FROM " + firstTable + " CROSS JOIN " + secTable + ";" ;	
	}
}
