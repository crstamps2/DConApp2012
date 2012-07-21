/**
 * 
 */
package com.dconapp.provider.services;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dconapp.model.EventSlots;
import com.dconapp.model.Rooms;
import com.dconapp.model.Tracks;
import com.dconapp.provider.dao.CommonSlotsDAO;
import com.dconapp.provider.dao.RoomsDAO;
import com.dconapp.provider.dao.TracksDAO;
import com.dconapp.provider.dao.wrappers.CommonSlotsDAOWrapper;
import com.dconapp.provider.dao.wrappers.RoomsDAOWrapper;
import com.dconapp.provider.dao.wrappers.TracksDAOWrapper;
import com.google.gson.Gson;

/**
 * @author mrkaiser
 *
 */


@Path("/PrivateServices")
public class PrivateAndroidServices{
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/commonslots")
	public String buildCommonSlots() throws SQLException{
			String returnable = "";
			//build caching stuff... maybe? 
			//TODO Build a caching mechanism to speed up retreivals 
			CommonSlotsDAO dao = new CommonSlotsDAO();
			EventSlots events = dao.executeQuery(new CommonSlotsDAOWrapper());
			//build GSON
			Gson gson = new Gson();
			//build JSON
			returnable = gson.toJson(events);
			return returnable;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/tracks")
	public String buildTracks() throws SQLException{
		TracksDAO dao  = new TracksDAO();
		Tracks tracks = dao.executeQuery(new TracksDAOWrapper());
		Gson gson = new Gson();
		String returnable = gson.toJson(tracks);
		return returnable;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/rooms")
	public String buildRooms() throws SQLException{
		RoomsDAO dao  = new RoomsDAO();
		Rooms rooms = dao.executeQuery(new RoomsDAOWrapper());
		Gson gson = new Gson();
		String returnable = gson.toJson(rooms);
		return returnable;
	}
}
