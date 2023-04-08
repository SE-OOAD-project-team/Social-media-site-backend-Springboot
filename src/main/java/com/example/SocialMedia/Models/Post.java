package com.example.SocialMedia.Models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
public class Post {
	@Id
	public String id;
	public String username;
	public String desc;
	public String pic;
	public List<Comment> comments;
	public int likes_count;
	public int comments_count;

	@Override
	public String toString() {
		return String.format("Post{id: '%s', username: '%s', desc: '%s'}", this.id, this.username, this.desc);
	}
}
