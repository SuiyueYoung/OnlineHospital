package com.rectangle.onlinehospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("checkitem")
public class CheckItem {
    @TableId(value = "ciId", type = IdType.AUTO)
    private Integer ciID;

    @TableField("ciName")
    private String ciName;

    @TableField("ciContent")
    private String ciContent;

    @TableField("meaning")
    private String meaning;

    @TableField("remarks")
    private String remark;
}
