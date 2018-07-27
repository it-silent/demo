package com.hy.demo.event;

import com.hy.demo.dal.dataobject.SttDO;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * MyEvent
 *
 * @author silent
 * @date 2018/4/24
 */
@Getter
public class MyEvent extends ApplicationContextEvent {

    private SttDO sttDO;

    public MyEvent(ApplicationContext source, SttDO sttDO) {
        super(source);
        this.sttDO = sttDO;
    }
}
