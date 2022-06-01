package stp.demonick.basecncprog.repository;

import stp.demonick.basecncprog.model.Detail;

import java.util.Collection;

public interface Store {
    Collection<Detail> findAllModels();

    Detail findById(int id);

    void save(Detail model);

    void delete(int id);

}
