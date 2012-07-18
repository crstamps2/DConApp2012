package com.dconapp.provider.dao;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.PreparedStatement;
import com.google.cloud.sql.jdbc.ResultSet;

/**
 * DAOs can follow the Template Method Design Pattern
 * 
 * @author mrkaiser
 *
 */
public abstract class CloudSQLDAO {
	
	protected ResultSet executeQuery(){
		Connection c = buildDriverAndRetrieveConnection();
		PreparedStatement statement = generatePreparedStatementUsingConnection(c);
		ResultSet returnableResults = executePreparedStatementForQuery(statement);
		return returnableResults;
	}
	
	protected Integer executeUpdate(){
		Connection c = buildDriverAndRetrieveConnection();
		PreparedStatement statement = generatePreparedStatementUsingConnection(c);
		Integer returnableExecutionCode = executePreparedStatementForUpdate(statement);
		return returnableExecutionCode;
		
	}
	
	
	
	abstract protected Integer  executePreparedStatementForUpdate(PreparedStatement preparedStatement);
	
	abstract protected ResultSet  executePreparedStatementForQuery(PreparedStatement preparedStatement);
	
	abstract protected PreparedStatement generatePreparedStatementUsingConnection(Connection c);
	
	abstract protected Connection buildDriverAndRetrieveConnection();

}
