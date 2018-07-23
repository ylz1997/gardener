import com.jw.base.GeneralException;
import com.jw.kids.bean.TKids;
import com.jw.kids.service.KidsStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.TimeUnit;

/**
 * @author jw
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
//@ContextConfiguration
//@EnableAutoConfiguration
public class RedisTest{
    @Test
    public void test(){

    }
/*    private MockMvc mvc;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private KidsStudentService service;
    @Test
    public void testSaveCache() throws GeneralException {
        ValueOperations<String, TKids> operations = redisTemplate.opsForValue();
        TKids kids = service.listKids(new TKids(),0,1).get(0);
        // 插入缓存
        operations.set("testRedis", kids, 100000, TimeUnit.SECONDS);
    }*/
}
