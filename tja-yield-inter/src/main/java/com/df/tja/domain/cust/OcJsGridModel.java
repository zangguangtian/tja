/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain.cust
 *
 * OcJsGridModel.java
 * 
 * 2017年9月23日-下午2:30:55
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.domain.cust;

import java.io.Serializable;

/**
 * <p>OcJsGridModel</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月23日 下午2:30:55</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public class OcJsGridModel implements Serializable {

    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 2927967733534996653L;

    private String name;

    private String title;

    private String type;

    private String width;

    private boolean filtering;

    public OcJsGridModel() {
        super();
    }

    public OcJsGridModel(String name, String title, String type, String width, boolean filtering) {
        super();
        this.name = name;
        this.title = title;
        this.type = type;
        this.width = width;
        this.filtering = filtering;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public boolean isFiltering() {
        return filtering;
    }

    public void setFiltering(boolean filtering) {
        this.filtering = filtering;
    }

    @Override
    public String toString() {
        return "OcJsGridModel [name=" + name + ", title=" + title + ", type=" + type + ", width=" + width + "]";
    }
}
