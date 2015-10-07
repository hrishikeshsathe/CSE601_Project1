package com.dm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;

public class DBConnection {
	static final String USER = "hsathe";
	static final String PASSWORD = "cse601";
	static final String URL = "jdbc:oracle:thin:@dbod-scan.acsu.buffalo.edu:1521/CSE601_2159.buffalo.edu";
	static Connection connection;
	static final Logger logger = Logger.getLogger(DBConnection.class.getName());
    static final ConsoleHandler consoleHandler = new ConsoleHandler();

    /**
     * Creates the connection to Oracle SQL Developer
     */
	public DBConnection(){
		logger.addHandler(consoleHandler);
		OracleDataSource ods = null;
		try {
			ods = new OracleDataSource();
			ods.setUser(USER);
			ods.setPassword(PASSWORD);
			ods.setURL(URL);
			connection = ods.getConnection();
		}
		catch (SQLException e) {
			logger.warning(e.getMessage());
		}
	}

	/**
	 * Handle query one
	 * @param diseaseName
	 * @param diseaseType
	 * @param diseaseDescription
	 */
	public void handleQueryOne(String diseaseName, String diseaseType, String diseaseDescription) {
		long startTime = System.currentTimeMillis();
		String sql = "select d.name,count(dm.p_id) from diagnosis dm "
				+ "inner join disease d on (dm.ds_id = d.ds_id) where d.name='" + diseaseName + "' group by d.name "
				+ "Union "
				+ "select d.type,count(dm.p_id) from diagnosis dm "
				+ "inner join disease d on (dm.ds_id = d.ds_id) where d.type='" + diseaseType + "' group by d.type "
				+ "Union "
				+ "select d.description ,count(dm.p_id) from diagnosis dm "
				+ "inner join disease d on (dm.ds_id = d.ds_id) where d.description = '" + diseaseDescription + "' group by d.description";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			System.out.println("NAME\t|\tCOUNT(P_ID)");
			while(rs.next()) {
				System.out.println(rs.getString(1) + "\t|\t" + rs.getInt(2));
			}
			System.out.println("Time taken: " + (System.currentTimeMillis() - startTime));
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
	}

	/**
	 * Handle query two
	 * @param diseaseDescription
	 */
	public void handleQueryTwo(String diseaseDescription) {
		long startTime = System.currentTimeMillis();
		String sql = "select distinct type from drug where DR_ID in "
				+ "(select dr.dr_id from drug_use dr where dr.P_ID in (select distinct m.p_id from diagnosis m, "
				+ "disease d where m.ds_ID = d.DS_ID and d.DESCRIPTION='tumor'))";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			System.out.println("TYPE");
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
			System.out.println("Time taken: " + (System.currentTimeMillis() - startTime));
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
	}

	/**
	 * Handle query three
	 * @param diseaseName
	 * @param measureUnitID
	 * @param clusterID
	 */
	public void handleQueryThree(String diseaseName, String measureUnitID, String clusterID) {
		long startTime = System.currentTimeMillis();
		String sql = "select exp from microarray_fact where s_id in "
				+ "(select s_id from clinical_fact where p_id in "
				+ "(select distinct p_id from diagnosis where ds_id in "
				+ "(select ds_id from disease where name = '" + diseaseName + "')) and s_id is not null) and "
				+ "pb_id in (select pb_id from probe where U_ID in "
				+ "(select u_id from gene_cluster where cl_id = '" + clusterID + "')) and mu_id = '" + measureUnitID + "'";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			System.out.println("EXP");
			int count = 0;
			while(rs.next()) {
				count++;
				System.out.println(rs.getString(1));
			}
			System.out.println("Rows retrieved: " + count);
			System.out.println("Time taken: " + (System.currentTimeMillis() - startTime));
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
	}
}

