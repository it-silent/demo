package com.hy.demo.dal.dataobject.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * BaseDO
 *
 * @author yuhaiyang
 * @date 2018/6/4
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@MappedSuperclass
public class BaseDO implements Serializable {

    private static final long serialVersionUID = -7122872911435630187L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    /**
     * 创建时间
     */
    @Column(columnDefinition = "datetime not null comment '创建时间' ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    @Column(columnDefinition = "datetime not null comment '最后修改时间' ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

    /**
     * 创建者
     */
    @Column(columnDefinition = "varchar(255) comment '创建者' ")
    private String creator;

    /**
     * 最后修改者
     */
    @Column(columnDefinition = "varchar(255) comment '最后修改者' ")
    private String modifier;

    /**
     * 数据逻辑状态
     */
    @Column(columnDefinition = "tinyint(1) comment '状态' ")
    protected Integer status;
}
