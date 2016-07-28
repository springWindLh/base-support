package daoImpl;

import dao.IBaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by lh on 2016/3/7.
 */
@NoRepositoryBean
public class BaseDaoImpl<T> extends SimpleJpaRepository<T, Long> implements IBaseDao<T> {
    public BaseDaoImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    private static final String PARAMS_NUMBERS_ERROR = "参数个数错误";

    @Override
    public Optional<T> saveEntity(T entity) {
        return Optional.ofNullable(save(entity));
    }

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

    private Specification getWhereSpecification(final Object... fieldsAndValues) {
        this.CheckFieldsAndValues();
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Object> predicates = new ArrayList<>();
                for (int i = 0; i < fieldsAndValues.length; i += 2) {
                    Predicate predicate = cb.equal(root.get(fieldsAndValues[i].toString()).as(String.class), fieldsAndValues[i + 1]);
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
}
