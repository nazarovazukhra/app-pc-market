package uz.pdp.task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task2.entity.Feature;


@Projection(types = Feature.class)
public interface CustomFeature {

    Integer getId();

    String getName();

    Integer getCategory();
}
