package org.acme.getting.started;

import java.io.IOException;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.getting.started.controller.SQL;
import org.jboss.resteasy.reactive.RestPath;


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
    
    
    
}
