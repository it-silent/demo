package com.hy.demo.dal.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hy.demo.dal.dataobject.base.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * SttDO
 *
 * @author silent
 * @date 2018/4/22
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "stt")
public class SttDO extends BaseDO {

    private static final long serialVersionUID = 1660894737773106173L;

    @Transient
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:ms")
    private LocalDateTime testTime;

    @Transient
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date testTime2;

    /**
     * 名称
     */
    @Column(columnDefinition = "varchar(255) comment '名称' ")
    private String name;

    /**
     * 类型
     */
    @Column(columnDefinition = "varchar(255) comment '类型' ")
    private String type;

    /**
     * 成员来源
     */
    @Column(columnDefinition = "varchar(255) comment '成员来源' ")
    private String memberSource;

    /**
     * 成员来源详情
     */
    @Column(columnDefinition = "varchar(255) comment '成员来源详情' ")
    private String memberSourceDetail;

    /**
     * 负责人
     */
    @Column(columnDefinition = "varchar(255) comment '负责人' ")
    private String leaderId;

    /**
     * 负责人昵称
     */
    @Column(columnDefinition = "varchar(255) comment '负责人昵称' ")
    private String leaderNick;

    /**
     * 管理员
     */
    @Column(columnDefinition = "varchar(255) comment '管理员' ")
    private String masterId;

    /**
     * 管理员昵称
     */
    @Column(columnDefinition = "varchar(255) comment '管理员昵称' ")
    private String masterNick;

    /**
     * SLA
     */
    @Column(columnDefinition = "bigint(20) comment 'SLA ID' ")
    private Long slaId;

    /**
     * 升级策略
     */
    @Column(columnDefinition = "varchar(255) comment '升级策略' ")
    private String upgradePolicy;

    /**
     * 升级路径
     */
    @Column(columnDefinition = "varchar(255) comment '升级路径' ")
    private String upgradePath;

    /**
     * 钉钉群
     */
    @Column(columnDefinition = "varchar(255) comment '钉钉群' ")
    private String dingGroup;

    /**
     * 值班表
     */
    @Column(columnDefinition = "bigint(20) comment '值班表' ")
    private Long dutyPlan;
}






