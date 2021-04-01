package uz.pdp.task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task2.entity.Invoice;

import java.util.Date;

@Projection(types = Invoice.class)
public interface CustomInvoice {

    Integer getId();

    Date getCreatedDate();

    Date getDueDate();

    Double getPrice();

    Boolean getStatus();

    Integer getOrder();

    Integer getCustomer();
}
