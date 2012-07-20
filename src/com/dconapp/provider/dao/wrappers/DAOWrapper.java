/**
 * 
 */
package com.dconapp.provider.dao.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author mrkaiser
 *
 */
public interface  DAOWrapper<T> {
	
	abstract public T wrapResultSet(ResultSet set) throws SQLException;

}
