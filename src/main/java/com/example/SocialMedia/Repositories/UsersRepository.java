package com.example.SocialMedia.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SocialMedia.Models.User;

@Repository
public interface UsersRepository extends MongoRepository<User, String> {
	
	@Query("{username: '?0'}")
	public Optional<User> findByUsername(String username);

	@Query("{username: {$regex: '?0', $options: 'i'} }")
	public List<User> searchForUser(String string);

}
