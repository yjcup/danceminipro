package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DanceUserMapper;
import com.ruoyi.system.domain.DanceUser;
import com.ruoyi.system.service.IDanceUserService;

/**
 * 用户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-01
 */
@Service
public class DanceUserServiceImpl implements IDanceUserService 
{
    @Autowired
    private DanceUserMapper danceUserMapper;

    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    @Override
    public DanceUser selectDanceUserById(Long id)
    {
        return danceUserMapper.selectDanceUserById(id);
    }

    /**
     * 查询用户管理列表
     * 
     * @param danceUser 用户管理
     * @return 用户管理
     */
    @Override
    public List<DanceUser> selectDanceUserList(DanceUser danceUser)
    {
        return danceUserMapper.selectDanceUserList(danceUser);
    }

    /**
     * 新增用户管理
     * 
     * @param danceUser 用户管理
     * @return 结果
     */
    @Override
    public int insertDanceUser(DanceUser danceUser)
    {
        danceUser.setCreateTime(DateUtils.getNowDate());
        return danceUserMapper.insertDanceUser(danceUser);
    }

    /**
     * 修改用户管理
     * 
     * @param danceUser 用户管理
     * @return 结果
     */
    @Override
    public int updateDanceUser(DanceUser danceUser)
    {
        return danceUserMapper.updateDanceUser(danceUser);
    }

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteDanceUserByIds(Long[] ids)
    {
        return danceUserMapper.deleteDanceUserByIds(ids);
    }

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteDanceUserById(Long id)
    {
        return danceUserMapper.deleteDanceUserById(id);
    }
}
