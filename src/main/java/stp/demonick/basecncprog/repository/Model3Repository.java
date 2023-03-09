package stp.demonick.basecncprog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stp.demonick.basecncprog.model.Model3D;

@Repository
public interface Model3Repository extends CrudRepository<Model3D,Long> {
}
