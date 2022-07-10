package uz.online.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.online.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
