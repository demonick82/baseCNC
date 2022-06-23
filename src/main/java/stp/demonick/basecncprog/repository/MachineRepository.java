package stp.demonick.basecncprog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stp.demonick.basecncprog.model.Machine;

@Repository
public interface MachineRepository extends CrudRepository<Machine, Long> {
    Machine findByNameForNX(String name);
}
