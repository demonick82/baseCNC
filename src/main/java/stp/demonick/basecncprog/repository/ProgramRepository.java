package stp.demonick.basecncprog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.model.Program;

import java.util.List;

@Repository
public interface ProgramRepository extends CrudRepository<Program, Long> {
    List<Program> findAll();
}
