package stp.demonick.basecncprog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stp.demonick.basecncprog.model.tools.Tool;

@Repository
public interface ToolRepository extends CrudRepository<Tool, Long> {
}
