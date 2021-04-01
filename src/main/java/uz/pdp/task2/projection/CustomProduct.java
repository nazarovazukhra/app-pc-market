package uz.pdp.task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task2.entity.Attachment;
import uz.pdp.task2.entity.Product;

import java.util.List;

@Projection(types = Product.class)
public interface CustomProduct {

    Integer getId();

    String getProductName();

    String getDescription();

    Double getPrice();

    Integer getCategory();

    List<Attachment> getAttachment();
}
