package stp.demonick.basecncprog.repository;

import stp.demonick.basecncprog.model.tools.Tool;

import java.util.Collection;

public class MemToolRepository implements Store<Tool> {
    @Override
    public Collection<Tool> findAll() {
        return null;
    }

    @Override
    public void save(Tool domain) {

    }

    @Override
    public Tool findById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
