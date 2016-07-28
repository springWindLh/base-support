package serviceImpl;

import dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import service.IBaseService;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * Created by lh on 2016/3/12.
 */
public class BaseServiceImpl<T> extends SimpleJpaRepository<T, Long> implements IBaseService<T> {
    @Autowired
    private IBaseDao IBaseDao;

    public BaseServiceImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public Optional<T> add(T entity) {
        return IBaseDao.saveEntity(entity);
    }

    @Override
    public Optional<T> update(T entity) {
        return IBaseDao.saveEntity(entity);
    }
}
