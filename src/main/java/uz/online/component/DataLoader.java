package uz.online.component;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import uz.online.entity.Role;
import uz.online.entity.User;
import uz.online.entity.enums.Permission;
import uz.online.entity.enums.RoleName;
import uz.online.repository.RoleRepository;
import uz.online.repository.UserRepository;
import static uz.online.entity.enums.Permission.*;

//is a Class that load Admin and User at the Biginn Programm into Database
@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Value("${spring.sql.init.mode}")
	private String initialMode; // insert Admin and User into database if initialMode equals always is
	
	@Override
	public void run(String... args) throws Exception {

		if (initialMode.equals("always")) {
			Role admin = roleRepository.save(new Role(RoleName.ROLE_ADMIN.name(), Arrays.asList(Permission.values()),"System owner"));

			Role user = roleRepository
					.save(new Role(RoleName.ROLE_USER.name(), Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT),"simples user"));

			userRepository.save(new User("Admin", "admin", passwordEncoder.encode("admin123"), admin, true));

			userRepository.save(new User("User", "user", passwordEncoder.encode("user123"), user, true));
			
			System.out.println("im am run");
		}
	}

	
}
