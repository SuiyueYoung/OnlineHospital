package com.rectangle.onlinehospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("setmealdetailed")
public class SetMealDetailed {
    @TableId(value = "sdId", type = IdType.AUTO)
    private Integer sdID;

    @TableField("smId")
    private Integer smID;

    @TableField("ciId")
    private Integer ciID;
}
