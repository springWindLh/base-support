package dao;

import model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by lh on 2016/2/27.
 */
public interface IUserDao extends PagingAndSortingRepository<User,Long>,JpaSpecificationExecutor{
    User findByName(String name);
    User findByNameLike(String name);
    @Query("from User  where name = ?1 and password = ?2")
    User findByNameAndPwd(String name, String password);
    User findBySignatureLike(String signature);
}
