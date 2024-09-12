package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Course;

/**
 * 课程信息管理Service接口
 * 
 * @author ruoyi
 * @date 2024-02-25
 */
public interface ICourseService 
{
    /**
     * 查询课程信息管理
     * 
     * @param id 课程信息管理主键
     * @return 课程信息管理
     */
    public Course selectCourseById(Long id);

    /**
     * 查询课程信息管理列表
     * 
     * @param course 课程信息管理
     * @return 课程信息管理集合
     */
    public List<Course> selectCourseList(Course course);

    /**
     * 新增课程信息管理
     * 
     * @param course 课程信息管理
     * @return 结果
     */
    public int insertCourse(Course course);

    /**
     * 修改课程信息管理
     * 
     * @param course 课程信息管理
     * @return 结果
     */
    public int updateCourse(Course course);

    /**
     * 批量删除课程信息管理
     * 
     * @param ids 需要删除的课程信息管理主键集合
     * @return 结果
     */
    public int deleteCourseByIds(Long[] ids);

    /**
     * 删除课程信息管理信息
     * 
     * @param id 课程信息管理主键
     * @return 结果
     */
    public int deleteCourseById(Long id);
}
