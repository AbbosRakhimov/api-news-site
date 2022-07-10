package uz.online.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import uz.online.entity.Post;
import uz.online.payload.ApiResponse;
import uz.online.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;
	
	public ApiResponse addPost(Post post) {
		if(postRepository.existsByTitel(post.getTitel()))
			return new ApiResponse("Titel exist already",false);
		Post post2 = new Post(post.getText(), post.getTitel(),post.getUrl());
		postRepository.save(post2);
		
		return new ApiResponse("Post succesfuly created", true);
	}

	public ApiResponse getAllPost() {
		return new ApiResponse("",true, postRepository.findAll());
	}
	public List<Post> getPostPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Post> findAll = postRepository.findAll(pageable);
		return findAll.getContent();
	}

	public ApiResponse getPost(Integer id) {
		Optional<Post> optional = postRepository.findById(Long.valueOf(id));
		if(!optional.isPresent())
			return new ApiResponse("Post not found", false);
		
		return new ApiResponse("Post found", true, optional.get());
	}

	public ApiResponse editPost(Long id, Post post) {
		if(postRepository.existsByTitelAndIdNot(post.getTitel(), id))
			return new ApiResponse("Titel already exist", false);
		Optional<Post> pOptional=postRepository.findById(id);
		if(!pOptional.isPresent())
			return new ApiResponse("Post not found", false);
		Post post2 = pOptional.get();
		post2.setText(post.getText());
		post2.setTitel(post.getTitel());
		post2.setUrl(post.getUrl());
		postRepository.save(post2);
		return new ApiResponse("Succesfuly edited", true);
	}
	
	public ApiResponse deletPost(Long id) {
		Optional<Post> pOptional = postRepository.findById(id);
		if (pOptional.isPresent()) {
			postRepository.deleteById(id);
			return new ApiResponse("Succesfuly deleted",true);
		}
		return new ApiResponse("Post not found", false);
	}
}
