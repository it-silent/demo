package com.hy.demo.stt;

import com.hy.demo.base.BaseTest;
import com.hy.demo.dal.dataobject.SttDO;
import org.junit.Ignore;
import org.junit.Test;

import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * SttControllerTest
 *           
 *
 * @author  Yu.HaiYang
 * @date    2018/7/27
 */
@Transactional
public class SttControllerTest extends BaseTest {

    private static final String baseUrl = "/stt";

    @Test
    public void find() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("name", "silent");

        Map<String, String> params = new HashMap<>();
        params.put("sex", "uik");
        params.put("light", "black");
        mvc.perform(get(baseUrl + "/list", params, headers))
                .andDo(print())
                .andExpect(jsonPath("$.success").value("true"));
    }

    @Ignore
    @Test
    public void save() throws Exception {
        SttDO stt = new SttDO();
        stt.setCreator("大海");
        stt.setName("凡所有相,皆是虚妄");
        stt.setLeaderId("leader-1");
        stt.setMasterId("master-1");
        mvc.perform(post(baseUrl + "/save", stt, null))
                .andDo(print())
                .andExpect(jsonPath("$.success").value("true"));
    }

    @Test
    public void update() throws Exception{
        SttDO stt = new SttDO();
        stt.setId(2L);
        stt.setName("凡所有相,皆是虚妄,若见诸相非相,即见如来");
        stt.setLeaderId("leader-1");
        stt.setMasterId("master-1");
        stt.setCreator("大海");
        mvc.perform(put(baseUrl + "/update", stt, null))
                .andDo(print())
                .andExpect(jsonPath("$.success").value("true"));
    }

    @Test
    public void findOne() throws Exception{
        Long id = 2L;
        mvc.perform(get(baseUrl + "/info/" + id))
                .andDo(print())
                .andExpect(jsonPath("$.success").value("true"));
    }

    @Ignore
    @Test
    public void delete() throws Exception{
        Long id = 2L;
        mvc.perform(delete(baseUrl + "/delete/" + id))
                .andDo(print())
                .andExpect(jsonPath("$.success").value("true"));
    }
}
