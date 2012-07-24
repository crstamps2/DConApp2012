package com.dconapp.provider.dao.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.dconapp.model.SearchSuggestions;
import com.dconapp.model.Track;
import com.dconapp.model.Tracks;


public class SearchSuggestionsDAOWrapper implements DAOWrapper<SearchSuggestions> {
	

	
	public SearchSuggestions wrapResultSet(ResultSet set) throws SQLException{
		//let's iterate through the list
		List<String> listOfWords  = new LinkedList<String>();
		while(set.next()){
			//build Track
			String word = "";
			//get the fields
			word = set.getString("word");
			listOfWords.add(word);
		}
		//after all of this let's return an array of the Track objects and set it on the returnabl Tracks
		SearchSuggestions returnable = new SearchSuggestions();
		//modify by reference
		returnable.words = listOfWords.toArray(new String[0]);
		return returnable;
	}
}
