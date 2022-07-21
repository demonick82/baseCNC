package stp.demonick.basecncprog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import stp.demonick.basecncprog.model.Detail;

import java.util.List;

@Repository
public interface DetailRepository extends PagingAndSortingRepository<Detail, Long> {
    List<Detail> findByOrderByDrawingNumberAsc();
}
