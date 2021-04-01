package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.task2.entity.Invoice;
import uz.pdp.task2.projection.CustomInvoice;


@RepositoryRestResource(path = "invoice",excerptProjection = CustomInvoice.class)
public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
}
