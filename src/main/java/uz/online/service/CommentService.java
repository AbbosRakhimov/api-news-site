package uz.online.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import uz.online.entity.Comment;
import uz.online.entity.Post;
import uz.online.entity.User;
import uz.online.payload.ApiResponse;
import uz.online.payload.CommentDto;
import uz.online.repository.CommentRepository;
import uz.online.repository.PostRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commenRepo;
	
	@Autowired
	PostRepository postRepository;
	
	public ApiResponse addComment(CommentDto commentDto) {
		Optional<Post> pOptional = postRepository.findById(commentDto.getPostId()); 
		if(!pOptional.isPresent())
			return new ApiResponse("Post not exist",false);
		Comment comment = new Comment(commentDto.getText(), pOptional.get());
		commenRepo.save(comment);
		
		return new ApiResponse("Comment succesfuly created", true);
	}

	public ApiResponse getAllComment() {
		return new ApiResponse("",true, commenRepo.findAll());
	}
	public List<Comment> getPostPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Comment> findAll = commenRepo.findAll(pageable);
		return findAll.getContent();
	}

	public ApiResponse getComment(Long id) {
		Optional<Comment> optional = commenRepo.findById(id);
		if(!optional.isPresent())
			return new ApiResponse("Comment not found", false);
		
		return new ApiResponse("Post found", true, optional.get());
	}

	public ApiResponse editComment(Long id, CommentDto commentdto) {
		Optional<Post> post=postRepository.findById(commentdto.getPostId());
		if(!post.isPresent())
			return new ApiResponse("Post not Found", false);
		
		Optional<Comment> pOptional=commenRepo.findById(id);
		if(!pOptional.isPresent())
			return new ApiResponse("Comment not found", false);
		
		Comment comment = pOptional.get();
		comment.setText(commentdto.getText());
		comment.setPost(post.get());
		commenRepo.save(comment);
		return new ApiResponse("Succesfuly edited", true);
	}
	
	public ApiResponse deletComment(Long id) {
		Optional<Comment> pOptional = commenRepo.findById(id);
		if(!pOptional.isPresent())
			return new ApiResponse("Comment mot found", false);
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user.getId()==pOptional.get().getCreatedBy().getId()&& user.getRole().equals("ROLE_USER")) {
			commenRepo.deleteById(id);
			return new ApiResponse("your own commnet are succesfuly deleted",true);
		}else if(!(user.getId()==pOptional.get().getCreatedBy().getId()&& user.getRole().equals("ROLE_USER"))){
			commenRepo.deleteById(id);
			return new ApiResponse("your are succesfuly deleted",true);
		}
		return new ApiResponse("coun't deleted", false);
	}
}
