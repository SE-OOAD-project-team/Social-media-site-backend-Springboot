package com.example.SocialMedia.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SocialMedia.Models.Comment;
import com.example.SocialMedia.Models.Post;
import com.example.SocialMedia.Repositories.PostsRepository;

@RestController
@RequestMapping("/api/v2/post")
public class Posts {
	@Autowired
	PostsRepository postsRepo;

	@GetMapping("get/{id}")
	public ResponseEntity<Post> getPost(@PathVariable("id") String id) {
		Optional<Post> post = postsRepo.findById(id);

		if (post.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(post.get());
		}
	}

	@PostMapping(value = "create", consumes = "application/json")
	public Post createPost(@RequestBody Post post) {
		if (post.comments == null) {
			post.comments = new ArrayList<Comment>();
		}
		post.comments_count = post.comments.size();
		post.likes_count = 0;

		Post created = postsRepo.save(post);

		return created;
	}

	@GetMapping("getallposts")
	public List<String> getAllPosts() {
		return postsRepo.findAll().stream().map((post) -> {
			return post.id;
		}).collect(Collectors.toList());
	}
	
	@PostMapping("inc-like")
	public HashMap<String, String> incrementLike(@RequestBody HashMap<String, String> req) {
		String username = req.get("username");
		String post_id = req.get("post_id");
		
		HashMap<String, String> ret = new HashMap<>();
		
		Optional<Post> post_opt =postsRepo.findById(post_id);
		if(post_opt.isPresent()) {
			Post post = post_opt.get();
			int curr_likes_count = post.likes_count;
			post.likes_count = curr_likes_count + 1;
			Post updated_post = postsRepo.save(post);
			
			if (updated_post.likes_count > curr_likes_count)
				ret.put("status", "Success");
			else
				ret.put("status", "Failure");
		}
		else
			ret.put("status", "Failure");
		
		return ret;
	}

}
