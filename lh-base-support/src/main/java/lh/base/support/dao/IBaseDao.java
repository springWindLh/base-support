package lh.base.support.dao;

import lh.base.support.model.BaseDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * Created by lh on 2016/3/7.
 */
public interface IBaseDao<T extends BaseDomain> {

    Page<T> findAll(int page, int size);

    Page<T> findAll(int page, int size, Sort.Direction direction, String sortField);

    List<T> findByFieldsAndValues(Object... fieldsAndValues);

    Page<T> findByFieldsAndValues(int page, int size, Object... fieldsAndValues);

    Page<T> findByFieldsAndValues(int page, int size, Sort.Direction direction, String sortField, Object... fieldsAndValues);

    Page<T> findByMap(int page, int size, Map<String, Object> map);

    Page<T> findByMap(int page, int size, Sort.Direction direction, String sortField, Map<String, Object> map);

    Page<T> findByQueryItems(int page, int size, List<QueryItem> queryItems);

    Page<T> findByQueryItems(int page, int size, Sort.Direction direction, String sortField, List<QueryItem> queryItems);

    List<T> findAll(Sort sort);

    List<T> findAll(Iterable<Long> ids);

    List<T> findAll(Specification specification);

    Page<T> findAll(Specification specification, Pageable pageable);

    List<T> findAll();

    List<T> save(Iterable<T> entities);

    void flush();

    T saveAndFlush(T entity);

    void deleteInBatch(Iterable<T> entities);

    void deleteAllInBatch();

    T getOne(Long id);

    Page<T> findAll(Pageable pageable);

    T save(T entity);

    T findOne(Long id);

    boolean exists(Long id);

    long count();

    void delete(Long id);

    void delete(Object entity);

    void delete(Iterable<? extends T> entities);

    void deleteAll();
}
