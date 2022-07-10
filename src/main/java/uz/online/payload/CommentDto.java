package uz.online.payload;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

	
	private String text;
	
	@NotNull(message = "PostId is mandatory")
	private Long postId;
}
