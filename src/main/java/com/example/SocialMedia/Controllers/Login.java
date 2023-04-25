package com.example.SocialMedia.Controllers;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SocialMedia.Models.Credential;
import com.example.SocialMedia.Models.User;
import com.example.SocialMedia.Repositories.UsersRepository;

@RestController
@RequestMapping("/api/v2/login")
public class Login {
	
	@Autowired
	UsersRepository userRepo;
	
	@PostMapping("")
	public HashMap<String, String> loginUser(@RequestBody Credential cred) {
		Optional<User> user = userRepo.findByUsername(cred.username);
		HashMap<String, String> ret = new HashMap<>();
		if(user.isPresent() && (user.get()).passwordHash.equals(cred.password)) {
			ret.put("status", "Success");
			ret.put("token", "secret");		// CHANGE THE TOKEN
		}
		else {
			ret.put("status", "Failure");
			ret.put("reason", "Invalid username or password");
		}
		return ret;
	}
}