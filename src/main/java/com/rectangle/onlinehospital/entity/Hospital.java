package com.rectangle.onlinehospital.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("hospital")
public class Hospital {
    @TableId(value = "hpId", type = IdType.AUTO)
    private Integer hpID;

    @TableField("name")
    private String name;

    @TableField("picture")
    private String picture;

    @TableField("telephone")
    private String telephone;

    @TableField("address")
    private String address;

    @TableField("businessHours")
    private String businessHours;

    @TableField("deadline")
    private String deadline;

    @TableField("rule")
    private String rule;

    @TableField("state")
    private Integer state;
}
