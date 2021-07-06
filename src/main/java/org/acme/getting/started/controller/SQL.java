package org.acme.getting.started.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.acme.getting.started.utils.SqlUtils;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;



public class SQL {
	
	public String insertDataPost(String title, String content, String tags) throws SQLException, IOException {
		
		String status = "false";
		Connection con = SqlUtils.connect();
	    PreparedStatement stmt = null;
	    String query = null;
	    query = "INSERT INTO tb_post (title, content, tags) VALUES (?, ?, ?)";
	    JsonObject json = new JsonObject();
	    try {
	      stmt = con.prepareStatement(query);
	      stmt.setString(1, title);
	      stmt.setString(2, content);
	      stmt.setString(3, tags);
	      int row = stmt.executeUpdate();
	      if (row!=0) {
			status = "true";
	      }
	      json.put("title", title);
	      json.put("status", status);
	    } catch (SQLException e) {
//	      e.printStackTrace();
	      json.put("status", status);
	      json.put("error", e.getMessage());
	      
	    } finally {
	      if (con != null) {
	        stmt.close();
	        con.close();
	      } 
	    } 
		return json.toString();
	}
	
	public String insertDataTag(String label, String posts) throws SQLException, IOException {
		
		String status = "false";
		Connection con = SqlUtils.connect();
	    PreparedStatement stmt = null;
	    String query = null;
	    query = "INSERT INTO tb_tag (label, posts) VALUES (?, ?)";
	    JsonObject json = new JsonObject();
	    try {
	      stmt = con.prepareStatement(query);
	      stmt.setString(1, label);
	      stmt.setString(2, posts);
	      int row = stmt.executeUpdate();
	      if (row!=0) {
			status = "true";
	      }	
	      json.put("label", label);
	      json.put("status", status);
	    } catch (SQLException e) {
//	      e.printStackTrace();
	      json.put("status", status);
		  json.put("error", e.getMessage());
	    } finally {
	      if (con != null) {
	        stmt.close();
	        con.close();
	      } 
	    } 
		return json.toString();
	}
	
	public String getPostBy(String param, String value) throws SQLException, IOException {
		
		Connection con = SqlUtils.connect();
	    PreparedStatement stmt = null;
	    String query = null;
	    query = "SELECT * FROM tb_post where "+param+" = ?";
	    JsonObject json = new JsonObject();
	    JsonArray js = new JsonArray();
	    try {
	      stmt = con.prepareStatement(query);
	      if(param.equalsIgnoreCase("id")) {
	    	  stmt.setInt(1, Integer.parseInt(value));
	      }else {
	    	  stmt.setString(1, value);
	      }
	      
	      ResultSet rs = stmt.executeQuery();
		  while(rs.next()) {
			  JsonObject jsont = new JsonObject();
			  jsont.put("id", rs.getString("id"));
			  jsont.put("title", rs.getString("title"));
			  jsont.put("content", rs.getString("content"));
			  jsont.put("tags", rs.getString("tags"));
			  js.add(jsont);
	      }
		  json.put("status", "true");
		  json.put("data", js);
	    } catch (SQLException e) {
//	      e.printStackTrace();
	      json.put("status", "false");
	      json.put("error", e.getMessage());
	    } finally {
	      if (con != null) {
	        stmt.close();
	        con.close();
	      } 
	    } 
		return json.toString();
	}
	
