package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CourseDetail;

/**
 * 课程详情表Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-27
 */
public interface CourseDetailMapper 
{
    /**
     * 查询课程详情表
     * 
     * @param id 课程详情表主键
     * @return 课程详情表
     */
    public CourseDetail selectCourseDetailById(Long id);

    /**
     * 查询课程详情表列表
     * 
     * @param courseDetail 课程详情表
     * @return 课程详情表集合
     */
    public List<CourseDetail> selectCourseDetailList(CourseDetail courseDetail);

    /**
     * 新增课程详情表
     * 
     * @param courseDetail 课程详情表
     * @return 结果
     */
    public int insertCourseDetail(CourseDetail courseDetail);

    /**
     * 修改课程详情表
     * 
     * @param courseDetail 课程详情表
     * @return 结果
     */
    public int updateCourseDetail(CourseDetail courseDetail);

    /**
     * 删除课程详情表
     * 
     * @param id 课程详情表主键
     * @return 结果
     */
    public int deleteCourseDetailById(Long id);

    /**
     * 批量删除课程详情表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseDetailByIds(Long[] ids);
}