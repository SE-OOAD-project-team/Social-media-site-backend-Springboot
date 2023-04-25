package com.example.SocialMedia.Models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	@Id
	public String id;
	public String username;
	public String passwordHash;
	public String passwordSalt;
	public String displayName;
	public String description;
	public String picture;
	public List<String> posts;
	public List<String> followers;
	public List<String> following;
	
	public User() {
		
	}
	
	public User(String username, String passwordHash) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.passwordSalt = "";
		this.displayName = "";
		this.description = "";
		this.picture = "";
		this.posts = new ArrayList<>();
		this.followers = new ArrayList<>();
		this.following = new ArrayList<>();
	}
}
