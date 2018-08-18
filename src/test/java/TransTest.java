import com.jw.base.Constants;
import com.jw.base.GeneralException;
import com.jw.kids.bean.TClass;
import com.jw.kids.bean.TClassLogDetail;
import com.jw.kids.bean.TClassLogVO;
import com.jw.kids.service.KidsClassLogSV;
import com.jw.kids.service.impl.KidsLogSVImpl;
import com.mysql.jdbc.AssertionFailedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jw
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class TransTest {
    @Autowired
    private KidsClassLogSV kidsLogSV;
    @Test
    public void testAdd() throws GeneralException {
        TClassLogVO tClassLogVO = new TClassLogVO();
        List<TClassLogDetail> kidsList = new ArrayList<>();
        TClassLogDetail detail1 = new TClassLogDetail();
        detail1.setLogObjId(9999991L);
        detail1.setLogType(Constants.KIDS_LOG_OBJ_TYPE_CD.LOG_OBJ_TYPE_KIDS);
        kidsList.add(detail1);
        tClassLogVO.setKidsList(kidsList);

        List<TClassLogDetail> teacherList = new ArrayList<>();
        TClassLogDetail detail2 = new TClassLogDetail();
        detail2.setLogObjId(9999991L);
        detail2.setLogType(Constants.KIDS_LOG_OBJ_TYPE_CD.LOG_OBJ_TYEP_TEACHER);
        teacherList.add(detail2);
        tClassLogVO.setTeacherList(teacherList);
        kidsLogSV.add(tClassLogVO);

    }
}
