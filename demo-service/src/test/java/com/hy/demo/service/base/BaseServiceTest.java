package com.hy.demo.service.base;

import com.hy.demo.service.ApplicationTestForService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * baseServiceTest
 *
 * @author wb-yhy282733
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationTestForService.class})
public class BaseServiceTest {

    @Test
    public void run() {}
}
