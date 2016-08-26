package com.karen.learning.KariJavaWS.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.FindByIndexOptions;
import com.karen.learning.KariJavaWS.database.CloudantDBSingleton;
import com.karen.learning.KariJavaWS.model.Animal;
import com.karen.learning.KariJavaWS.model.Books;
import com.karen.learning.KariJavaWS.model.Persona;
import com.karen.learning.KariJavaWS.service.PersonaService;



@Path("/personas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonaResource {
	
	PersonaService ps = new PersonaService();
	
	@GET  
	@Path("/echo/{message}")  
	@Produces("text/plain")  
	public String showMsg(@PathParam("message") String message){  
	    //return message;     
	    CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
	    return dbSingleton.getDatabase().info().toString();
	}
	@GET 
    @Path("/records")  
    @Produces(MediaType.APPLICATION_JSON)  
    public List<Persona> getAll(){
		return ps.getAll();
   	
    } 
	
	@GET 
	@Path("/getRecords")
    @Produces(MediaType.APPLICATION_JSON)  
    public List<Books> getRecords2(){
   	 /*MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
   	 DB db = dbSingleton.getTestdb();*/
   	 CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
   	 Database db = dbSingleton.getDatabase();
   	 //DBCollection coll = db.getCollection("Books");	
   	 //DBCursor cursor = coll.find().sort(new BasicDBObject("by", 1));
   	 //List<Animal> list = db.findByIndex("\"selector\": {\"_id\": { \"$gt\": 0 }}", Animal.class);
   	 //List<Animal> list = db.findByIndex("\"selector\": { \"_id\": { \"$gt\": 0} }",Animal.class, new FindByIndexOptions().fields("diet").fields("class").fields("_id"));
   	 List<Books> list = db.findByIndex("\"selector\": { \"_id\": { \"$gt\": 0} }",Books.class);
	 
   	 System.out.println("El tamaño de la lista es: "+list.size());
	 return list;
   			 //db.findByIndex("\"selector\": {}",Persona.class, new FindByIndexOptions().fields("diet"));
   	 
   			 //.includeDocs(true)
   			// .query(Books.class); 
   	 //while (cursor.hasNext()) { 
           /* DBObject o = cursor.next();
            Books bools = new Books();
            bools.setTitle((String) o.get("title"));
            bools.setDescription((String) o.get("description"));
            bools.setYear((String) o.get("year"));
            bools.setBy((String) o.get("by"));
            bools.setLikes((Long) o.get("likes"));
            list.add(bools);
         }*/
   	 
   	 //return list;
    } 
	
	@GET
	public List<Persona> getMessages() {
		return ps.getAll();
	}
	
	@GET
	@Path("/{personaId}")
	public Response getPersona(@PathParam("personaId") String id) {
		Persona pers = ps.getById(id);
		if(pers == null) {
			return Response.status(Status.NOT_FOUND)
							.build();
		}
		return Response.status(Status.FOUND)
						.entity(pers)
						.build();
	}
	
	@POST
	public void addPersona(Persona persona) {
		ps.addMessage(persona);
	}
	
	@PUT
	@Path("/{personaId}")
	public void getMessages(@PathParam("personaId") String id, Persona persona) {
		persona.setId(id);
		ps.update(persona);
	}
	
	@DELETE
	@Path("/{personaId}")
	public void deleteMessage(@PathParam("personaId") String id) {
		ps.remove(id);
	}
}
