package com.dconapp.provider.dao.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.dconapp.model.Room;
import com.dconapp.model.Rooms;
import com.dconapp.model.Track;
import com.dconapp.model.Tracks;


public class RoomsDAOWrapper implements DAOWrapper<Rooms> {
	

	
	public Rooms wrapResultSet(ResultSet set) throws SQLException{
		//let's iterate through the list
		List<Room> listOfRooms  = new LinkedList<Room>();
		while(set.next()){
			//build Track
			Room trackToAdd = new Room();
			//get the fields
			trackToAdd.id = set.getString("id");
			trackToAdd.name = set.getString("name");
			trackToAdd.floor = set.getString("floor");
			listOfRooms.add(trackToAdd);
		}
		//after all of this let's return an array of the Track objects and set it on the returnabl Tracks
		Rooms returnable = new Rooms();
		//modify by reference
		returnable.rooms = listOfRooms.toArray(new Room[0]);
		return returnable;
	}
}
