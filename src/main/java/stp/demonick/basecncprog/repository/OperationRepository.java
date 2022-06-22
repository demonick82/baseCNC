package stp.demonick.basecncprog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stp.demonick.basecncprog.model.Operation;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {
}
