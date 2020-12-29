package com.ruoyi.project.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.PjtTodosMapper;
import com.ruoyi.project.domain.PjtTodos;
import com.ruoyi.project.service.IPjtTodosService;

/**
 * 任务Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-12-24
 */
@Service
public class PjtTodosServiceImpl implements IPjtTodosService 
{
    @Autowired
    private PjtTodosMapper pjtTodosMapper;

    /**
     * 查询任务
     * 
     * @param todoId 任务ID
     * @return 任务
     */
    @Override
    public PjtTodos selectPjtTodosById(Integer todoId)
    {
        return pjtTodosMapper.selectPjtTodosById(todoId);
    }

    /**
     * 查询任务列表
     * 
     * @param pjtTodos 任务
     * @return 任务
     */
    @Override
    public List<PjtTodos> selectPjtTodosList(PjtTodos pjtTodos)
    {
        return pjtTodosMapper.selectPjtTodosList(pjtTodos);
    }

    /**
     * 新增任务
     * 
     * @param pjtTodos 任务
     * @return 结果
     */
    @Override
    public int insertPjtTodos(PjtTodos pjtTodos)
    {
        pjtTodos.setCreateTime(DateUtils.getNowDate());
        return pjtTodosMapper.insertPjtTodos(pjtTodos);
    }

    /**
     * 修改任务
     * 
     * @param pjtTodos 任务
     * @return 结果
     */
    @Override
    public int updatePjtTodos(PjtTodos pjtTodos)
    {
        pjtTodos.setUpdateTime(DateUtils.getNowDate());
        return pjtTodosMapper.updatePjtTodos(pjtTodos);
    }

    /**
     * 批量删除任务
     * 
     * @param todoIds 需要删除的任务ID
     * @return 结果
     */
    @Override
    public int deletePjtTodosByIds(Integer[] todoIds)
    {
        return pjtTodosMapper.deletePjtTodosByIds(todoIds);
    }

    /**
     * 删除任务信息
     * 
     * @param todoId 任务ID
     * @return 结果
     */
    @Override
    public int deletePjtTodosById(Integer todoId)
    {
        return pjtTodosMapper.deletePjtTodosById(todoId);
    }
}
