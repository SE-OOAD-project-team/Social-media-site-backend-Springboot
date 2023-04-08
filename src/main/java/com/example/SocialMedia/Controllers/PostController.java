package com.example.SocialMedia.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SocialMedia.Repositories.PostsRepository;

@RestController
@RequestMapping("/api/v2")
public class PostController {
	@Autowired
	PostsRepository postsRepo;

	@GetMapping("getallposts")
	public List<String> getAllPosts() {
		return postsRepo.findAll().stream().map((post) -> {
			return post.id;
		}).collect(Collectors.toList());
	}
}
