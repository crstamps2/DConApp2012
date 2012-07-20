package com.dconapp.provider.dao.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dconapp.model.Day;
import com.dconapp.model.EventSlots;
import com.dconapp.model.TimeSlot;


public class CommonSlotsDAOWrapper implements DAOWrapper<EventSlots> {
	

	
	public EventSlots wrapResultSet(ResultSet set) throws SQLException{
		//let's iterate through the list
		//build a hashmap
		Map<String,List<TimeSlot>> map = new HashMap<String, List<TimeSlot>>();
		while(set.next()){
			//build TimeSlot
			TimeSlot timeSlotToAdd = new TimeSlot();
			//get the fields!
			String date = set.getString("date");
			//time slot fields
			timeSlotToAdd.start = set.getString("start_time");
			timeSlotToAdd.end = set.getString("end_time");
			timeSlotToAdd.title = set.getString("title");
			timeSlotToAdd.type = set.getString("type");
			timeSlotToAdd.meta = set.getString("meta");
			//check and add
			//this can clearly be optimized but I'm not going to do it. It's 9 and i haven't even tested yet
			if(map.get(date) == null){
				//add the key and new List
				map.put(date, new ArrayList<TimeSlot>());
				map.get(date).add(timeSlotToAdd);
			}
			else{
				map.get(date).add(timeSlotToAdd);
			}
		}
		//modify the map into an EventSlots object
		EventSlots slots = modifyMapToList(map);
		return slots;
		
	}
	
	private EventSlots modifyMapToList(Map<String,List<TimeSlot>> modifyMap){
		//grab the key set
		Set<String> keys = modifyMap.keySet();
		Day[] days = new Day[keys.size()];
		int count = 0;
		for(String key : keys){
			Day day = new Day();
			day.date = key;
			day.slot = modifyMap.get(key).toArray(new TimeSlot[0]);
			days[count] = day;
			count++;
		}
		EventSlots returnableEventSlots = new EventSlots(days);
		return returnableEventSlots;
	}

}
