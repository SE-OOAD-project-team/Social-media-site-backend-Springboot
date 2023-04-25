package com.example.SocialMedia.Controllers;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SocialMedia.Models.Credential;
import com.example.SocialMedia.Models.User;
import com.example.SocialMedia.Repositories.UsersRepository;

@RestController
@RequestMapping("/api/v2/signup")
public class Signup {
	
	@Autowired
	UsersRepository userRepo;
	
	@PostMapping(value = "", consumes = "application/json")
	public HashMap<String, String> signupUser(@RequestBody Credential cred) {
		User user = new User(cred.username, cred.password);
		
		User db_ret = userRepo.save(user);
		
		HashMap<String, String> ret = new HashMap<>();
		if (db_ret == null)
			ret.put("status", "Fail");
		else
			ret.put("status", "Success");
		return ret;
	}
}