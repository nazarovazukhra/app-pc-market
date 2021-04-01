package uz.pdp.task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task2.entity.Detail;


@Projection(types = Detail.class)
public interface CustomDetail {

    Integer getId();

    String getValue();

    Integer getFeature();

    Integer getProduct();
}
