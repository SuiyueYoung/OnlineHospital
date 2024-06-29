package com.rectangle.onlinehospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("setmeal")
public class SetMeal {
    @TableId(value = "smId", type = IdType.AUTO)
    private Integer smID;

    @TableField(value = "name")
    private String name;

    @TableField(value = "type")
    private Integer type;

    @TableField(value = "price")
    private Integer price;
}
