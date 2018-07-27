package com.hy.demo.base;

import com.hy.demo.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * BaseTest
 *          base
 *
 * @author  Yu.HaiYang
 * @date    2018/7/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class BaseTest {
}
