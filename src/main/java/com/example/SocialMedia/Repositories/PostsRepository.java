package com.example.SocialMedia.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SocialMedia.Models.Post;

@Repository
public interface PostsRepository extends MongoRepository<Post, String> {
	
	@Query("{username: '?0'}")
	public Post findPostByUser(String username);

}
