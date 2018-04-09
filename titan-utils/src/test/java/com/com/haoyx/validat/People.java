package com.com.haoyx.validat;


import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by haoyuexun on 2018/4/2.
 */
public class People {
    //在First分组时，判断不能为空
    @NotEmpty(groups={First.class})
    private String id;

    //name字段不为空，且长度在3-8之间
    @NotEmpty
    @Size(min=3,max=8)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
