package com.dm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import org.apache.commons.math3.stat.inference.OneWayAnova;
import org.apache.commons.math3.stat.inference.TTest;

import com.dm.utility.StringUtility;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;


import oracle.jdbc.pool.OracleDataSource;

public class DBConnection {
	static final String USER = "hsathe";
	static final String PASSWORD = "cse601";
	static final String URL = 
			"jdbc:oracle:thin:@dbod-scan.acsu.buffalo.edu:1521/CSE601_2159.buffalo.edu";
	static Connection connection;
	static final Logger logger = Logger.getLogger(DBConnection.class.getName());
	static final ConsoleHandler consoleHandler = new ConsoleHandler();
	static ArrayList<String> informativeGenes = new ArrayList<>();
	static String informativeGenesString;

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
	 * Handle query 1
	 * @param diseaseName
	 * @param diseaseType
	 * @param diseaseDescription
	 */
	public void handleQueryOne(String diseaseName, String diseaseType, 
			String diseaseDescription) {
		long startTime = System.currentTimeMillis();

		try {
			PreparedStatement statement = connection.prepareStatement(StringUtility.QUERY_1);
			statement.setString(1, diseaseName);
			statement.setString(2, diseaseType);
			statement.setString(3, diseaseDescription);
			ResultSet rs = statement.executeQuery();
			System.out.println("NAME | COUNT(P_ID)");
			while(rs.next()) {
				System.out.println(rs.getString(1) + " | " + rs.getInt(2));
			}
			System.out.println("Time taken: " + (System.currentTimeMillis() - startTime));
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
	}

	/**
	 * Handle query 2
	 * @param diseaseDescription
	 */
	public void handleQueryTwo(String diseaseDescription) {
		long startTime = System.currentTimeMillis();

		try {
			PreparedStatement statement = connection.prepareStatement(StringUtility.QUERY_2);
			statement.setString(1, diseaseDescription);
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
	 * Handle query 3
	 * @param diseaseName
	 * @param measureUnitID
	 * @param clusterID
	 */
	public void handleQueryThree(String diseaseName, String measureUnitID, String clusterID) {
		long startTime = System.currentTimeMillis();

		try {
			PreparedStatement statement = connection.prepareStatement(StringUtility.QUERY_3);
			statement.setString(1, diseaseName);
			statement.setString(2, clusterID);
			statement.setString(3, measureUnitID);
			ResultSet rs = statement.executeQuery();
			rs.setFetchSize(3000);
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

	/**
	 * Handle query 4
	 * @param diseaseNames
	 * @param go_id
	 */
	public void handleQueryFour(String diseaseName,  Integer goID)
	{
		long startTime = System.currentTimeMillis();
		TTest tt = new TTest();
		double[] withDisease;
		double[] withoutDisease;

		PreparedStatement statement;
		try {

			statement = connection.prepareStatement(StringUtility.QUERY_4_A, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.setInt(1, goID);
			statement.setString(2, diseaseName);
			withDisease = populateResultSet(statement);

			statement = connection.prepareStatement(StringUtility.QUERY_4_B,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.setInt(1, goID);
			statement.setString(2, diseaseName);
			withoutDisease = populateResultSet(statement);

			double tstat = tt.homoscedasticT(withDisease, withoutDisease);
			System.out.println("Time taken: " + (System.currentTimeMillis() - startTime));
			System.out.println("T-Stat Value : " + tstat);
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}

	}


	/**
	 * Handle query 5
	 * @param diseaseNames
	 * @param go_id
	 */
	public void handleQueryFive(String[] diseaseNames, Integer goID) {
		long startTime = System.currentTimeMillis();
		OneWayAnova owa = new OneWayAnova();
		Collection<double[]> list = new ArrayList<double[]>();
		for(int i = 0; i < diseaseNames.length; i++) {
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement(StringUtility.QUERY_5,
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				statement.setInt(1, goID);
				statement.setString(2, diseaseNames[i]);
				ResultSet rs = statement.executeQuery();
				rs.setFetchSize(3000);
				rs.last();
				double temp[] = new double[rs.getRow()];
				rs.beforeFirst();
				int index = 0;
				while(rs.next()) {
					temp[index] = Double.valueOf(rs.getString(1));
					index++;
				}
				list.add(temp);
			} catch (SQLException e) {
				logger.warning(e.getMessage());
			}
		}
		double fValue = owa.anovaFValue(list);
		System.out.println("Time taken: " + (System.currentTimeMillis() - startTime));
		System.out.println("F-statistic value: " + fValue);
	}

	/**
	 * Get the informative genes for a disease
	 * @param diseaseName
	 */
	public void getInformativeGenes(String diseaseName) {
		HashMap<String, double[]> withDiseaseMap = new HashMap<String, double[]>();
		HashMap<String, double[]> withoutDiseaseMap = new HashMap<String, double[]>();
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement(StringUtility.PART_3_A_1);
			statement.setString(1, diseaseName);
			withDiseaseMap = populateResultHashMap(statement);

			statement = connection.prepareStatement(StringUtility.PART_3_A_2);
			statement.setString(1, diseaseName);
			withoutDiseaseMap = populateResultHashMap(statement);

		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}

		TTest tt = new TTest();
		System.out.println("UID\t\tT-Statistic");
		for(String key: withDiseaseMap.keySet()){
			double tStat = tt.homoscedasticT(withDiseaseMap.get(key), withoutDiseaseMap.get(key));
			System.out.println(key + "\t|\t" + tStat);
			double pValue = tt.homoscedasticTTest(withDiseaseMap.get(key), withoutDiseaseMap.get(key));
			if(pValue < 0.01) {
				informativeGenes.add(key);
			}
		}
		populateAndPrintInformativeGenes();
		classifyPatients(); 
	}

	/**
	 * Classify patients
	 */
	public void classifyPatients() {
		LinkedHashMap<String, double[]> oldPatientsWithDiseaseMap = new LinkedHashMap<>();
		LinkedHashMap<String, double[]> oldPatientsWithoutDiseaseMap = new LinkedHashMap<>();
		LinkedHashMap<String, double[]> newPatients = new LinkedHashMap<>();
		ArrayList<double[]> correlationWithDisease = new ArrayList<>();
		ArrayList<double[]> correlationWithoutDisease = new ArrayList<>();
		String query1 = "select cs.p_id,g.u_id,mf.exp from microarray_fact mf "
				+ "inner join "
				+ "probe p on mf.pb_id = p.pb_id "
				+ "inner join "
				+ "gene g on p.u_id = g.u_id "
				+ "inner join "
				+ "(select * from clinical_sample where p_id in "
				+ "(select distinct p_id from diagnosis where ds_id in "
				+ "(select ds_id from disease where name = 'ALL')) "
				+ "and s_id is not null) cs on mf.s_id=cs.s_id  where g.u_id in " + informativeGenesString + " order by cs.p_id, g.u_id";
		String query2 = "select cs.p_id,g.u_id,mf.exp from microarray_fact mf "
				+ "inner join "
				+ "probe p on mf.pb_id = p.pb_id "
				+ "inner join "
				+ "gene g on p.u_id = g.u_id "
				+ "inner join "
				+ "(select * from clinical_sample where p_id in "
				+ "(select distinct p_id from diagnosis where ds_id in "
				+ "(select ds_id from disease where name != 'ALL')) "
				+ "and s_id is not null) cs on mf.s_id=cs.s_id  where g.u_id in " + informativeGenesString + " order by cs.p_id, g.u_id";
		String query3 = "select * from test_samples where u_id in " + informativeGenesString + " order by u_id";
		oldPatientsWithDiseaseMap = populateResultSet(query1);
		oldPatientsWithoutDiseaseMap = populateResultSet(query2);
		newPatients = populateResultSetForNewPatients(query3);

		correlationWithDisease = calculateCorrelation(newPatients, oldPatientsWithDiseaseMap);
		correlationWithoutDisease = calculateCorrelation(newPatients, oldPatientsWithoutDiseaseMap);

		TTest test = new TTest();
		for(int i = 0; i < correlationWithDisease.size(); i++) {
			System.out.println(test.homoscedasticTTest(correlationWithDisease.get(i), 
					correlationWithoutDisease.get(i)));
		}

	}

	/**
	 * 
	 * @param rs
	 * @return
	 */
	private LinkedHashMap<String, double[]> populateResultSetForNewPatients(String query) {
		LinkedHashMap<String, double[]> toReturn = new LinkedHashMap<>();
		ResultSet rs;

		try {
			PreparedStatement statement = connection.prepareStatement(query, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//			statement.setString(1, informativeGenesString);
			rs = statement.executeQuery();
			rs.last();
			int rows = rs.getRow();
			rs.beforeFirst();
			for(int i = 1; i <= 5; i++) {
				toReturn.put("Patient " + i, new double[rows]);
			}
			int j = 0;
			while(rs.next() && j < rows) {
				for(int i = 1; i <= 5; i++) {
					double[] temp = toReturn.get("Patient " + i);
					temp[j] = rs.getDouble(i + 1);
				}
				j++;
			}

		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}

		return toReturn;
	}

	/**
	 * Populate the resultSet as a HashMap
	 * @param rs
	 * @return
	 */
	private LinkedHashMap<String, double[]> populateResultSet(String query) {

		LinkedHashMap<String, ArrayList<Double>> tempList = new LinkedHashMap<String, ArrayList<Double>>();
		LinkedHashMap<String, double[]> toReturn = new LinkedHashMap<>();
		ResultSet rs;
		try {
			PreparedStatement statement = connection.prepareStatement(query, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery();
			rs.setFetchSize(1000);
			while(rs.next()) {
				if(tempList.containsKey(rs.getString(1))) {
					tempList.get(rs.getString(1)).add((rs.getDouble(3)));
				} else {
					ArrayList<Double> list = new ArrayList<>();
					list.add((rs.getDouble(3)));
					tempList.put(rs.getString(1), list);
				}
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		for(String key: tempList.keySet()) {
			double[] tempArr = new double[tempList.get(key).size()];
			int i = 0;
			for(Double value: tempList.get(key)) {
				tempArr[i] = value;
				i++;
			}
			toReturn.put(key, tempArr);
		}
		return toReturn;
	}

	/**
	 * Calculate the correlation
	 * @param newPatients
	 * @param tempMap
	 * @return
	 */
	private ArrayList<double[]> calculateCorrelation(HashMap<String, double[]> newPatients, HashMap<String, double[]> tempMap) {
		PearsonsCorrelation pc = new PearsonsCorrelation();
		ArrayList<double[]> toReturn = new ArrayList<>();
		for(String newPatient: newPatients.keySet()) {
			int i = 0;
			double[] temp = new double[tempMap.size()];
			for (String oldPatient: tempMap.keySet()) {
				temp[i] = pc.correlation(tempMap.get(oldPatient),
						newPatients.get(newPatient));
				i++;
			}
			toReturn.add(temp);
		}
		return toReturn;
	}

	/**
	 * Populate and return a double array of values
	 * @param statement
	 * @return
	 */
	private double[] populateResultSet(PreparedStatement statement) {
		ResultSet rs;
		double temp[] = null;
		try {
			rs = statement.executeQuery();
			rs.setFetchSize(1000);
			rs.last();
			temp = new double[rs.getRow()];
			rs.beforeFirst();
			int index = 0;
			while(rs.next()) {
				temp[index] = Double.valueOf(rs.getString(1));
				index++;
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return temp;
	}

	/**
	 * 
	 * @param statement
	 * @return
	 */
	private HashMap<String, double[]> populateResultHashMap(PreparedStatement statement) {
		HashMap<String, double[]> toReturn = new HashMap<>();
		HashMap<String, ArrayList<Double>> temp = new HashMap<>();
		ResultSet rs;
		try {
			rs = statement.executeQuery();
			rs.setFetchSize(5000);
			while(rs.next()) {
				if(temp.containsKey(rs.getString(1))) {
					temp.get(rs.getString(1)).add((rs.getDouble(2)));
				} else {
					ArrayList<Double> tempList = new ArrayList<>();
					tempList.add((rs.getDouble(2)));
					temp.put(rs.getString(1), tempList);
				}
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		for(String key: temp.keySet()) {
			double[] tempArr = new double[temp.get(key).size()];
			int i = 0;
			for(Double value: temp.get(key)) {
				tempArr[i] = value;
				i++;
			}
			toReturn.put(key, tempArr);
		}
		return toReturn;
	}
	
	/**
	 * Populate and print the informative genes
	 */
	private void populateAndPrintInformativeGenes() {
		System.out.println("\nInformative Genes: " + informativeGenes.size());
		informativeGenesString = "(";
		for(int i = 0; i < informativeGenes.size() - 1; i ++) {
			System.out.println(informativeGenes.get(i));
			informativeGenesString += "'" + informativeGenes.get(i)+ "',";
		}
		System.out.println(informativeGenes.get(informativeGenes.size() - 1));
		informativeGenesString += "'" + informativeGenes.get(informativeGenes.size() - 1) + "')";
	}
}


