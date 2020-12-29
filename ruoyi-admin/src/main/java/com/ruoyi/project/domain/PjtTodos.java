package com.ruoyi.project.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务对象 pjt_todos
 * 
 * @author ruoyi
 * @date 2020-12-24
 */
public class PjtTodos extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer todoId;

    /** 责任人ID */
    @Excel(name = "责任人ID")
    private Integer assignee;

    /** 截止时间 */
    @Excel(name = "截止时间")
    private Long dealline;

    /** 优先级 */
    @Excel(name = "优先级")
    private Integer priority;

    /** 所属项目id */
    @Excel(name = "所属项目id")
    private Long pjtId;

    /** 所属平台产品id */
    @Excel(name = "所属平台产品id")
    private Long productId;

    /** 任务标题 */
    @Excel(name = "任务标题")
    private String todoTitle;

    /** 任务描述 */
    @Excel(name = "任务描述")
    private String todoDesc;

    /** 上传附件的id集合 */
    @Excel(name = "上传附件的id集合")
    private String fileIds;


    /** 删除人id */
    @Excel(name = "删除人id")
    private Long deleteBy;

    /** 删除人 */
    @Excel(name = "删除人")
    private Long deleteTime;

    /** 状态，0未完成，1进行中，2已完成，3已删除 */
    @Excel(name = "状态，0未完成，1进行中，2已完成，3已删除")
    private Integer todoStatus;

    /** 系统提醒时间设定 */
    @Excel(name = "系统提醒时间设定")
    private Long remindTime;

    /** 1有星标置顶，0无星标置顶 */
    @Excel(name = "1有星标置顶，0无星标置顶")
    private Integer hasStar;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDel;

    /** 任务类型 */
    @Excel(name = "任务类型")
    private Integer todoType;

    public void setTodoId(Integer todoId) 
    {
        this.todoId = todoId;
    }

    public Integer getTodoId() 
    {
        return todoId;
    }
    public void setAssignee(Integer assignee) 
    {
        this.assignee = assignee;
    }

    public Integer getAssignee() 
    {
        return assignee;
    }
    public void setDealline(Long dealline) 
    {
        this.dealline = dealline;
    }

    public Long getDealline() 
    {
        return dealline;
    }
    public void setPriority(Integer priority) 
    {
        this.priority = priority;
    }

    public Integer getPriority() 
    {
        return priority;
    }

    public void setPjtId(Long pjtId) 
    {
        this.pjtId = pjtId;
    }

    public Long getPjtId() 
    {
        return pjtId;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setTodoTitle(String todoTitle) 
    {
        this.todoTitle = todoTitle;
    }

    public String getTodoTitle() 
    {
        return todoTitle;
    }
    public void setTodoDesc(String todoDesc) 
    {
        this.todoDesc = todoDesc;
    }

    public String getTodoDesc() 
    {
        return todoDesc;
    }
    public void setFileIds(String fileIds) 
    {
        this.fileIds = fileIds;
    }

    public String getFileIds() 
    {
        return fileIds;
    }

    public void setDeleteBy(Long deleteBy) 
    {
        this.deleteBy = deleteBy;
    }

    public Long getDeleteBy() 
    {
        return deleteBy;
    }
    public void setDeleteTime(Long deleteTime) 
    {
        this.deleteTime = deleteTime;
    }

    public Long getDeleteTime() 
    {
        return deleteTime;
    }
    public void setTodoStatus(Integer todoStatus) 
    {
        this.todoStatus = todoStatus;
    }

    public Integer getTodoStatus() 
    {
        return todoStatus;
    }
    public void setRemindTime(Long remindTime) 
    {
        this.remindTime = remindTime;
    }

    public Long getRemindTime() 
    {
        return remindTime;
    }
    public void setHasStar(Integer hasStar) 
    {
        this.hasStar = hasStar;
    }

    public Integer getHasStar() 
    {
        return hasStar;
    }
    public void setIsDel(Integer isDel) 
    {
        this.isDel = isDel;
    }

    public Integer getIsDel() 
    {
        return isDel;
    }
    public void setTodoType(Integer todoType) 
    {
        this.todoType = todoType;
    }

    public Integer getTodoType() 
    {
        return todoType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("todoId", getTodoId())
            .append("assignee", getAssignee())
            .append("dealline", getDealline())
            .append("priority", getPriority())
            .append("pjtId", getPjtId())
            .append("productId", getProductId())
            .append("todoTitle", getTodoTitle())
            .append("todoDesc", getTodoDesc())
            .append("fileIds", getFileIds())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("deleteBy", getDeleteBy())
            .append("deleteTime", getDeleteTime())
            .append("todoStatus", getTodoStatus())
            .append("remindTime", getRemindTime())
            .append("hasStar", getHasStar())
            .append("isDel", getIsDel())
            .append("todoType", getTodoType())
            .toString();
    }
}
