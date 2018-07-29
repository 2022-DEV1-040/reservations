package be.icc.repository;

import be.icc.entity.CategoryEntity;
import be.icc.entity.RolesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("categoryRepository")
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    CategoryEntity findByNom(String nom);
}
