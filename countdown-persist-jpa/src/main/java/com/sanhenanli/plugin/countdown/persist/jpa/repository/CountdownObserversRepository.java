package com.sanhenanli.plugin.countdown.persist.jpa.repository;

import com.sanhenanli.plugin.countdown.persist.jpa.model.entity.CountdownObserversEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * datetime 2020/8/18 10:45
 *
 * @author zhouwenxiang
 */
@Repository
public interface CountdownObserversRepository extends CrudRepository<CountdownObserversEntity, Integer> {

    CountdownObserversEntity findByNameAndObserver(String name, String observer);

    List<CountdownObserversEntity> findAllByName(String name);
}
