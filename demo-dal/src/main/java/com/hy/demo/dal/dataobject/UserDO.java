package com.hy.demo.dal.dataobject;

import com.hy.demo.dal.dataobject.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * UserDO
 *
 * @author yuhaiyang
 * @date 2018/5/29
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class UserDO extends BaseDO {

    private static final long serialVersionUID = -9035310331638004739L;

    @Column(columnDefinition = "varchar(255) comment '用户ID'")
    private String empId;

    @Column(columnDefinition = "varchar(255) comment '用户姓名'")
    private String name;

    @Column(columnDefinition = "varchar(255) comment '用户姓名(英文)'")
    private String nameEn;

    @Column(columnDefinition = "varchar(255) comment '用户昵称'")
    private String nickName;

    @Column(columnDefinition = "varchar(255) comment '用户昵称(英文)'")
    private String nickNameEn;

    @Column(columnDefinition = "varchar(255) comment '部门ID'")
    private String deptId;

    @Column(columnDefinition = "varchar(255) comment '部门名称'")
    private String deptName;

    @Column(columnDefinition = "varchar(255) comment '部门名称(英文)'")
    private String deptNameEn;

    @Column(columnDefinition = "varchar(255) comment '手机号'")
    private String mobile;

    @Column(columnDefinition = "varchar(255) comment '等级'")
    private String level;

    @Column(columnDefinition = "varchar(255) comment ''")
    private String manager;

    @Column(columnDefinition = "tinyint(1) comment '是否在职'")
    private Boolean inOffice;

    @Column(columnDefinition = "varchar(255) comment '用户类型'")
    private String empType;

    @Column(columnDefinition = "varchar(255) comment '用户邮箱'")
    private String email;

    @Column(columnDefinition = "varchar(255) comment 'text'")
    private String text;
}
