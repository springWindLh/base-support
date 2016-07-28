package lh.base.support.daoImpl;

import lh.base.support.dao.IBaseDao;
import lh.base.support.dao.QueryItem;
import lh.base.support.model.BaseDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lh on 2016/3/7.
 */
@NoRepositoryBean
public abstract class BaseDaoImpl<T extends BaseDomain> implements IBaseDao<T> {

    private static final String PARAMS_NUMBERS_ERROR = "参数个数错误";

    public abstract JpaRepository getRepository();

    public abstract JpaSpecificationExecutor getSpecificationExecutor();

    @Override
    public Page<T> findAll(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size);
        return findAll(pageRequest);
    }

    @Override
    public Page<T> findAll(int page, int size, Sort.Direction direction, String sortField) {
        Sort sort = new Sort(direction, sortField);
        PageRequest pageRequest = new PageRequest(page, size, sort);
        return findAll(pageRequest);
    }

    @Override
    public List<T> findByFieldsAndValues(Object... fieldsAndValues) {
        return findAll(getWhereSpecification(fieldsAndValues));
    }

    @Override
    public Page<T> findByFieldsAndValues(int page, int size, Object... fieldsAndValues) {
        PageRequest pageRequest = new PageRequest(page, size);
        return findAll(getWhereSpecification(fieldsAndValues), pageRequest);
    }

    @Override
    public Page<T> findByFieldsAndValues(int page, int size, Sort.Direction direction, String sortField, Object... fieldsAndValues) {
        Sort sort = new Sort(direction, sortField);
        PageRequest pageRequest = new PageRequest(page, size, sort);
        return findAll(getWhereSpecification(fieldsAndValues), pageRequest);
    }

    @Override
    public Page<T> findByMap(int page, int size, Map<String, Object> map) {
        PageRequest pageRequest = new PageRequest(page, size);
        return findAll(getWhereSpecification(map), pageRequest);
    }

    @Override
    public Page<T> findByMap(int page, int size, Sort.Direction direction, String sortField, Map<String, Object> map) {
        Sort sort = new Sort(direction, sortField);
        PageRequest pageRequest = new PageRequest(page, size, sort);
        return findAll(getWhereSpecification(map), pageRequest);
    }

    @Override
    public Page<T> findByQueryItems(int page, int size, List<QueryItem> queryItems) {
        PageRequest pageRequest = new PageRequest(page, size);
        return findAll(getWhereSpecification(queryItems), pageRequest);
    }

    @Override
    public Page<T> findByQueryItems(int page, int size, Sort.Direction direction, String sortField, List<QueryItem> queryItems) {
        Sort sort = new Sort(direction, sortField);
        PageRequest pageRequest = new PageRequest(page, size, sort);
        return findAll(getWhereSpecification(queryItems), pageRequest);
    }

    private Specification getWhereSpecification(final Object... fieldsAndValues) {
        this.CheckFieldsAndValues();
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Object> predicates = new ArrayList<>();
                for (int i = 0; i < fieldsAndValues.length; i += 2) {
                    Predicate predicate = cb.equal(root.get(fieldsAndValues[i].toString()), fieldsAndValues[i + 1]);
                    predicates.add(predicate);
                }
                query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                return query.getRestriction();
            }
        };
        return specification;
    }

    private Specification getWhereSpecification(final Map<String, Object> paramsMap) {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Object> predicates = new ArrayList<>();
                for (Map.Entry entry : paramsMap.entrySet()) {
                    Predicate predicate = cb.equal(root.get(entry.getKey().toString()), entry.getValue());
                    predicates.add(predicate);
                }
                query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                return query.getRestriction();
            }
        };
        return specification;
    }

    private Specification getWhereSpecification(final List<QueryItem> queryItems) {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Object> predicates = new ArrayList<>();
                for (QueryItem item : queryItems) {
                    Predicate predicate = null;
                    if (item.getOperatorType() != null) {
                        switch (item.getOperatorType()) {
                            case LIKE:
                                predicate = cb.like(root.get(item.getField()), "%" + item.getValue() + "%");
                                break;
                            case GREATER_THAN:
                                predicate = cb.gt(root.get(item.getField()), (Number) item.getValue());
                                break;
                            case LESS_THAN:
                                predicate = cb.lt(root.get(item.getField()), (Number) item.getValue());
                                break;
                            case GREATER_THAN_OR_EQUAL:
                                predicate = cb.ge(root.get(item.getField()), (Number) item.getValue());
                                break;
                            case LESS_THAN_OR_EQUAL:
                                predicate = cb.le(root.get(item.getField()), (Number) item.getValue());
                                break;
                            default:
                                predicate = cb.equal(root.get(item.getField()), item.getValue());
                                break;
                        }
                    } else {
                        predicate = cb.equal(root.get(item.getField()), item.getValue());
                    }
                    predicates.add(predicate);
                }
                query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                return query.getRestriction();
            }
        };
        return specification;
    }

    private boolean CheckFieldsAndValues(Object... fieldsAndValues) {
        if (fieldsAndValues.length % 2 != 0) {
            throw new IllegalArgumentException(PARAMS_NUMBERS_ERROR);
        } else {
            return true;
        }
    }

    @Override
    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    @Override
    public List<T> findAll(Iterable<Long> ids) {
        return getRepository().findAll(ids);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public List<T> findAll(Specification specification) {
        return getSpecificationExecutor().findAll(specification);
    }

    @Override
    public Page<T> findAll(Specification specification, Pageable pageable) {
        return getSpecificationExecutor().findAll(specification, pageable);
    }

    @Override
    public List<T> save(Iterable<T> entities) {
        return getRepository().save(entities);
    }

    @Override
    public void flush() {
        getRepository().flush();
    }

    @Override
    public T saveAndFlush(T entity) {
        return (T) getRepository().saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        getRepository().deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        getRepository().deleteAllInBatch();
    }

    @Override
    public T getOne(Long id) {
        return (T) getRepository().getOne(id);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public T save(T entity) {
        return (T) getRepository().save(entity);
    }

    @Override
    public long count() {
        return getRepository().count();
    }

    @Override
    public void delete(Object entity) {
        getRepository().delete(entity);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        getRepository().delete(entities);
    }

    @Override
    public void deleteAll() {
        getRepository().deleteAll();
    }

    @Override
    public T findOne(Long id) {
        return (T) getRepository().findOne(id);
    }

    @Override
    public boolean exists(Long id) {
        return getRepository().exists(id);
    }

    @Override
    public void delete(Long id) {
        getRepository().delete(id);
    }

}
