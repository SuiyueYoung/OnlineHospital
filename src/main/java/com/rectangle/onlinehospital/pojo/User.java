package com.rectangle.onlinehospital.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("users")
public class User {
    @TableId(value = "userId")
    private String userID;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @TableField("password")
    private String password;

    @TableField("realName")
    private String realName;

    @TableField("sex")
    private Integer sex;

    @TableField("identityCard")
    private String identityCard;

    @TableField("birthday")
    private Date birthday;

    @TableField("userType")
    private Integer userType;
}
