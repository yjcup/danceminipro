package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserCourseMapper;
import com.ruoyi.system.domain.UserCourse;
import com.ruoyi.system.service.IUserCourseService;

/**
 * 用户课程信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-04
 */
@Service
public class UserCourseServiceImpl implements IUserCourseService 
{
    @Autowired
    private UserCourseMapper userCourseMapper;

    /**
     * 查询用户课程信息
     * 
     * @param id 用户课程信息主键
     * @return 用户课程信息
     */
    @Override
    public UserCourse selectUserCourseById(Long id)
    {
        return userCourseMapper.selectUserCourseById(id);
    }

    /**
     * 查询用户课程信息列表
     * 
     * @param userCourse 用户课程信息
     * @return 用户课程信息
     */
    @Override
    public List<UserCourse> selectUserCourseList(UserCourse userCourse)
    {
        return userCourseMapper.selectUserCourseList(userCourse);
    }

    /**
     * 新增用户课程信息
     * 
     * @param userCourse 用户课程信息
     * @return 结果
     */
    @Override
    public int insertUserCourse(UserCourse userCourse)
    {
        userCourse.setCreateTime(DateUtils.getNowDate());
        return userCourseMapper.insertUserCourse(userCourse);
    }

    /**
     * 修改用户课程信息
     * 
     * @param userCourse 用户课程信息
     * @return 结果
     */
    @Override
    public int updateUserCourse(UserCourse userCourse)
    {
        return userCourseMapper.updateUserCourse(userCourse);
    }

    /**
     * 批量删除用户课程信息
     * 
     * @param ids 需要删除的用户课程信息主键
     * @return 结果
     */
    @Override
    public int deleteUserCourseByIds(Long[] ids)
    {
        return userCourseMapper.deleteUserCourseByIds(ids);
    }

    /**
     * 删除用户课程信息信息
     * 
     * @param id 用户课程信息主键
     * @return 结果
     */
    @Override
    public int deleteUserCourseById(Long id)
    {
        return userCourseMapper.deleteUserCourseById(id);
    }
}
