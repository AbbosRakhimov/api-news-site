package uz.online.payload;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

	@NotNull(message = "fullName is mandatory")
	private String fullName;
	
	@NotNull(message = "username is mandatory")
	private String username;
	
	@NotNull(message = "passowrd is mandatory")
	private String password;
	
	@NotNull(message = "prePassword is mandatory")
	private String prePassword;
}
