package com.hy.demo.service.stt;

import com.hy.demo.dal.dataobject.SttDO;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * SttService
 *
 * @author silent
 * @date 2018/4/22
 */
public interface SttService {

    Long create(String creator, String name, String leaderId, String masterId);

    List<SttDO>findAll();

    SttDO findById(Long id);

    List<SttDO> findAllByPageAndSort(int page, int size, Sort sort);

    void update(Long id, String name, String masterId, String leaderId);

    void delete(Long id);
}
