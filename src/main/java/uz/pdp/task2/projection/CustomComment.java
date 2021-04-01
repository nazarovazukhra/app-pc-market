package uz.pdp.task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task2.entity.Comment;
import java.util.Date;


@Projection(types = Comment.class)
public interface CustomComment {

    Integer getId();

    Date getCreatedDate();

    String getMessage();

    Integer getMark();

    Integer getCustomer();

    Integer getProduct();
}
