package com.sanhenanli.plugin.countdown.persist.jpa.repository;

import com.sanhenanli.plugin.countdown.persist.jpa.model.entity.CountdownStatesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * datetime 2020/8/18 10:46
 *
 * @author zhouwenxiang
 */
@Repository
public interface CountdownStatesRepository extends CrudRepository<CountdownStatesEntity, Integer> {

    List<CountdownStatesEntity> findAllByName(String name);

    CountdownStatesEntity findFirstByNameOrderByIdDesc(String name);

    CountdownStatesEntity findFirstByNameOrderByIdAsc(String name);
}
