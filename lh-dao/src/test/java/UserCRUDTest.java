import dao.ISchoolDao;
import dao.IUserDao;
import model.School;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lh on 2016/2/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml"})
@Transactional
public class UserCRUDTest {
    @Autowired
    private IUserDao IUserDao;
    @Autowired
    private ISchoolDao ISchoolDao;


    @Rollback(false)
    @Test
    public void testSaveUser() {
        School school = ISchoolDao.findOne(1);
        for (int i = 0; i < 25; i++) {
            User user = new User();
            user.setName("user---" + i);
            user.setPassword("123");
            user.setPhone("18328388471");
            user.setEmail("qq@qq.com");
            user.setSchool(school);
            IUserDao.save(user);
        }
    }

    @Rollback(false)
    @Test
    public void testDeleteUser() {
        User user = IUserDao.findOne(27);
        IUserDao.delete(user);
    }

    @Rollback(false)
    @Test
    public void testUpdateUser() {
        User user = IUserDao.findOne(27);
        user.setName("updateUserName");
        IUserDao.save(user);
    }

    @Rollback(false)
    @Test
    public void testOtherUser() {
        School school = ISchoolDao.findOne(2);
        long start = System.currentTimeMillis();
//        for (int i = 1; i < 10000; i++) {
//            User user = new User();
//            user.setName("user---" + i);
//            user.setSchool(school);
//            user.setEmail("qq@qq.com");
//            user.setPhone("18328388471");
//            user.setPassword("123");
//            user.setSignature("学校概况1956年3月15日，国务院批准建立成都地质勘探学院。同年3月27日，高等教育部和地质部联合发文，以重庆大学地质系、西北大学和南京大学地质系的工科部分为基础同时抽调北京地质学院、长春地质勘探学院部分干部教师组建成都地质勘探学院，建校当年即开始招收本科生。学校建校后陆续部分或成建制的迁入了原北京地质学院石油系和二系部分、三系整体。1960年，学校开始招收研究生。1983年，学校成为国家恢复学位制度后首批招收博士生的高校。成都地质勘探学院1958年更名为成都地质学院，1993年更名为成都理工");
//            IUserDao.save(user);
//        }
//        User user = IUserDao.findByName("newUser---7755");
        User user = IUserDao.findBySignatureLike("%gnat%");
        System.out.println(user.getSignature());
//        List<User> users = (List<User>)IUserDao.findAll();
//        System.out.println(users.get(6699).getSignature());
        long end = System.currentTimeMillis();
        System.out.println("time:" + (end - start) / 1000);
    }
}
