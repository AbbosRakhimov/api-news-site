package uz.online.payload;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.online.entity.enums.Permission;
import uz.online.entity.enums.RoleName;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

	@NotBlank
	private String roleName;
	
	@NotEmpty
	private List<Permission> permissions;
	
	private String desriction;
}
