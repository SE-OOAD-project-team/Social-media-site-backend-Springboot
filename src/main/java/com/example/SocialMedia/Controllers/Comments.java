package com.example.SocialMedia.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SocialMedia.Models.Comment;
import com.example.SocialMedia.Models.Post;
import com.example.SocialMedia.Repositories.PostsRepository;

@RestController
@RequestMapping("api/v2/post/comments")
public class Comments {
	@Autowired
	PostsRepository postsRepo;
	
	@GetMapping("get/{post_id}")
	public ResponseEntity<List<Comment>> getComments(@PathVariable("post_id") String post_id) {
		Optional<Post> post = postsRepo.findById(post_id);

		if (post.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {	
			return ResponseEntity.ok(post.get().comments);
		}
	}
}
