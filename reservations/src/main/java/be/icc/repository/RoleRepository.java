package be.icc.repository;

import be.icc.entity.RolesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends CrudRepository<RolesEntity, Long> {

    RolesEntity findByRole(String role);
}
