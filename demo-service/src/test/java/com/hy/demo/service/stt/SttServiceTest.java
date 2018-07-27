package com.hy.demo.service.stt;

import com.hy.demo.common.utils.JacksonUtils;
import com.hy.demo.dal.dataobject.SttDO;
import com.hy.demo.service.base.BaseServiceTest;
import net.minidev.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

/**
 * SttServiceTest
 *
 * @author silent
 * @date 2018/5/22
 */
@Transactional
public class SttServiceTest extends BaseServiceTest {

    @Autowired
    private SttService sttService;

    @Test
    public void create() {
        Long id = sttService.create("若朴", "泪~痕", "0417", "0417", -1);
        System.err.println(id);
    }

    @Test
    public void find() {
        SttDO sttDO = sttService.findById(1L);
        System.err.println(sttDO);
    }

    @Test
    public void findByPageAndSort() {
        List<SttDO> sttDO = sttService.findAllByPageAndSort(0, 100, Sort.by(Sort.Direction.DESC, "gmtCreate"));
        System.err.println(JacksonUtils.writeValue(sttDO));
    }
}
