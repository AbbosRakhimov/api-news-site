package uz.online.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uz.online.entity.Comment;
import uz.online.entity.Post;
import uz.online.payload.ApiResponse;
import uz.online.payload.CommentDto;
import uz.online.service.CommentService;
import uz.online.service.PostService;

@RestController
@RequestMapping("/api/comment")
public class CommentControlle {

	@Autowired
	CommentService commentService;
	
	
	@PostMapping("/addComment")
	public HttpEntity<?> addComment(@Valid @RequestBody CommentDto commentDto){
		ApiResponse apiResponse = commentService.addComment(commentDto);
		return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
		
	}
	@GetMapping("/getAllComment")
	public HttpEntity<?> getAllComment(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
		List<Comment> posts = commentService.getPostPage(page, size);
		return ResponseEntity.ok(posts);
	}
	@GetMapping("/{id}")
	public HttpEntity<?> getComment(@PathVariable Long id){
		ApiResponse apiResponse = commentService.getComment(id);
		return ResponseEntity.status(apiResponse.isSucces()?200:400).body(apiResponse);
	}
	@PreAuthorize("hasAuthority('EDIT_COMMENT')")
	@PutMapping("/{id}")
	public HttpEntity<?> editComment(@PathVariable Long id, @RequestBody CommentDto commentDto){
		ApiResponse apiResponse = commentService.editComment(id, commentDto);
		return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
	}
	@PreAuthorize("hasAuthority('DELETE_MY_COMMENT')")
	@DeleteMapping("/{id}")
	public HttpEntity<?> deleteComment(@PathVariable Long id){
		ApiResponse apiResponse = commentService.deletComment(id);
		return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
	}
	
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
