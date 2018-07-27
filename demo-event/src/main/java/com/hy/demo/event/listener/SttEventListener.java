package com.hy.demo.event.listener;

import com.hy.demo.common.utils.TimeUtils;
import com.hy.demo.event.MyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * SttEventListener
 *
 * @author Yu.HaiYang
 * @date 2018/6/25
 */
@Component
public class SttEventListener {

    @EventListener
    public void onApplicationEvent(MyEvent event) {
        System.err.println("applicationListener doing " + TimeUtils.format(new Date()));
        System.err.println(String.format("当前查询sttID: %s， sttDO: %s", event.getSttDO().getId(), event.getSttDO()));

    }
}
