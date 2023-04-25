package com.example.SocialMedia.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SocialMedia.Models.User;
import com.example.SocialMedia.Repositories.UsersRepository;

@RestController
@RequestMapping("api/v2/profile")
public class Profile {
	@Autowired
	UsersRepository usersRepo;

	@GetMapping("/{username}")
	public ResponseEntity<User> getProfile(@PathVariable("username") String username) {
		Optional<User> userOpt = usersRepo.findByUsername(username);

		if (userOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(userOpt.get());
		}
	}

	@PostMapping
	public ResponseEntity<User> editProfile(@RequestBody User editedUser) {
		Optional<User> userOpt = usersRepo.findByUsername(editedUser.username);

		if (userOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			User user = userOpt.get();

			user.displayName = editedUser.displayName;
			user.description = editedUser.description;

			user = usersRepo.save(user);

			return ResponseEntity.ok(user);
		}
	}
}
