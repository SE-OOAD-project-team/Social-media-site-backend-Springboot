package com.example.SocialMedia;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
public class Post {
	@Id
	private String id;
	private String username;
	private String desc;
	private String pic;
	private List<Comment> comments;
	private int likes_count;
	private int comments_count;
	
	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getDesc() {
		return desc;
	}

	public String getPic() {
		return pic;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public int getLikes_count() {
		return likes_count;
	}

	public int getComments_count() {
		return comments_count;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setLikes_count(int likes_count) {
		this.likes_count = likes_count;
	}

	public void setComments_count(int comments_count) {
		this.comments_count = comments_count;
	}

	public Post(String username, String desc, String pic, List<Comment> comments, int likes_count, int comments_count) {
		super();
		this.username = username;
		this.desc = desc;
		this.pic = pic;
		this.comments = comments;
		this.likes_count = likes_count;
		this.comments_count = comments_count;
	}

	@Override
	public String toString() {
		return String.format("Post{id: '%s', username: '%s', desc: '%s'}", this.id, this.username, this.desc);
	}
}
