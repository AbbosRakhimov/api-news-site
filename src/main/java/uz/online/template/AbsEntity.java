package uz.online.template;



import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import uz.online.entity.User;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
@Audited
public abstract class AbsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable = false, nullable = false)
	@CreationTimestamp
	private Timestamp createdAt;

	@Column(updatable = false)
	@UpdateTimestamp
	private Timestamp updateAt;
	
	@JoinColumn(updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@CreatedBy
	private User createdBy;
	
	@LastModifiedBy
	@ManyToOne(fetch = FetchType.LAZY)
	private User updateBy;

}
