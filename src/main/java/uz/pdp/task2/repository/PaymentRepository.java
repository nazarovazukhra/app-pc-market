package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.task2.entity.Payment;
import uz.pdp.task2.projection.CustomPayment;

@RepositoryRestResource(path = "payment",excerptProjection = CustomPayment.class)
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
