package com.example.SocialMedia.Models;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "credential")
public class Credential {
	public String username;
	public String password;
}