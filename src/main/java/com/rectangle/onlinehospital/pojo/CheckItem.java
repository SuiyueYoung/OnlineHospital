package com.rectangle.onlinehospital.pojo;

import lombok.Data;

@Data
public class CheckItem {
    private Integer ciID;
    private String ciName;
    private String ciContent;
    private String meaning;
    private String remark;
}
