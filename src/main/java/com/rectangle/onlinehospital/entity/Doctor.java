package com.rectangle.onlinehospital.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("doctor")
public class Doctor {
    @TableId(value = "docId", type = IdType.AUTO)
    private Integer docID;

    @TableField("docCode")
    private String docCode;

    @TableField("realName")
    private String realName;

    @TableField("password")
    private String password;

    @TableField("sex")
    private Integer sex;

    @TableField("deptno")
    private Integer deptno;

    @TableField("hpId")
    private Integer hospitalID;
}
