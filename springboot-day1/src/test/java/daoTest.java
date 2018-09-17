import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2018/8/23 0023.
 */
public class daoTest extends serviceTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void testDao(){
        userDao.delete(121);
        List<User> users = userDao.queryAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
