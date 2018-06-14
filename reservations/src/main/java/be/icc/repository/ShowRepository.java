package be.icc.repository;

import be.icc.entity.CategoryEntity;
import be.icc.entity.ShowsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("showRepository")
public interface ShowRepository extends CrudRepository<ShowsEntity, Long> {

    List<ShowsEntity> findByTitle(String title);

    List<ShowsEntity> findByTitleAndCategory(String title, CategoryEntity category);

    List<ShowsEntity> findByCategory(CategoryEntity category);
}
