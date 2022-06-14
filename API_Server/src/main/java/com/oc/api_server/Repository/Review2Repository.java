package com.oc.api_server.Repository;

import com.oc.api_server.VO.Review2;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface Review2Repository extends JpaRepository<Review2,Long> {
    @Override
    List<Review2> findAll();

    @Override
    List<Review2> findAll(Sort sort);

    @Override
    List<Review2> findAllById(Iterable<Long> longs);

    @Override
    <S extends Review2> List<S> saveAll(Iterable<S> entities);

    @Override
    void flush();

    @Override
    <S extends Review2> S saveAndFlush(S entity);

    @Override
    <S extends Review2> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    default void deleteInBatch(Iterable<Review2> entities) {
        JpaRepository.super.deleteInBatch(entities);
    }

    @Override
    void deleteAllInBatch(Iterable<Review2> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    Review2 getOne(Long aLong);

    @Override
    Review2 getById(Long aLong);

    @Override
    Review2 getReferenceById(Long aLong);

    @Override
    <S extends Review2> List<S> findAll(Example<S> example);

    @Override
    <S extends Review2> List<S> findAll(Example<S> example, Sort sort);
}
