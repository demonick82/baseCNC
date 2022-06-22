package stp.demonick.basecncprog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stp.demonick.basecncprog.model.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}
