package com.dconapp.provider.dao;

import java.sql.*;
import java.util.Collection;

import com.dconapp.provider.dao.wrappers.DAOWrapper;

/**
 * DAOs can follow the Template Method Design Pattern
 * 
 * @author mrkaiser
 * 
 */
public abstract class CloudSQLDAO<T> {
	
	
	
	public final T executeQuery(DAOWrapper<T> wrapper) throws SQLException {
		ResultSet returnableResults = null;
		Connection c = null;
		T returnable = null;
		try {
			c = buildDriverAndRetrieveConnection();
			PreparedStatement statement = generatePreparedStatementUsingConnection(c);
			returnableResults = executePreparedStatementForQuery(statement);
			returnable = wrapper.wrapResultSet(returnableResults);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return returnable;
	}

	protected Integer executeUpdate() throws SQLException {
		Connection c = buildDriverAndRetrieveConnection();
		PreparedStatement statement = generatePreparedStatementUsingConnection(c);
		Integer returnableExecutionCode = executePreparedStatementForUpdate(statement);
		return returnableExecutionCode;

	}

	abstract protected Integer executePreparedStatementForUpdate(
			PreparedStatement preparedStatement);

	abstract protected ResultSet executePreparedStatementForQuery(
			PreparedStatement preparedStatement) throws SQLException;

	abstract protected PreparedStatement generatePreparedStatementUsingConnection(
			Connection c) throws SQLException;

	abstract protected Connection buildDriverAndRetrieveConnection()
			throws SQLException;

}
