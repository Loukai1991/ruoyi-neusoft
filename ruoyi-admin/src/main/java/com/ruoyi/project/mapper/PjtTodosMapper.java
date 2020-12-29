package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.PjtTodos;

/**
 * 任务Mapper接口
 * 
 * @author ruoyi
 * @date 2020-12-24
 */
public interface PjtTodosMapper 
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
     * 删除任务
     * 
     * @param todoId 任务ID
     * @return 结果
     */
    public int deletePjtTodosById(Integer todoId);

    /**
     * 批量删除任务
     * 
     * @param todoIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePjtTodosByIds(Integer[] todoIds);
}