	public String getTagBy(String param, String value) throws SQLException, IOException {
		
		Connection con = SqlUtils.connect();
	    PreparedStatement stmt = null;
	    String query = null;
	    query = "SELECT * FROM tb_tag where "+param+" = ?";
	    JsonObject json = new JsonObject();
	    JsonArray js = new JsonArray();
	    try {
	      stmt = con.prepareStatement(query);
	      if(param.equalsIgnoreCase("id")) {
	    	  stmt.setInt(1, Integer.parseInt(value));
	      }else {
	    	  stmt.setString(1, value);
	      }
	      ResultSet rs = stmt.executeQuery();
		  while(rs.next()) {
			  JsonObject jsont = new JsonObject();
			  jsont.put("id", rs.getString("id"));
			  jsont.put("label", rs.getString("label"));
			  jsont.put("posts", rs.getString("posts"));
			  js.add(jsont);
				
	      }
		  
		  json.put("status", "true");
		  json.put("data", js);
	    } catch (SQLException e) {
//	      e.printStackTrace();
	    	json.put("status", "false");
		    json.put("error", e.getMessage());
	    } finally {
	      if (con != null) {
	        stmt.close();
	        con.close();
	      } 
	    } 
		return json.toString();
	}
	
	public String getAllPost() throws SQLException, IOException {
		
		Connection con = SqlUtils.connect();
	    PreparedStatement stmt = null;
	    String query = null;
	    query = "SELECT * FROM tb_post";
	    JsonObject json = new JsonObject();
	    JsonArray js = new JsonArray();
	    try {
	      stmt = con.prepareStatement(query);
	      ResultSet rs = stmt.executeQuery();
		  while(rs.next()) {
			  JsonObject jsont = new JsonObject();
			  jsont.put("id", rs.getInt("id"));
			  jsont.put("title", rs.getString("title"));
			  jsont.put("content", rs.getString("content"));
			  jsont.put("tags", rs.getString("tags"));
			  js.add(jsont);
	      }
		  json.put("status", "true");
		  json.put("data", js);
	    } catch (SQLException e) {
//	      e.printStackTrace();
	      json.put("status", "false");
	      json.put("error", e.getMessage());
	    } finally {
	      if (con != null) {
	        stmt.close();
	        con.close();
	      } 
	    } 
		return json.toString();
	}
	
	public String getAllTag() throws SQLException, IOException {
		
		Connection con = SqlUtils.connect();
	    PreparedStatement stmt = null;
	    String query = null;
	    query = "SELECT * FROM tb_tag ";
	    JsonObject json = new JsonObject();
	    JsonArray js = new JsonArray();
	    try {
	      stmt = con.prepareStatement(query);
	      ResultSet rs = stmt.executeQuery();
		  while(rs.next()) {
			  JsonObject jsont = new JsonObject();
			  jsont.put("id", rs.getInt("id"));
			  jsont.put("label", rs.getString("label"));
			  jsont.put("posts", rs.getString("posts"));
			  js.add(jsont);
				
	      }
		  
		  json.put("status", "true");
		  json.put("data", js);
	    } catch (SQLException e) {
//	      e.printStackTrace();
	    	json.put("status", "false");
		    json.put("error", e.getMessage());
	    } finally {
	      if (con != null) {
	        stmt.close();
	        con.close();
	      } 
	    } 
		return json.toString();
	}
	
	public String updatePostById(String id, String title, String content, String tags) throws SQLException, IOException {
		
		String status = "false";
		Connection con = SqlUtils.connect();
	    PreparedStatement stmt = null;
	    String query = null;
	    query = "SELECT * FROM tb_post where id = ?";
	    JsonObject json = new JsonObject();
	    try {
	      stmt = con.prepareStatement(query);
	      stmt.setInt(1, Integer.parseInt(id));
	      ResultSet rs = stmt.executeQuery();
		  if(rs.next()) {
			  query = "UPDATE tb_post\n" + 
			  		"	SET title = ?, content = ?, tags = ?\n" + 
			  		"	WHERE id = ?;";
			  stmt = con.prepareStatement(query);
		      stmt.setString(1, title);
		      stmt.setString(2, content);
		      stmt.setString(3, tags);
		      stmt.setInt(4, Integer.parseInt(id));
		      int row = stmt.executeUpdate();
		      if (row!=0) {
		    	  status = "true";
		      }	
	      }
		  json.put("status", status);
		  json.put("title", title);
	    } catch (SQLException e) {
//	      e.printStackTrace();
	      json.put("status", "false");
	      json.put("error", e.getMessage());
	    } finally {
	      if (con != null) {
	        stmt.close();
	        con.close();
	      } 
	    } 
		return json.toString();
	}
	
