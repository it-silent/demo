package com.hy.demo.service.stt.impl;

import com.hy.demo.common.enums.StatusEnum;
import com.hy.demo.common.utils.ExecutorUtils;
import com.hy.demo.dal.repositories.SttDORepository;
import com.hy.demo.dal.dataobject.SttDO;
import com.hy.demo.event.MyEvent;
import com.hy.demo.service.stt.SttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.*;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * SttServiceImpl
 *
 * @author silent
 * @date 2018/4/22
 */
@Service
public class SttServiceImpl implements SttService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SttDORepository sttDORepository;


    @Override
    public Long create(String creator, String name, String leaderId, String masterId) {
        SttDO sttDO = new SttDO();
        sttDO.setGmtCreate(new Date());
        sttDO.setGmtModified(new Date());
        sttDO.setCreator(creator);
        sttDO.setModifier(creator);
        sttDO.setName(name);
        sttDO.setType("type");
        sttDO.setLeaderId(leaderId);
        sttDO.setLeaderNick(leaderId);
        sttDO.setMasterId(masterId);
        sttDO.setMasterNick(masterId);
        sttDO.setStatus(StatusEnum.NORMAL.value);
        sttDORepository.save(sttDO);
        return sttDO.getId();
    }

    @Override
    public List<SttDO> findAll() {
        List<SttDO> sttDOS = sttDORepository.findAll(new Sort(Sort.Direction.DESC, "gmtModified"));
        return sttDOS;
    }

    @Override
    public SttDO findById(Long id) {
//        sttDORepository.findAll(PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "gmtCreate")));

        Optional<SttDO> result = sttDORepository.findById(id);
        SttDO sttDO = result.isPresent() ? result.get() : null;

        ExecutorUtils.execute(() ->
            applicationContext.publishEvent(new MyEvent(applicationContext, result.isPresent() ? result.get() : null))
        );
        return sttDO;
    }

    @Override
    public List<SttDO> findAllByPageAndSort(int page, int size, Sort sort) {
        Page<SttDO> pages = sttDORepository.findAll(PageRequest.of(page, size, sort));
        return pages.getContent();
    }

    @Override
    public void update(Long id, String name, String masterId, String leaderId) {
        Optional<SttDO> optional = sttDORepository.findById(id);
        SttDO sttDO = optional.isPresent() ? optional.get() : null;
        Assert.notNull(sttDO, "数据不存在");

        sttDO.setName(name);
        sttDO.setMasterId(masterId);
        sttDO.setMasterNick(masterId);
        sttDO.setLeaderId(leaderId);
        sttDO.setLeaderNick(leaderId);
        sttDORepository.save(sttDO);
    }

    @Override
    public void delete(Long id) {
        Assert.notNull(id, "id must not be null");
        sttDORepository.deleteById(id);
    }


}
