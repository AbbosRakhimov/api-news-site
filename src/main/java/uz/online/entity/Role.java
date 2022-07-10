package uz.online.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.online.entity.enums.Permission;
import uz.online.entity.enums.RoleName;
import uz.online.template.AbsEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Role extends AbsEntity {

//	private String name;
	
//	@JoinColumn(unique = true, nullable = false)
//	@Enumerated(value = EnumType.STRING)
	@Column(unique = true, nullable = false)
	private String roleName;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.LAZY)
	private List<Permission> permissions;
	
	@Column(columnDefinition = "text")
	private String desripcion;
}