	public String updateTagById(String id, String label, String posts) throws SQLException, IOException {
		
		String status = "false";
		Connection con = SqlUtils.connect();
	    PreparedStatement stmt = null;
	    String query = null;
	    query = "SELECT * FROM tb_tag where id = ?";
	    JsonObject json = new JsonObject();
	    try {
	      stmt = con.prepareStatement(query);
	      stmt.setInt(1, Integer.parseInt(id));
	      ResultSet rs = stmt.executeQuery();
		  if(rs.next()) {
			  query = "UPDATE tb_tag\n" + 
			  		"	SET label = ?, posts = ?" + 
			  		"	WHERE id = ?;";
			  stmt = con.prepareStatement(query);
		      stmt.setString(1, label);
		      stmt.setString(2, posts);
		      stmt.setInt(3, Integer.parseInt(id));
		      int row = stmt.executeUpdate();
		      if (row!=0) {
		    	  status = "true";
		      }	
	      }
		  json.put("status", status);
		  json.put("label", label);
	    } catch (SQLException e) {
//	      e.printStackTrace();
	      json.put("status", "false");
	      json.put("error", e.getMessage());
	    } finally {
	      if (con != null) {
	        stmt.close();
	        con.close();
	      } 
	    } 
		return json.toString();
	}
	
	public String delPostById(String id) throws SQLException, IOException {
		
		String status = "false";
		Connection con = SqlUtils.connect();
	    PreparedStatement stmt = null;
	    String query = null;
	    query = "SELECT * FROM tb_post where id = ?";
	    JsonObject json = new JsonObject();
	    try {
	      stmt = con.prepareStatement(query);
	      stmt.setInt(1, Integer.parseInt(id));
	      ResultSet rs = stmt.executeQuery();
		  if(rs.next()) {
			  query = "DELETE FROM tb_post\n" +
			  		"	WHERE id = ?;";
			  stmt = con.prepareStatement(query);
		      stmt.setInt(1, Integer.parseInt(id));
		      int row = stmt.executeUpdate();
		      if (row!=0) {
		    	  status = "true";
		      }	
	      }
		  json.put("status", status);
		  json.put("id", id);
	    } catch (SQLException e) {
//	      e.printStackTrace();
	      json.put("status", "false");
	      json.put("error", e.getMessage());
	    } finally {
	      if (con != null) {
	        stmt.close();
	        con.close();
	      } 
	    } 
		return json.toString();
	}
	
	public String delTagById(String id) throws SQLException, IOException {
		
		String status = "false";
		Connection con = SqlUtils.connect();
	    PreparedStatement stmt = null;
	    String query = null;
	    query = "SELECT * FROM tb_tag where id = ?";
	    JsonObject json = new JsonObject();
	    try {
	      stmt = con.prepareStatement(query);
	      stmt.setInt(1, Integer.parseInt(id));
	      ResultSet rs = stmt.executeQuery();
		  if(rs.next()) {
			  query = "DELETE FROM tb_tag\n" + 
			  		"	WHERE id = ?;";
			  stmt = con.prepareStatement(query);
		      stmt.setInt(1, Integer.parseInt(id));
		      int row = stmt.executeUpdate();
		      if (row!=0) {
		    	  status = "true";
		      }	
	      }
		  json.put("status", status);
		  json.put("id", id);
	    } catch (SQLException e) {
//	      e.printStackTrace();
	      json.put("status", "false");
	      json.put("error", e.getMessage());
	    } finally {
	      if (con != null) {
	        stmt.close();
	        con.close();
	      } 
	    } 
		return json.toString();
	}

}
