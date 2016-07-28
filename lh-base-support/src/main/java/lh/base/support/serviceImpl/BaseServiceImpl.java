package lh.base.support.serviceImpl;

import lh.base.support.dao.IBaseDao;
import lh.base.support.dao.QueryItem;
import lh.base.support.model.BaseDomain;
import lh.base.support.model.CanLogicDelDomain;
import lh.base.support.model.DomainPage;
import lh.base.support.service.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by lh on 2016/3/12.
 */
@NoRepositoryBean
public abstract class BaseServiceImpl<T extends BaseDomain> implements IBaseService<T> {

    public abstract IBaseDao<T> getDao();

    public Optional<T> add(T entity) {
        return Optional.ofNullable(this.getDao().save(entity));
    }

    public Optional<T> update(T entity) {
        return Optional.ofNullable(this.getDao().save(entity));
    }

    @Override
    public DomainPage<T> findAll(int page, int size) {
        Page resultPage = this.getDao().findAll(page, size);
        return new DomainPage<>(page, size, resultPage.getTotalPages(), resultPage.getTotalElements(), resultPage.hasNext(), resultPage.isLast(), resultPage.getContent());
    }

    @Override
    public DomainPage<T> findAll(int page, int size, Sort.Direction direction, String sortField) {
        Page resultPage = this.getDao().findAll(page, size, direction, sortField);
        return new DomainPage<>(page, size, resultPage.getTotalPages(), resultPage.getTotalElements(), resultPage.hasNext(), resultPage.isLast(), resultPage.getContent());
    }

    @Override
    public List<T> findByFieldsAndValues(Object... fieldsAndValues) {
        return this.getDao().findByFieldsAndValues(fieldsAndValues);
    }

    @Override
    public DomainPage<T> findByFieldsAndValues(int page, int size, Object... fieldsAndValues) {
        Page resultPage = this.getDao().findByFieldsAndValues(page, size, fieldsAndValues);
        return new DomainPage<>(page, size, resultPage.getTotalPages(), resultPage.getTotalElements(), resultPage.hasNext(), resultPage.isLast(), resultPage.getContent());
    }

    @Override
    public DomainPage<T> findByFieldsAndValues(int page, int size, Sort.Direction direction, String sortField, Object... fieldsAndValues) {
        Page resultPage = this.getDao().findByFieldsAndValues(page, size, direction, sortField, fieldsAndValues);
        return new DomainPage<>(page, size, resultPage.getTotalPages(), resultPage.getTotalElements(), resultPage.hasNext(), resultPage.isLast(), resultPage.getContent());
    }

    @Override
    public DomainPage<T> findByParamsMap(int page, int size, Map<String, Object> paramsMap) {
        Page resultPage = this.getDao().findByMap(page, size, paramsMap);
        return new DomainPage<>(page, size, resultPage.getTotalPages(), resultPage.getTotalElements(), resultPage.hasNext(), resultPage.isLast(), resultPage.getContent());
    }

    @Override
    public DomainPage<T> findByParamsMap(int page, int size, Sort.Direction direction, String sortField, Map<String, Object> paramsMap) {
        Page resultPage = this.getDao().findByMap(page, size, direction, sortField, paramsMap);
        return new DomainPage<>(page, size, resultPage.getTotalPages(), resultPage.getTotalElements(), resultPage.hasNext(), resultPage.isLast(), resultPage.getContent());
    }

    @Override
    public DomainPage<T> findByQueryItems(int page, int size, List<QueryItem> queryItems) {
        Page resultPage = this.getDao().findByQueryItems(page, size, queryItems);
        return new DomainPage<>(page, size, resultPage.getTotalPages(), resultPage.getTotalElements(), resultPage.hasNext(), resultPage.isLast(), resultPage.getContent());
    }

    @Override
    public DomainPage<T> findByQueryItems(int page, int size, Sort.Direction direction, String sortField, List<QueryItem> queryItems) {
        Page resultPage = this.getDao().findByQueryItems(page, size, direction, sortField, queryItems);
        return new DomainPage<>(page, size, resultPage.getTotalPages(), resultPage.getTotalElements(), resultPage.hasNext(), resultPage.isLast(), resultPage.getContent());
    }

    @Override
    public List<T> findAll(Sort sort) {
        return getDao().findAll(sort);
    }

    @Override
    public List<T> findAll(Iterable<Long> ids) {
        return getDao().findAll(ids);
    }

    @Override
    public List<T> findAll(Specification specification) {
        return getDao().findAll(specification);
    }

    @Override
    public Page<T> findAll(Specification specification, Pageable pageable) {
        return getDao().findAll(specification, pageable);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    public List<T> add(Iterable<T> entities) {
        return getDao().save(entities);
    }

    @Override
    public void flush() {
        getDao().flush();
    }

    @Override
    public Optional<T> saveAndFlush(T entity) {
        return Optional.ofNullable(getDao().saveAndFlush(entity));
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        if (entities.iterator().hasNext() && !isCanLogicDel(entities.iterator().next())) {
            getDao().deleteInBatch(entities);
        }
    }

    @Override
    public void deleteAllInBatch(Class<T> clazz) {
        if (!isCanLogicDel(clazz)) {
            getDao().deleteAllInBatch();
        }
    }

    @Override
    public Optional<T> getOne(Long id) {
        return Optional.ofNullable(getDao().getOne(id));
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getDao().findAll(pageable);
    }

    @Override
    public Optional<T> findOne(Long id) {
        return Optional.ofNullable(getDao().findOne(id));
    }

    @Override
    public boolean exists(Long id) {
        return getDao().exists(id);
    }

    @Override
    public long count() {
        return getDao().count();
    }

    @Override
    public void delete(Class<T> clazz, Long id) {
        logicDel(clazz, id);
    }

    @Override
    public void delete(T entity) {
        this.logicDel(entity);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        this.logicDel(entities);
    }

    @Override
    public void deleteAll(Class<T> clazz) {
        if (!isCanLogicDel(clazz)) {
            getDao().deleteAll();
        }
    }

    private boolean isCanLogicDel(T entity) {
        return (entity instanceof CanLogicDelDomain);
    }

    private boolean isCanLogicDel(Class<T> clazz) {
        return CanLogicDelDomain.class.isAssignableFrom(clazz);
    }

    private void logicDel(Class<T> clazz, Long id) {
        if (isCanLogicDel(clazz)) {
            Optional<T> optional = this.findOne(id);
            if (!optional.isPresent()) {
                throw new IllegalArgumentException("can not find a domain with the id");
            }
            try {
                T entity = optional.get();
                Method method = entity.getClass().getMethod("setDel", Boolean.class);
                method.invoke(entity, true);
                this.update(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            getDao().delete(id);
        }
    }

    private void logicDel(T entity) {
        if (isCanLogicDel(entity)) {
            try {
                Method method = entity.getClass().getMethod("setDel", Boolean.class);
                method.invoke(entity, true);
                this.update(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            getDao().delete(entity);
        }
    }

    private void logicDel(Iterable<? extends T> entities) {
        for (T entity : entities) {
            logicDel(entity);
        }
    }
}
