package uz.online.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.online.entity.Role;
import uz.online.entity.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role>findByRoleName(String roleName);
	
	boolean existsByRoleName(String roleName);

	Optional<Role> findById(Integer roleId);
}
