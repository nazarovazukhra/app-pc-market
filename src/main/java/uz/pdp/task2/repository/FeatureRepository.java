package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.task2.entity.Feature;
import uz.pdp.task2.projection.CustomFeature;

@RepositoryRestResource(path = "feature",excerptProjection = CustomFeature.class)
public interface FeatureRepository extends JpaRepository<Feature,Integer> {
}
