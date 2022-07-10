package uz.online.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.online.entity.Role;
import uz.online.entity.enums.RoleName;
import uz.online.payload.ApiResponse;
import uz.online.payload.RoleDto;
import uz.online.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public ApiResponse addRole(RoleDto roleDto) {
		if(roleRepository.existsByRoleName(roleDto.getRoleName()))
			return new ApiResponse("Role with name "+roleDto.getRoleName()+" already exists", false);
		Role role = new Role(roleDto.getRoleName(), roleDto.getPermissions(), roleDto.getDesriction());
		roleRepository.save(role);
		
		return new ApiResponse("Role has been created", true);
	}

}
