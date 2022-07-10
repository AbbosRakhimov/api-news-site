package uz.online.payload;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

	
	@NotNull(message = "username is mandatory")
	private String username;
	
	@NotNull(message = "passowrd is mandatory")
	private String password;
	
}
