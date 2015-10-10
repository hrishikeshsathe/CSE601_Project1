package com.dm.servlets;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dm.dao.DBConnection;
import com.dm.utility.StringUtility;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection connection;
	static final Logger logger = Logger.getLogger(MainServlet.class.getName());
	static final ConsoleHandler consoleHandler = new ConsoleHandler();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		logger.addHandler(consoleHandler);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		connection = new DBConnection();
		String queryNumber = request.getParameter("queryNumber");
		logger.info("Query: " + queryNumber);
		switch (queryNumber) {
			case "1": {
				String diseaseName = request.getParameter(StringUtility.DISEASE_NAME);
				String diseaseType = request.getParameter(StringUtility.DISEASE_TYPE);
				String diseaseDescription = request.getParameter(StringUtility.DISEASE_DESCRIPTION);
				logger.info("Disease Name:" + diseaseName);
				logger.info("Disease Type:" + diseaseType);
				logger.info("Disease Description : " + diseaseDescription);
				connection.handleQueryOne(diseaseName, diseaseType, diseaseDescription);
				break;
			}
			case "2": {
				String diseaseDescription = request.getParameter(StringUtility.DISEASE_DESCRIPTION);
				logger.info("Disease Description : " + diseaseDescription);
				connection.handleQueryTwo(diseaseDescription);
				break;
			}
			case "3": {
				String diseaseName = request.getParameter(StringUtility.DISEASE_NAME);
				String measureUnitID = request.getParameter(StringUtility.MEASURE_UNIT_ID);
				String clusterID = request.getParameter(StringUtility.CLUSTER_ID);
				logger.info("Disease Name:" + diseaseName);
				logger.info("Measure Unit ID: " + measureUnitID);
				logger.info("Cluster ID: " + clusterID);
				connection.handleQueryThree(diseaseName, measureUnitID, clusterID);
				break;
			}
			case "4": {
				String diseaseName = request.getParameter(StringUtility.SELECTED_DISEASE);
				Integer goID = Integer.valueOf(request.getParameter(StringUtility.GO_ID));
				connection.handleQueryFour(diseaseName, goID);
				break;
			}
			case "5": {
				String[] diseaseNames = request.getParameterValues(StringUtility.DISEASE_NAME);
				Integer goID = Integer.valueOf(request.getParameter(StringUtility.GO_ID));
				connection.handleQueryFive(diseaseNames, goID);
				break;
			}
			case "6":
				String diseaseOne = request.getParameter(StringUtility.DISEASE_ONE);
				String diseaseTwo = request.getParameter(StringUtility.DISEASE_TWO);
				String goID = request.getParameter(StringUtility.GO_ID);
				logger.info(diseaseOne);
				logger.info(diseaseTwo);
				logger.info(goID);
				connection.handleQuerySix(diseaseOne, diseaseTwo, goID);
				break;
			case "7": {
				String diseaseName = request.getParameter(StringUtility.DISEASE_NAME);
				logger.info("Disease Name:" + diseaseName);
				connection.getInformativeGenes(diseaseName);
				break;
			}
			case "8": {
				String diseaseName = request.getParameter(StringUtility.DISEASE_NAME);
				logger.info("Disease Name:" + diseaseName);
					connection.classifyPatients(diseaseName);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
