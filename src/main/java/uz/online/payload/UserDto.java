package uz.online.payload;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	@NotNull(message = "fullName is mandatory")
	private String fullName;
	
	@NotNull(message = "username is mandatory")
	private String username;
	
	@NotNull(message = "passowrd is mandatory")
	private String password;
	
	@NotNull(message = "roleId is mandatory")
	private Integer roleId;
}
