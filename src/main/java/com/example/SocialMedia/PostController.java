package com.example.SocialMedia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
	@Autowired
	PostsRepository postsRepo;

	@GetMapping("/api/v2/getallposts")
	public List<Post> getPostUsername() {
		return postsRepo.findAll();
	}
}
