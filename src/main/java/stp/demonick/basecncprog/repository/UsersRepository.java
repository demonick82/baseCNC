package stp.demonick.basecncprog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stp.demonick.basecncprog.model.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
}
