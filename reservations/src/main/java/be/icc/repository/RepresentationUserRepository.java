package be.icc.repository;

import be.icc.entity.RepresentationUserEntity;
import be.icc.entity.RepresentationsEntity;
import be.icc.entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("representationUserRepository")
public interface RepresentationUserRepository extends CrudRepository<RepresentationUserEntity, Long> {

    List<RepresentationUserEntity> findByUsersByUserId(UsersEntity usersByUserId);

}
