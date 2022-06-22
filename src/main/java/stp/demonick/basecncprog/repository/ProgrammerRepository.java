package stp.demonick.basecncprog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stp.demonick.basecncprog.model.Programmer;

@Repository
public interface ProgrammerRepository extends CrudRepository<Programmer, Long> {
}
