package com.example.SocialMedia.Controllers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SocialMedia.Models.Post;
import com.example.SocialMedia.Repositories.PostsRepository;

@RestController
@RequestMapping("api/v2/recommendations")
public class Recommendations {
	@Autowired
	PostsRepository postsRepo;

	@GetMapping
	public List<String> getRecommendations(@RequestParam("username") String username) {
		return postsRepo
				.findAll()
				.stream()
				.sorted(new Comparator<Post>() {
					public int compare(Post a, Post b) {
						return 10 * (b.likes_count - a.likes_count) + (b.comments_count - a.comments_count);
					}
				})
				.map((post) -> {
					return post.id;
				})
				.collect(Collectors.toList());
	}
}
