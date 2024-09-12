package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DanceUser;

/**
 * 用户管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-01
 */
public interface DanceUserMapper 
{
    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    public DanceUser selectDanceUserById(Long id);

    /**
     * 查询用户管理列表
     * 
     * @param danceUser 用户管理
     * @return 用户管理集合
     */
    public List<DanceUser> selectDanceUserList(DanceUser danceUser);

    /**
     * 新增用户管理
     * 
     * @param danceUser 用户管理
     * @return 结果
     */
    public int insertDanceUser(DanceUser danceUser);

    /**
     * 修改用户管理
     * 
     * @param danceUser 用户管理
     * @return 结果
     */
    public int updateDanceUser(DanceUser danceUser);

    /**
     * 删除用户管理
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    public int deleteDanceUserById(Long id);

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDanceUserByIds(Long[] ids);
}