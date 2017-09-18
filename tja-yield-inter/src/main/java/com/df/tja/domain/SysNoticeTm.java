package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* SysNoticeTM.java
*
* 2017年9月18日 9:29:02
*
* 2016 上海一勤信息技术有限公司-版权所有 
*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.df.framework.base.domain.BaseDomain;

/**
 * <p>SysNoticeTm </p>
 * 
 * <p>描述：公告管理表 </p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 9:29:02</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */

@Entity
@Table(name = "SYS_NOTICE_TM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysNoticeTm extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -4567022400455803169L;

    /** 属性：公告类型。1000：有效期内按指定方式公告；2000：有效期内每天公告 */
    private java.lang.String noticeType;

    /** 属性：公告主题 */
    private java.lang.String noticeTitle;

    /** 属性：公告内容 */
    private String noticeContent;

    /** 属性：有效期始 */
    private java.util.Date startDate;

    /** 属性：有效期止 */
    private java.util.Date endDate;

    /** 属性：公告方式。1000：按周；2000：按月；3000：按季；4000：按年 */
    private java.lang.String noticeWay;

    /** 属性：指定月。按季、按年时指定的月数 */
    private Integer month;

    /** 属性：指定天。按周、按月、按季、按年时指定有天数 */
    private Integer day;

    /** 属性：是否删除。1：是；0：否 */
    private Boolean delFlag;

    /**
     * <p> 属性：noticeType的Getter方法. </p>
     * 
     * @return 返回公告类型属性的值
     */
    @Column(name = "NOTICE_TYPE")
    public java.lang.String getNoticeType() {
        return noticeType;
    }

    /**
     * <p> 属性公告类型的Setter方法. </p>
     * 
     * @param noticeType 为属性noticeType设置的值
     */
    public void setNoticeType(java.lang.String noticeType) {
        this.noticeType = noticeType;
    }

    /**
     * <p> 属性：noticeTitle的Getter方法. </p>
     * 
     * @return 返回公告主题属性的值
     */
    @Column(name = "NOTICE_TITLE")
    public java.lang.String getNoticeTitle() {
        return noticeTitle;
    }

    /**
     * <p> 属性公告主题的Setter方法. </p>
     * 
     * @param noticeTitle 为属性noticeTitle设置的值
     */
    public void setNoticeTitle(java.lang.String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    /**
     * <p> 属性：noticeContent的Getter方法. </p>
     * 
     * @return 返回公告内容属性的值
     */
    @Column(name = "NOTICE_CONTENT")
    public String getNoticeContent() {
        return noticeContent;
    }

    /**
     * <p> 属性公告内容的Setter方法. </p>
     * 
     * @param noticeContent 为属性noticeContent设置的值
     */
    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    /**
     * <p> 属性：startDate的Getter方法. </p>
     * 
     * @return 返回有效期始属性的值
     */
    @Column(name = "START_DATE")
    public java.util.Date getStartDate() {
        return startDate;
    }

    /**
     * <p> 属性有效期始的Setter方法. </p>
     * 
     * @param startDate 为属性startDate设置的值
     */
    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    /**
     * <p> 属性：endDate的Getter方法. </p>
     * 
     * @return 返回有效期止属性的值
     */
    @Column(name = "END_DATE")
    public java.util.Date getEndDate() {
        return endDate;
    }

    /**
     * <p> 属性有效期止的Setter方法. </p>
     * 
     * @param endDate 为属性endDate设置的值
     */
    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    /**
     * <p> 属性：noticeWay的Getter方法. </p>
     * 
     * @return 返回公告方式属性的值
     */
    @Column(name = "NOTICE_WAY")
    public java.lang.String getNoticeWay() {
        return noticeWay;
    }

    /**
     * <p> 属性公告方式的Setter方法. </p>
     * 
     * @param noticeWay 为属性noticeWay设置的值
     */
    public void setNoticeWay(java.lang.String noticeWay) {
        this.noticeWay = noticeWay;
    }

    /**
     * <p> 属性：month的Getter方法. </p>
     * 
     * @return 返回指定月属性的值
     */
    @Column(name = "MONTH")
    public Integer getMonth() {
        return month;
    }

    /**
     * <p> 属性指定月的Setter方法. </p>
     * 
     * @param month 为属性month设置的值
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * <p> 属性：day的Getter方法. </p>
     * 
     * @return 返回指定天属性的值
     */
    @Column(name = "DAY")
    public Integer getDay() {
        return day;
    }

    /**
     * <p> 属性指定天的Setter方法. </p>
     * 
     * @param day 为属性day设置的值
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * <p> 属性：delFlag的Getter方法. </p>
     * 
     * @return 返回是否删除属性的值
     */
    @Column(name = "DEL_FLAG")
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * <p> 属性是否删除的Setter方法. </p>
     * 
     * @param delFlag 为属性delFlag设置的值
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * <p> 属性：creator的Getter方法. </p>
     * 
     * @return 返回登记人属性的值
     */
    @Column(name = "CREATOR")
    public java.lang.String getCreator() {
        return creator;
    }

    /**
     * <p> 属性：createDate的Getter方法. </p>
     * 
     * @return 返回登记时间属性的值
     */
    @Column(name = "CREATE_DATE")
    public java.util.Date getCreateDate() {
        return createDate;
    }

    /**
     * <p> 属性：modifier的Getter方法. </p>
     * 
     * @return 返回修改人属性的值
     */
    @Column(name = "MODIFIER")
    public java.lang.String getModifier() {
        return modifier;
    }

    /**
     * <p> 属性：modifyDate的Getter方法. </p>
     * 
     * @return 返回修改时间属性的值
     */
    @Column(name = "MODIFY_DATE")
    public java.util.Date getModifyDate() {
        return modifyDate;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() != getClass()) {
            return false;
        }
        SysNoticeTm obj1 = (SysNoticeTm) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}