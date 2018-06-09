package be.icc.repository;

import be.icc.entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<UsersEntity, Long> {

    UsersEntity findByLogin(String login);
}
