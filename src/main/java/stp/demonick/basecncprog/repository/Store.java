package stp.demonick.basecncprog.repository;

import stp.demonick.basecncprog.model.Detail;

import java.util.Collection;

public interface Store<T> {

    Collection<T> findAllModels();

    void save(T domain);

    T findById(int id);

    void delete(int id);

}
