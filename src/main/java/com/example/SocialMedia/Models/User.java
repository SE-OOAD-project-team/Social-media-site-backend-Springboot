package com.example.SocialMedia.Models;

import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "users")
public class User {
	@Id
	public String id;
	public String username;
	@JsonIgnore
	public String passwordHash;
	@JsonIgnore
	public String passwordSalt;
	public String displayName;
	public String description;
	public String picture;
	public List<String> posts;
	public Set<String> followers;
	public Set<String> following;
}
