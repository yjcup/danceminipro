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
import com.ruoyi.system.domain.CourseDetail;
import com.ruoyi.system.service.ICourseDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程详情表Controller
 * 
 * @author ruoyi
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/dance/detail")
public class CourseDetailController extends BaseController
{
    @Autowired
    private ICourseDetailService courseDetailService;

    /**
     * 查询课程详情表列表
     */
//    @PreAuthorize("@ss.hasPermi('dance:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(CourseDetail courseDetail)
    {
        startPage();
        List<CourseDetail> list = courseDetailService.selectCourseDetailList(courseDetail);
        return getDataTable(list);
    }

    /**
     * 导出课程详情表列表
     */
//    @PreAuthorize("@ss.hasPermi('dance:detail:export')")
    @Log(title = "课程详情表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourseDetail courseDetail)
    {
        List<CourseDetail> list = courseDetailService.selectCourseDetailList(courseDetail);
        ExcelUtil<CourseDetail> util = new ExcelUtil<CourseDetail>(CourseDetail.class);
        util.exportExcel(response, list, "课程详情表数据");
    }

    /**
     * 获取课程详情表详细信息
     */
//    @PreAuthorize("@ss.hasPermi('dance:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(courseDetailService.selectCourseDetailById(id));
    }

    /**
     * 新增课程详情表
     */
//    @PreAuthorize("@ss.hasPermi('dance:detail:add')")
    @Log(title = "课程详情表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CourseDetail courseDetail)
    {
        return toAjax(courseDetailService.insertCourseDetail(courseDetail));
    }

    /**
     * 修改课程详情表
     */
//    @PreAuthorize("@ss.hasPermi('dance:detail:edit')")
    @Log(title = "课程详情表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CourseDetail courseDetail)
    {
        return toAjax(courseDetailService.updateCourseDetail(courseDetail));
    }

    /**
     * 删除课程详情表
     */
//    @PreAuthorize("@ss.hasPermi('dance:detail:remove')")
    @Log(title = "课程详情表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(courseDetailService.deleteCourseDetailByIds(ids));
    }
}
