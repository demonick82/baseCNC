package stp.demonick.basecncprog.repository;

import java.util.Collection;

public interface Store<T> {

    Collection<T> findAll();

    void save(T domain);

    T findById(int id);

    void delete(int id);

}
