package uz.online.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import uz.online.entity.User;
import uz.online.exceptions.ForbiddenExeption;

/**
 * @author Abbos Rakhimov
 *
 *checks whether the logged in user has the permission
 */
@Component
@Aspect

 public class CheckPermissionExecutor {

	@Before(value = "@annotation(checkPermission)")
	public void checkUserPermissionMyMethod(CheckPermission checkPermission) {
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean exist = false;
		if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(checkPermission.value()))) {
			exist=true;
		}
		if(!exist) {
			throw new ForbiddenExeption(checkPermission.value(), "not allowed");
		}
	}
}
