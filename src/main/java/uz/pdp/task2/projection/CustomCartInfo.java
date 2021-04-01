package uz.pdp.task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task2.entity.CartInfo;


@Projection(types = CartInfo.class)
public interface CustomCartInfo {

    Integer getId();

    Double getQuantity();

    Integer getProduct();

    Integer getCart();
}
