package uz.pdp.task2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import uz.pdp.task2.entity.Product;
import uz.pdp.task2.projection.CustomProduct;

import java.util.List;


@RepositoryRestResource(path = "product", collectionResourceRel = "list", excerptProjection = CustomProduct.class)
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @RestResource(path = "nameStartsWith")
    List<Product> findAllByProductNameStartingWith(@Param("name") String name);


    @RestResource(path = "byPrice")
    List<Product> findAllByPriceBetween(@Param("price1") Double price1, @Param("price2") Double price2);
}
