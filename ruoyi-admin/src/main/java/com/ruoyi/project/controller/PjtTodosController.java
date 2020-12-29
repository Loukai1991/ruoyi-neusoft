package com.ruoyi.project.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.project.domain.PjtTodos;
import com.ruoyi.project.service.IPjtTodosService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务Controller
 * 
 * @author ruoyi
 * @date 2020-12-24
 */
@RestController
@RequestMapping("/project/todos")
public class PjtTodosController extends BaseController
{
    @Autowired
    private IPjtTodosService pjtTodosService;

    /**
     * 查询任务列表
     */
//    @PreAuthorize("@ss.hasPermi('project:todos:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjtTodos pjtTodos)
    {
        startPage();
        List<PjtTodos> list = pjtTodosService.selectPjtTodosList(pjtTodos);
        return getDataTable(list);
    }

    /**
     * 导出任务列表
     */
    @PreAuthorize("@ss.hasPermi('project:todos:export')")
    @Log(title = "任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PjtTodos pjtTodos)
    {
        List<PjtTodos> list = pjtTodosService.selectPjtTodosList(pjtTodos);
        ExcelUtil<PjtTodos> util = new ExcelUtil<PjtTodos>(PjtTodos.class);
        return util.exportExcel(list, "todos");
    }

    /**
     * 获取任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:todos:query')")
    @GetMapping(value = "/{todoId}")
    public AjaxResult getInfo(@PathVariable("todoId") Integer todoId)
    {
        return AjaxResult.success(pjtTodosService.selectPjtTodosById(todoId));
    }

    /**
     * 新增任务
     */
    @PreAuthorize("@ss.hasPermi('project:todos:add')")
    @Log(title = "任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PjtTodos pjtTodos)
    {
        return toAjax(pjtTodosService.insertPjtTodos(pjtTodos));
    }

    /**
     * 修改任务
     */
    @PreAuthorize("@ss.hasPermi('project:todos:edit')")
    @Log(title = "任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PjtTodos pjtTodos)
    {
        return toAjax(pjtTodosService.updatePjtTodos(pjtTodos));
    }

    /**
     * 删除任务
     */
    @PreAuthorize("@ss.hasPermi('project:todos:remove')")
    @Log(title = "任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{todoIds}")
    public AjaxResult remove(@PathVariable Integer[] todoIds)
    {
        return toAjax(pjtTodosService.deletePjtTodosByIds(todoIds));
    }
}
