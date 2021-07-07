package org.acme.getting.started;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.getting.started.controller.SQL;
import org.acme.getting.started.entity.MapEntity;
import org.acme.getting.started.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jboss.resteasy.reactive.RestPath;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;


@Path("/v2")
public class GreetingResource {
	
	SQL sql = new SQL();
	
	@GET
    @Path("/post/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPost() throws IOException, SQLException {
    	String response = sql.getAllPost();
        return response;
    }
    
    @GET
    @Path("/tag/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllTag() throws IOException, SQLException {
    	String response = sql.getAllTag();
        return response;
    }
	
    @GET
    @Path("/post/{param}/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPost(@RestPath String param, @RestPath String value) throws IOException, SQLException {
    	String response = sql.getPostBy(param, value);
        return response;
    }
    
    @GET
    @Path("/tag/{param}/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTag(@RestPath String param, @RestPath String value) throws IOException, SQLException {
    	String response = sql.getTagBy(param, value);
        return response;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add/post/title/{title}/content/{content}/tags/{tags}")
    public String addPost(@RestPath String title, @RestPath String content, @RestPath String tags ) throws SQLException, IOException {
        // return something
    	String response = sql.insertDataPost(title, content, tags);
    	return response;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add/tag/label/{label}/posts/{posts}")
    public String addTag(@RestPath String label, @RestPath String posts) throws SQLException, IOException {
        // return something
    	String response = sql.insertDataTag(label, posts);
    	return response;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update/post/id/{id}/title/{title}/content/{content}/tags/{tags}")
    public String updatePost(@RestPath String id, @RestPath String title, @RestPath String content, @RestPath String tags ) throws SQLException, IOException {
        // return something
    	String response = sql.updatePostById(id, title, content, tags);
    	return response;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update/tag/id/{id}/label/{label}/posts/{posts}")
    public String updateTag(@RestPath String id, @RestPath String label, @RestPath String posts) throws SQLException, IOException {
        // return something
    	String response = sql.updateTagById(id, label, posts);
    	return response;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/post/id/{id}")
    public String deletePost(@RestPath String id) throws SQLException, IOException {
        // return something
    	String response = sql.delPostById(id);
    	return response;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/tag/id/{id}")
    public String deleteTag(@RestPath String id) throws SQLException, IOException {
        // return something
    	String response = sql.delTagById(id);
    	return response;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hbn/add/tag/label/{label}/posts/{posts}")
    public String hbnSaveTag(@RestPath String label, @RestPath String posts) throws SQLException, IOException {
    	Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
       
        String status = "false";
        JsonObject json = new JsonObject();
        try 
        {
        	MapEntity map = new MapEntity();
            map.setLabel(label);
            map.setPosts(posts);
             
            session.save(map);
             
            session.getTransaction().commit();
            status = "true";
            json.put("status", status);
            json.put("label", label);
            
        } 
        catch (Exception e) 
        {
            
            e.printStackTrace();
            status = "true";
            json.put("status", status);
            json.put("label", label);
        }finally {
	        session.close(); 
	    }

        
    	return json.toString();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hbn/add/post/title/{title}/content/{content}/tags/{tags}")
    public String hbnSavePost(@RestPath String title, @RestPath String content, @RestPath String tags) throws SQLException, IOException {
    	Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
       
        String status = "false";
        JsonObject json = new JsonObject();
        try 
        {
        	MapEntity map = new MapEntity();
            map.setContent(content);
            map.setTitle(title);
            map.setTags(tags);
             
            session.save(map);
             
            session.getTransaction().commit();
            status = "true";
            json.put("status", status);
            json.put("title", title);
            
        } 
        catch (Exception e) 
        {
            
            e.printStackTrace();
            status = "true";
            json.put("status", status);
            json.put("title", title);
        }finally {
	        session.close(); 
	    }
       

        
    	return json.toString();
    }
    
    @GET
    @Path("/hbn/post/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String hbnGetAllPost() throws IOException, SQLException {
    	Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
       
        String status = "false";
        JsonObject json = new JsonObject();
	    JsonArray js = new JsonArray();
        try 
        {
        	List<Object[]> res = session.createNativeQuery("select * from Posts").getResultList();
        	
        	Iterator it = res.iterator();
        	while(it.hasNext()){
        	    Object[] line = (Object[]) it.next();
        	    JsonObject jsont = new JsonObject();
        	    String title = "";
        	    String content = "";
        	    String tags = "";
        	    String id = "";
        	    if(line[3]!=null) {
        	    	title = line[3].toString(); 
        	    }
        	    if(line[1]!=null) {
        	    	content = line[1].toString(); 
        	    }
        	    if(line[2]!=null) {
        	    	tags = line[2].toString(); 
        	    }
        	    if(line[0]!=null) {
        	    	id = line[0].toString(); 
        	    }
        	    jsont.put("id", id);
 	            jsont.put("title", title);
 	            jsont.put("content", content);
 	            jsont.put("tag", tags);
 	            js.add(jsont);
        	    
        	     
        	}
            session.getTransaction().commit();
            json.put("status", status);
  		  	json.put("data", js);
        } 
        catch (Exception e) 
        {
            
            e.printStackTrace();
            json.put("status", status);
            json.put("error", e.getMessage());
        }finally {
	        session.close(); 
	    }

        
    	return json.toString();
    }
    
    @GET
    @Path("/hbn/tag/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String hbnGetAllTag() throws IOException, SQLException {
    	Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
       
        String status = "false";
        JsonObject json = new JsonObject();
	    JsonArray js = new JsonArray();
        try 
        {
        	List<Object[]> res = session.createNativeQuery("select * from tags").getResultList();
        	
        	Iterator it = res.iterator();
        	while(it.hasNext()){
        	    Object[] line = (Object[]) it.next();
        	    JsonObject jsont = new JsonObject();
        	    String label = "";
        	    String posts = "";
        	    String id = "";
        	    if(line[0]!=null) {
        	    	label = line[0].toString(); 
        	    }
        	    if(line[1]!=null) {
        	    	posts = line[1].toString(); 
        	    }
        	    if(line[2]!=null) {
        	    	id = line[2].toString(); 
        	    }
        	    jsont.put("id", id);
        	    jsont.put("label", label);
	            jsont.put("post", posts);
 	            js.add(jsont);
        	     
        	}
        	
            session.getTransaction().commit();
            json.put("status", status);
  		  	json.put("data", js);
        } 
        catch (Exception e) 
        {
            
            e.printStackTrace();
            json.put("status", status);
            json.put("error", e.getMessage());
        }finally {
	        session.close(); 
	    }

        return json.toString();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hbn/delete/post/id/{id}")
    public String hbnDeletePost(@RestPath int id) throws SQLException, IOException {
    	Session session = HibernateUtils.getSessionFactory().openSession();
        
        JsonObject json = new JsonObject();
        String status = "false";
        try {
           session.beginTransaction();
           Query q = session.createNativeQuery("delete from posts where id = ?1");
           q.setParameter(1, id);
           int res = q.executeUpdate();
           if(res!=0) {
        	   status = "true"; 
        	   json.put("status", status);
               json.put("id", id);
           }else {
        	   json.put("status", status);
           }
           session.getTransaction().commit();
           
        } catch (Exception e) {
        	e.printStackTrace();
            json.put("status", status);
            json.put("error", e.getMessage());
        } finally {
           session.close(); 
        }
    	return json.toString();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hbn/delete/tag/id/{id}")
    public String hbnDeleteTag(@RestPath int id) throws SQLException, IOException {
        // return something
    	Session session = HibernateUtils.getSessionFactory().openSession();
        
        JsonObject json = new JsonObject();
        String status = "false";
        try {
           session.beginTransaction();
           Query q = session.createNativeQuery("delete from tags where id = ?1");
           q.setParameter(1, id);
           int res = q.executeUpdate();
           if(res!=0) {
        	   status = "true"; 
        	   json.put("status", status);
               json.put("id", id);
           }else {
        	   json.put("status", status);
           }
           session.getTransaction().commit();
        } catch (Exception e) {
        	e.printStackTrace();
            json.put("status", status);
            json.put("error", e.getMessage());
        } finally {
           session.close(); 
        }
    	return json.toString();
    }
    
    
}
