package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CourseDetailMapper;
import com.ruoyi.system.domain.CourseDetail;
import com.ruoyi.system.service.ICourseDetailService;

/**
 * 课程详情表Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-27
 */
@Service
public class CourseDetailServiceImpl implements ICourseDetailService 
{
    @Autowired
    private CourseDetailMapper courseDetailMapper;

    /**
     * 查询课程详情表
     * 
     * @param id 课程详情表主键
     * @return 课程详情表
     */
    @Override
    public CourseDetail selectCourseDetailById(Long id)
    {
        return courseDetailMapper.selectCourseDetailById(id);
    }

    /**
     * 查询课程详情表列表
     * 
     * @param courseDetail 课程详情表
     * @return 课程详情表
     */
    @Override
    public List<CourseDetail> selectCourseDetailList(CourseDetail courseDetail)
    {
        return courseDetailMapper.selectCourseDetailList(courseDetail);
    }

    /**
     * 新增课程详情表
     * 
     * @param courseDetail 课程详情表
     * @return 结果
     */
    @Override
    public int insertCourseDetail(CourseDetail courseDetail)
    {
        courseDetail.setCreateTime(DateUtils.getNowDate());
        return courseDetailMapper.insertCourseDetail(courseDetail);
    }

    /**
     * 修改课程详情表
     * 
     * @param courseDetail 课程详情表
     * @return 结果
     */
    @Override
    public int updateCourseDetail(CourseDetail courseDetail)
    {
        return courseDetailMapper.updateCourseDetail(courseDetail);
    }

    /**
     * 批量删除课程详情表
     * 
     * @param ids 需要删除的课程详情表主键
     * @return 结果
     */
    @Override
    public int deleteCourseDetailByIds(Long[] ids)
    {
        return courseDetailMapper.deleteCourseDetailByIds(ids);
    }

    /**
     * 删除课程详情表信息
     * 
     * @param id 课程详情表主键
     * @return 结果
     */
    @Override
    public int deleteCourseDetailById(Long id)
    {
        return courseDetailMapper.deleteCourseDetailById(id);
    }
}
