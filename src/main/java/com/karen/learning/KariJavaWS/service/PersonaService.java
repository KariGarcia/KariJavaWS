package com.karen.learning.KariJavaWS.service;


import java.util.List;

import com.cloudant.client.api.Database;
import com.karen.learning.KariJavaWS.database.CloudantDBSingleton;
import com.karen.learning.KariJavaWS.model.Persona;


public class PersonaService {
	CloudantDBSingleton cloudant = CloudantDBSingleton.getInstance();
	Database db = cloudant.getDatabase();
	
	public List<Persona> getAll() {
		List<Persona> list = db.findByIndex("\"selector\": { \"_id\": { \"$gt\": 0} }",Persona.class);
		return list;
	}
	
	public Persona getById(String id) {
		Persona persona = db.  findByIndex("\"selector\": { \"_id\": "+id+" }",Persona.class).get(0);
		return persona;
	}
	
	public void addMessage(Persona persona) {
		
	}
	
	public void update(Persona persona) {
		
	}
	
	public void remove(String id) {
		
	}
}
