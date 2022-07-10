package uz.online.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.online.entity.enums.Permission;
import uz.online.template.AbsEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User extends AbsEntity implements UserDetails {
	
	@Column(nullable = false)
	private String fullName;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
	private Role role;
	
	private boolean enabled;
	private boolean isAccountNonExpired=true;
	private boolean isAccountNonLocked=true;
	private boolean isCredentialsNonExpired=true;
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Permission> permissions = this.role.getPermissions();
		List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
		for (Permission permission : permissions) {
			grantedAuthorities.add(new SimpleGrantedAuthority(permission.name()));
		}
//		for (Permission permission : permissions) {
//			grantedAuthorities.add(new GrantedAuthority() {
//				
//				@Override
//				public String getAuthority() {
//					// TODO Auto-generated method stub
//					return permission.name();
//				}
//			});
//		}
		return grantedAuthorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.isCredentialsNonExpired ;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}

	public User(String fullName, String username, String password, Role role, boolean enabled) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
	}
	
}
