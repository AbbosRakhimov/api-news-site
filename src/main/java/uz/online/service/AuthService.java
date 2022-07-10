package uz.online.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import uz.online.entity.Role;
import uz.online.entity.User;
import uz.online.entity.enums.RoleName;
import uz.online.exceptions.ResourceNotFoundExeption;
import uz.online.payload.ApiResponse;
import uz.online.payload.LoginDto;
import uz.online.payload.RegisterDto;
import uz.online.repository.RoleRepository;
import uz.online.repository.UserRepository;
import uz.online.security.JwtProvider;

@Service
public class AuthService implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtProvider jwtProvider;
	
	public ApiResponse registerUser(RegisterDto registerDto) {
		if(!registerDto.getPassword().equals(registerDto.getPrePassword()))
			return new ApiResponse("Passwords do not match", false);
		if(userRepo.existsByUsername(registerDto.getUsername()))
			return new ApiResponse("Username alredy exist", false);
		User user = new User(registerDto.getFullName(), 
				registerDto.getUsername(), passwordEncoder.encode(registerDto.getPassword()),
				roleRepo.findByRoleName(RoleName.ROLE_USER.name()).orElseThrow(()->new ResourceNotFoundExeption("Role", "RoleName", RoleName.ROLE_USER)),
				true);
		userRepo.save(user);
		return new ApiResponse("User succesfully registered",true);
	}

	

	public UserDetails loadUserByUsername(String username) {
	  return userRepo.findByUsername(username).orElseThrow((() -> new UsernameNotFoundException(username)));
		
	}



	public ApiResponse loginUser(LoginDto loginDto) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		User user = (User) authentication.getPrincipal();
		String token = jwtProvider.generateToken(user.getUsername(), user.getRole());
		return new  ApiResponse("Succesfuly authenticated",true, token);
	}

}
