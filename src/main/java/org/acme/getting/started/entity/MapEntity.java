package org.acme.getting.started.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;


@javax.persistence.Entity
@javax.persistence.Table(name = "Posts")
@javax.persistence.SecondaryTable(name = "Tags")
public class MapEntity {
	
	private static final long serialVersionUID = -1798070786993154676L;
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
 
    @Column(name = "title", unique = false, nullable = true, length = 100, table = "Posts")
    private String title;
 
    @Column(name = "content", unique = false, nullable = true, length = 100, table = "Posts")
    private String content;
 
    @Column(name = "tags", unique = false, nullable = true, length = 100, table = "Posts")
    private String tags;
    
    @Column(name = "label", unique = false, nullable = true, length = 100, table = "Tags")
    private String label;
 
    @Column(name = "posts", unique = false, nullable = true, length = 100, table = "Tags")
    private String posts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPosts() {
		return posts;
	}

	public void setPosts(String posts) {
		this.posts = posts;
	}

    

}
