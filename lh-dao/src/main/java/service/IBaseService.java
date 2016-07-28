package service;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Created by lh on 2016/3/12.
 */
public interface IBaseService<T> extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T> {
    Optional<T> add(T entity);

    Optional<T> update(T entity);
}
