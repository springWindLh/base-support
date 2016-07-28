package dao;

import model.School;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lh on 2016/2/29.
 */
public interface ISchoolDao extends CrudRepository<School,Long> {
}
