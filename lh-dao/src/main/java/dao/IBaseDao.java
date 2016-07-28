package dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by lh on 2016/3/7.
 */
public interface IBaseDao<T> extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T> {
    Optional<T> saveEntity(T entity);

    Page<T> findAll(int page, int size);

    Page<T> findAll(int page, int size, Sort.Direction direction, String sortField);

    List<T> findByFieldsAndValues(Object... fieldsAndValues);

    Page<T> findByFieldsAndValues(int page, int size, Object... fieldsAndValues);

    Page<T> findByFieldsAndValues(int page, int size, Sort.Direction direction, String sortField, Object... fieldsAndValues);
}
