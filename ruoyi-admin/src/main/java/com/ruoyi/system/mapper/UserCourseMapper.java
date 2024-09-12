package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.UserCourse;

/**
 * 用户课程信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-04
 */
public interface UserCourseMapper 
{
    /**
     * 查询用户课程信息
     * 
     * @param id 用户课程信息主键
     * @return 用户课程信息
     */
    public UserCourse selectUserCourseById(Long id);

    /**
     * 查询用户课程信息列表
     * 
     * @param userCourse 用户课程信息
     * @return 用户课程信息集合
     */
    public List<UserCourse> selectUserCourseList(UserCourse userCourse);

    /**
     * 新增用户课程信息
     * 
     * @param userCourse 用户课程信息
     * @return 结果
     */
    public int insertUserCourse(UserCourse userCourse);

    /**
     * 修改用户课程信息
     * 
     * @param userCourse 用户课程信息
     * @return 结果
     */
    public int updateUserCourse(UserCourse userCourse);

    /**
     * 删除用户课程信息
     * 
     * @param id 用户课程信息主键
     * @return 结果
     */
    public int deleteUserCourseById(Long id);

    /**
     * 批量删除用户课程信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserCourseByIds(Long[] ids);
}
