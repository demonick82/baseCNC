package stp.demonick.basecncprog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stp.demonick.basecncprog.model.Detail;

import java.util.List;

@Repository
public interface DetailRepository extends CrudRepository<Detail, Long> {
    List<Detail> findAll();

    List<Detail> findByDrawingNumberContainingIgnoreCase(String name);
}
