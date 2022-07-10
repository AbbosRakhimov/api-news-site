package uz.online.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.online.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	boolean existsByTitel(String titel);
	
	boolean existsByTitelAndIdNot(String titel, Long id);
}
