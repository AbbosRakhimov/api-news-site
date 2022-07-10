package uz.online.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.online.template.AbsEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Post extends AbsEntity implements Serializable {

	@NotNull(message = "Titel is mandatory")
	@Column(nullable = false, columnDefinition = "text", unique = true)
	private String titel;
	
	@NotNull(message = "Text is mandatory")
	@Column(nullable = false, columnDefinition = "text")
	private String text;
	
	@NotNull(message = "Url is mandatory")
	@Column(nullable = false, columnDefinition = "text")
	private String url;
	
//	@ManyToOne
//	private Attachment attachment;
}
