package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.PjtTodos;

/**
 * 任务Service接口
 * 
 * @author ruoyi
 * @date 2020-12-24
 */
public interface IPjtTodosService 
{
    /**
     * 查询任务
     * 
     * @param todoId 任务ID
     * @return 任务
     */
    public PjtTodos selectPjtTodosById(Integer todoId);

    /**
     * 查询任务列表
     * 
     * @param pjtTodos 任务
     * @return 任务集合
     */
    public List<PjtTodos> selectPjtTodosList(PjtTodos pjtTodos);

    /**
     * 新增任务
     * 
     * @param pjtTodos 任务
     * @return 结果
     */
    public int insertPjtTodos(PjtTodos pjtTodos);

    /**
     * 修改任务
     * 
     * @param pjtTodos 任务
     * @return 结果
     */
    public int updatePjtTodos(PjtTodos pjtTodos);

    /**
     * 批量删除任务
     * 
     * @param todoIds 需要删除的任务ID
     * @return 结果
     */
    public int deletePjtTodosByIds(Integer[] todoIds);

    /**
     * 删除任务信息
     * 
     * @param todoId 任务ID
     * @return 结果
     */
    public int deletePjtTodosById(Integer todoId);
}
