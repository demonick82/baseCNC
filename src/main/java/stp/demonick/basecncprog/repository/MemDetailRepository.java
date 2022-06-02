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
    private Map<Integer, Detail> models = new TreeMap<>();

    private static AtomicInteger modelId = new AtomicInteger(0);
    private static AtomicInteger programsId = new AtomicInteger(0);
    private static AtomicInteger operationsId = new AtomicInteger(0);
    private static AtomicInteger toolsId = new AtomicInteger(0);


    @Override
    public Collection<Detail> findAll() {
        return models.values();
    }


    @Override
    public Detail findById(int id) {
        return models.get(id);
    }


    @Override
    public void save(Detail detail) {
        if (detail.getId() == 0) {
            detail.setId(modelId.incrementAndGet());
        }
        setIdOperations(detail);
        models.put(detail.getId(), detail);
    }

    @Override
    public void delete(int id) {
        models.remove(id);
    }

    private void setIdOperations(Detail detail) {
        for (Program program : detail.getPrograms()) {
            program.setId(programsId.incrementAndGet());
            for (Operation operation : program.getOperations()) {
                operation.setId(operationsId.incrementAndGet());
                operation.getTool().setId(toolsId.incrementAndGet());
            }
        }
    }
}
