package be.icc.repository;

import be.icc.entity.ShowsEntity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("showRepository")
public interface ShowRepository extends CrudRepository<ShowsEntity, Long> {

    List<ShowsEntity> findByTitle(String title);

}
