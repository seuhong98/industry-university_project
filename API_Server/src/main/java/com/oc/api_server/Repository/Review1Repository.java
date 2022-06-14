package com.oc.api_server.Repository;

import com.oc.api_server.VO.Review1;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.util.List;

public interface Review1Repository extends JpaRepository<Review1,Long> {
    @Override
    List<Review1> findAll();

    @Override
    List<Review1> findAll(Sort sort);

    @Override
    List<Review1> findAllById(Iterable<Long> longs);

    @Override
    <S extends Review1> List<S> saveAll(Iterable<S> entities);

    @Override
    void flush();

    @Override
    <S extends Review1> S saveAndFlush(S entity);

    @Override
    <S extends Review1> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    default void deleteInBatch(Iterable<Review1> entities) {
        JpaRepository.super.deleteInBatch(entities);
    }

    @Override
    void deleteAllInBatch(Iterable<Review1> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    Review1 getOne(Long aLong);

    @Override
    Review1 getById(Long aLong);

    @Override
    Review1 getReferenceById(Long aLong);

    @Override
    <S extends Review1> List<S> findAll(Example<S> example);

    @Override
    <S extends Review1> List<S> findAll(Example<S> example, Sort sort);
}
