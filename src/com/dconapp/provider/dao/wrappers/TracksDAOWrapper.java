package com.dconapp.provider.dao.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.dconapp.model.Track;
import com.dconapp.model.Tracks;


public class TracksDAOWrapper implements DAOWrapper<Tracks> {
	

	
	public Tracks wrapResultSet(ResultSet set) throws SQLException{
		//let's iterate through the list
		List<Track> listOfTracks  = new LinkedList<Track>();
		while(set.next()){
			//build Track
			Track trackToAdd = new Track();
			//get the fields
			trackToAdd.name = set.getString("name");
			trackToAdd.color = set.getString("color");
			trackToAdd._abstract = set.getString("abstract");
			listOfTracks.add(trackToAdd);
		}
		//after all of this let's return an array of the Track objects and set it on the returnabl Tracks
		Tracks returnable = new Tracks();
		//modify by reference
		returnable.track = listOfTracks.toArray(new Track[0]);
		return returnable;
	}
}
