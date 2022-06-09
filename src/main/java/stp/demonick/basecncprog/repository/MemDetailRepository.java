package stp.demonick.basecncprog.repository;

import org.springframework.stereotype.Repository;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.model.Operation;
import stp.demonick.basecncprog.model.Program;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemDetailRepository implements Store<Detail> {
    private final Map<Integer, Detail> details = new TreeMap<>();

    private static final AtomicInteger detailsId = new AtomicInteger(0);

    public MemDetailRepository() {
        Detail detail1 = Detail.of("M6-AP2TM_04.02.01", "Дозатор");
        detail1.setId(detailsId.incrementAndGet());
        details.put(detail1.getId(), detail1);
        Detail detail2 = Detail.of("ИЯЕИЛ8.034.374", "Корпус");
        detail2.setId(detailsId.incrementAndGet());
        details.put(detail2.getId(), detail2);
    }

    @Override
    public Collection<Detail> findAll() {
        return details.values();
    }


    @Override
    public Detail findById(int id) {
        return details.get(id);
    }


    @Override
    public void save(Detail detail) {
        if (detail.getId() == 0) {
            detail.setId(detailsId.incrementAndGet());
        }
        details.put(detail.getId(), detail);
    }

    @Override
    public void delete(int id) {
        details.remove(id);
    }


}
