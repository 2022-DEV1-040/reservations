package be.icc.repository;

import be.icc.entity.RepresentationUserEntity;
import be.icc.entity.RepresentationsEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("representationRepository")
public interface RepresentationRepository extends CrudRepository<RepresentationsEntity, Long> {
    RepresentationsEntity findByRepresentationUsers(RepresentationUserEntity representationUser);
}
