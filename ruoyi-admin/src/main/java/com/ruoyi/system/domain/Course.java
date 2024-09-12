package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程信息管理对象 course
 * 
 * @author ruoyi
 * @date 2024-02-25
 */
public class Course extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String title;

    /** 课程详情页描述 */
    @Excel(name = "课程详情页描述")
    private String des;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 销量 */
    @Excel(name = "销量")
    private Long count;

    /** 课程分类 */
    @Excel(name = "课程分类")
    private String cate;

    /** 封面 */
    @Excel(name = "封面")
    private String img;

    /** 课程详情页图片 */
    @Excel(name = "课程详情页图片")
    private String imglist;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setDes(String des) 
    {
        this.des = des;
    }

    public String getDes() 
    {
        return des;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setCount(Long count) 
    {
        this.count = count;
    }

    public Long getCount() 
    {
        return count;
    }
    public void setCate(String cate) 
    {
        this.cate = cate;
    }

    public String getCate() 
    {
        return cate;
    }
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
    }
    public void setImglist(String imglist) 
    {
        this.imglist = imglist;
    }

    public String getImglist() 
    {
        return imglist;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("des", getDes())
            .append("price", getPrice())
            .append("count", getCount())
            .append("cate", getCate())
            .append("img", getImg())
            .append("imglist", getImglist())
            .append("createTime", getCreateTime())
            .toString();
    }
}
