import com.baizhi.App;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Administrator on 2018/8/23 0023.
 */
@RunWith(value = SpringRunner.class)  //启动springboot环境
@SpringBootTest(classes = App.class) //指定入口类
public class serviceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testService(){
        List<User> users = userService.queryAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void test1(){
        System.out.println((2<<3)+"*****************************");
    }
    @Test
    public void test2(){
        final StringBuffer s=new StringBuffer("nihao");
        s.append("lulu");
        System.out.println(s);
    }

    @Test
    public void test3(){
        
    }
}
