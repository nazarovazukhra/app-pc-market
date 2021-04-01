package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.task2.entity.Comment;
import uz.pdp.task2.projection.CustomComment;


@RepositoryRestResource(path = "comment",excerptProjection = CustomComment.class)
public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
