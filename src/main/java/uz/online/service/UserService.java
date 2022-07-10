package uz.online.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import uz.online.entity.Role;
import uz.online.entity.User;
import uz.online.payload.ApiResponse;
import uz.online.payload.UserDto;
import uz.online.repository.RoleRepository;
import uz.online.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public ApiResponse addUser(UserDto userDto) {
		if(userRepository.existsByUsername(userDto.getUsername()))
			return new ApiResponse("username exist already", false);
		Optional<Role> role = roleRepository.findById(userDto.getRoleId());
		if(!role.isPresent())
			return new ApiResponse("Role not exist", false);
		if(role.get().getRoleName().equals("ROLE_USER"))
			return new ApiResponse("User with RoleId "+userDto.getRoleId()+" is already registered as a normal User."
					+ "Please Choose another Id", false);
		if(role.get().getRoleName().equals("ROLE_ADMIN"))
			return new ApiResponse("User with RoleId "+userDto.getRoleId()+" is already registered as a ADMIN User."
					+ "Please Choose another Id", false);
		User user = new User(userDto.getFullName(), passwordEncoder.encode(userDto.getPassword()), userDto.getUsername(),
				role.get(),true);
		userRepository.save(user);
		
		return new ApiResponse("User has been assigned to its role", true);
	}

}
