package uz.pdp.task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task2.entity.Payment;

import java.util.Date;

@Projection(types = Payment.class)
public interface CustomPayment {

    Integer getId();

    Date getDate();

    Double getPrice();

    Integer getCustomer();

    Integer getInvoice();
}
