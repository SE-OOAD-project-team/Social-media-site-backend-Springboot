package com.example.SocialMedia;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends MongoRepository<Post, ObjectId> {
	
	@Query("{username: '?0'}")
	public Post findPostByUser(String username);

}
