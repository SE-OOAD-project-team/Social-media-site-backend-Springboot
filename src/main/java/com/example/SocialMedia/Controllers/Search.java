package com.example.SocialMedia.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SocialMedia.Repositories.PostsRepository;
import com.example.SocialMedia.Repositories.UsersRepository;

@RestController
@RequestMapping("api/v2/search")
public class Search {
	@Autowired
	UsersRepository usersRepo;
	@Autowired
	PostsRepository postsRepo;

	@GetMapping("{string}")
	public List<String> search(@PathVariable String string) {
		return usersRepo
				.searchForUser(string)
				.stream()
				.map((user) -> user.username)
				.collect(Collectors.toList());
	}
}
