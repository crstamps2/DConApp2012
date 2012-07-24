package com.dconapp.provider.dao.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.dconapp.model.Speaker;
import com.dconapp.model.SpeakersResponse;


public class SpeakersDAOWrapper implements DAOWrapper<SpeakersResponse> {
	

	
	public SpeakersResponse wrapResultSet(ResultSet set) throws SQLException{
		//let's iterate through the list
		List<Speaker> listOfTracks  = new LinkedList<Speaker>();
		while(set.next()){
			//build Track
			Speaker speakerToAdd = new Speaker();
			//get the fields
			speakerToAdd.user_id = set.getString("user_id");
			speakerToAdd.first_name = set.getString("first_name");
			speakerToAdd.last_name = set.getString("last_name");
			speakerToAdd.speaker_id = set.getString("speaker_id");
			speakerToAdd.plusone_url= set.getString("plusone_url");
			speakerToAdd.thumbnail_url = set.getString("thumbnail_url");
			speakerToAdd.display_name = set.getString("display_name");
			speakerToAdd.bio = set.getString("bio");
			//add the speaker
			listOfTracks.add(speakerToAdd);
		}
		//after all of this let's return an array of the Track objects and set it on the returnable Tracks
		SpeakersResponse returnable = new SpeakersResponse();
		//modify by reference
		returnable.speakers = listOfTracks.toArray(new Speaker[0]);
		return returnable;
	}
}
