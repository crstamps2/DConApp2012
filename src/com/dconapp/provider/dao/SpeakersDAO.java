package com.dconapp.provider.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dconapp.model.SpeakersResponse;
import com.google.appengine.api.rdbms.AppEngineDriver;

/**
 * 
 * @author mrkaiser
 *
 */
public class SpeakersDAO extends CloudSQLDAO<SpeakersResponse> {

	private final String STATEMENT_FOR_SPEAKERS = "select user_id,speaker_id,first_name,last_name," +
			"display_name,plusone_url,thumbnail_url,bio from speakers;";
	
	protected Integer executePreparedStatementForUpdate(
			PreparedStatement preparedStatement) {
		return 0;
	}

	
	protected ResultSet executePreparedStatementForQuery(
			PreparedStatement preparedStatement) throws SQLException {
		ResultSet returnableSet = preparedStatement.executeQuery();
		return returnableSet;
	}

	@Override
	protected PreparedStatement generatePreparedStatementUsingConnection(
			Connection c) throws SQLException {
		PreparedStatement statement = c.prepareStatement(STATEMENT_FOR_SPEAKERS);
		return statement;
	}

	@Override
	protected Connection buildDriverAndRetrieveConnection() throws SQLException {
		//build and register driver
		DriverManager.registerDriver(new AppEngineDriver());
		Connection conn = DriverManager.getConnection("jdbc:google:rdbms://dconapp:pr1/dcondb");
		return conn;
	}
	
}
