import com.baizhi.App;
import com.baizhi.util.RandomUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2018/9/13 0013.
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = App.class)
public class PasswordTest {
    @Test
    public void tstet(){
        String randoms = RandomUtil.getRandoms();
        System.out.println(randoms);
    }
   // Md5Hash()
}
