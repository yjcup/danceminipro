package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.domain.DanceUser;
import com.ruoyi.system.service.IDanceUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户管理Controller
 * 
 * @author ruoyi
 * @date 2024-02-01
 */
@RestController
@RequestMapping("/dance/user")
public class DanceUserController extends BaseController
{
    @Autowired
    private IDanceUserService danceUserService;

    /**
     * 查询用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('dance:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(DanceUser danceUser)
    {
        startPage();
        List<DanceUser> list = danceUserService.selectDanceUserList(danceUser);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('dance:user:export')")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DanceUser danceUser)
    {
        List<DanceUser> list = danceUserService.selectDanceUserList(danceUser);
        ExcelUtil<DanceUser> util = new ExcelUtil<DanceUser>(DanceUser.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('dance:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(danceUserService.selectDanceUserById(id));
    }

    /**
     * 新增用户管理
     */
    @PreAuthorize("@ss.hasPermi('dance:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DanceUser danceUser)
    {
        return toAjax(danceUserService.insertDanceUser(danceUser));
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('dance:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DanceUser danceUser)
    {
        return toAjax(danceUserService.updateDanceUser(danceUser));
    }

    /**
     * 删除用户管理
     */
    @PreAuthorize("@ss.hasPermi('dance:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(danceUserService.deleteDanceUserByIds(ids));
    }
}
