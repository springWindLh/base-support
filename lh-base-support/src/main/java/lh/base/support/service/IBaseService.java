package lh.base.support.service;

import lh.base.support.dao.QueryItem;
import lh.base.support.model.BaseDomain;
import lh.base.support.model.DomainPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Created by lh on 2016/3/12.
 */
public interface IBaseService<T extends BaseDomain> {
    Optional<T> add(T entity);

    Optional<T> update(T entity);

    DomainPage<T> findAll(int page, int size);

    DomainPage<T> findAll(int page, int size, Sort.Direction direction, String sortField);

    List<T> findByFieldsAndValues(Object... fieldsAndValues);

    DomainPage<T> findByFieldsAndValues(int page, int size, Object... fieldsAndValues);

    DomainPage<T> findByFieldsAndValues(int page, int size, Sort.Direction direction, String sortField, Object... fieldsAndValues);

    DomainPage<T> findByParamsMap(int page, int size, Map<String, Object> paramsMap);

    DomainPage<T> findByParamsMap(int page, int size, Sort.Direction direction, String sortField, Map<String, Object> paramsMap);

    DomainPage<T> findByQueryItems(int page, int size, List<QueryItem> queryItems);

    DomainPage<T> findByQueryItems(int page, int size, Sort.Direction direction, String sortField, List<QueryItem> queryItems);

    List<T> findAll(Sort sort);

    List<T> findAll(Iterable<Long> ids);

    List<T> findAll(Specification specification);

    Page<T> findAll(Specification specification, Pageable pageable);

    List<T> findAll();

    List<T> add(Iterable<T> entities);

    void flush();

    Optional<T> saveAndFlush(T entity);

    void deleteInBatch(Iterable<T> entities);

    void deleteAllInBatch(Class<T> clazz);

    Optional<T> getOne(Long id);

    Page<T> findAll(Pageable pageable);

    Optional<T> findOne(Long id);

    boolean exists(Long id);

    long count();

    void delete(Class<T> clazz,Long id);

    void delete(T entity);

    void delete(Iterable<? extends T> entities);

    void deleteAll(Class<T> clazz);

}
