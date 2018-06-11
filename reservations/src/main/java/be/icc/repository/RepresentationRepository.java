package be.icc.repository;

import be.icc.entity.RepresentationsEntity;
import be.icc.entity.ShowsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("representationRepository")
public interface RepresentationRepository extends CrudRepository<RepresentationsEntity, Long> {

}
