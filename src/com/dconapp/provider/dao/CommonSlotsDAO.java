package com.dconapp.provider.dao;

import java.sql.*;

import org.apache.commons.lang.NotImplementedException;

import com.dconapp.model.EventSlots;
import com.google.appengine.api.rdbms.AppEngineDriver;

/**
 * 
 * @author mrkaiser
 *
 */
public class CommonSlotsDAO extends CloudSQLDAO<EventSlots> {

	private final String STATEMENT_FOR_COMMON_SLOTS = "select t1.date, t2.start_time, t2.end_time,t2.title,t2.type,t2.meta " +
			"from common_slots as t1 INNER JOIN common_time_slots as t2 ON t1.time_slot_id = t2.time_slot_id;";
	
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
