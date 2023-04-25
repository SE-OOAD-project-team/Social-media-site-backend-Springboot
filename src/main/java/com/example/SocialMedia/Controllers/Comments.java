package com.example.SocialMedia.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@PostMapping("add/{post_id}")
	public ResponseEntity<Void> addComment(@PathVariable("post_id") String post_id, @RequestBody Comment comment) {
		Optional<Post> postOpt = postsRepo.findById(post_id);

		if (postOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			Post post = postOpt.get();

			post.comments.add(comment);
			post.comments_count = post.comments.size();
			
			post = postsRepo.save(post);

			return ResponseEntity.status(HttpStatus.OK).build();
		}
	}
}
