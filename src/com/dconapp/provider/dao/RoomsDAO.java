package com.dconapp.provider.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dconapp.model.Rooms;
import com.google.appengine.api.rdbms.AppEngineDriver;

/**
 * 
 * @author mrkaiser
 *
 */
public class RoomsDAO extends CloudSQLDAO<Rooms> {

	private final String STATEMENT_FOR_COMMON_SLOTS = "select id,name,floor from rooms;";
	
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
		PreparedStatement statement = c.prepareStatement(STATEMENT_FOR_COMMON_SLOTS);
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
