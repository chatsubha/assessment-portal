package com.portal.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.jsp.SkipPageException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.portal.service.LookupTableService;

/**
 * @author Rajkumar Kathiresan
 *
 */
@Configurable
public class LookupTableDescTag extends RequestContextAwareTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4411373497034672711L;

	private static String DB_DRIVER;
	private static String DB_CONNECTION;
	private static String DB_USER;
	private static String DB_PASSWORD;

	/** Table Name */
	private String tableName;

	/** Column Name */
	private String columnName;

	/** Lookup table key value */
	private String whereCondition;

	/** Lookup Table Service */
	@Autowired
	LookupTableService lookUpTableService;

	/**
	 * @param lookUpTableService
	 *            the lookUpTableService to set
	 */
	public void setLookUpTableService(LookupTableService lookUpTableService) {
		this.lookUpTableService = lookUpTableService;
	}

	public LookupTableDescTag() {
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @param columnName
	 *            the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @param whereCondition
	 *            the whereCondition to set
	 */
	public void setWhereCondition(String whereCondition) {
		this.whereCondition = whereCondition;
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		int result = 0;
		try {
			// String desc = lookUpTableService.getLookupTableDesc(tableName, columnName,
			// whereCondition);
			this.pageContext.getOut().write(getLookupTableDesc(tableName, columnName, whereCondition));
			result = EVAL_BODY_INCLUDE;
		} catch (Exception e) {
			e.printStackTrace();
			// stop page from loading further by throwing SkipPageException
			throw new SkipPageException("Exception in Loading Lookup Table Values from  " + tableName + " and Column "
					+ columnName + " with condition " + whereCondition);
		}

		return result;
	}

	private String getLookupTableDesc(String tableName, String columnName, String whereCondition) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT " + columnName + " FROM " + tableName + " WHERE " + whereCondition;
		String desc = "";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);

			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				desc = rs.getString(columnName);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return desc;

	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

	static {

		Properties prop = new Properties();
		InputStream input = null;
		String fileName = "application.properties";
		try {
			input = LookupTableDescTag.class.getClassLoader().getResourceAsStream(fileName);

			if (input == null) {
				throw new Exception("Sorry, unable to find " + fileName);
			}

			// load a properties file from class path, inside static method
			prop.load(input);

			DB_CONNECTION = prop.getProperty("spring.datasource.url");
			DB_USER = prop.getProperty("spring.datasource.username");
			DB_PASSWORD = prop.getProperty("spring.datasource.password");
			DB_DRIVER = prop.getProperty("spring.datasource.driver-class-name");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
