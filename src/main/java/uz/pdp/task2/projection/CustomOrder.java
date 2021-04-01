package uz.pdp.task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task2.entity.Cart;
import uz.pdp.task2.entity.Order;

import java.util.Date;
import java.util.List;

@Projection(types = Order.class)
public interface CustomOrder {

    Integer getId();

    Date getDate();

    Integer getCustomer();

    List<Cart> getCart();
}
