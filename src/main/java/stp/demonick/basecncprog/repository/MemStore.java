package stp.demonick.basecncprog.repository;

import org.springframework.stereotype.Repository;
import stp.demonick.basecncprog.model.Detail;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemStore implements Store<Detail> {
    private Map<Integer, Detail> models = new TreeMap<>();

    private static AtomicInteger modelId = new AtomicInteger(0);


    @Override
    public Collection<Detail> findAllModels() {
        return models.values();
    }


    @Override
    public Detail findById(int id) {
        return models.get(id);
    }


    @Override
    public void save(Detail model) {
        if (model.getId() == 0) {
            model.setId(modelId.incrementAndGet());
        }
        models.put(model.getId(), model);
    }

    @Override
    public void delete(int id) {
        models.remove(id);
    }
}
