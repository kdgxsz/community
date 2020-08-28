package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * userDao接口
 *
 * @author 尚郑
 */
@Mapper
public interface UserMapper {

    /**
     * 根据id查询单个用户
     *
     * @param id 用户id
     * @return 用户
     */
    User selectById(int id);

    /**
     * 根据用户姓名查询单个用户
     *
     * @param username 用户姓名
     * @return 用户
     */
    User selectByName(String username);

    /**
     * 根据email查询单个用户
     *
     * @param email Email
     * @return 用户
     */
    User selectByEmail(String email);

    /**
     * 添加一个用户
     *
     * @param user User对象
     * @return 自增长ID
     */
    int insertUser(User user);

    /**
     * 根据用户Id修改用户激活状态
     *
     * @param id     用户id
     * @param status 用户状态
     * @return 0-未激活; 1-已激活
     */
    int updateStatus(int id, int status);

    /**
     * 根据用户Id修改用户头像
     *
     * @param id        用户id
     * @param headerUrl 用户头像地址
     * @return 修改条数
     */
    int updateHeader(int id, String headerUrl);

    /**
     * 根据Id修改密码
     *
     * @param id       用户Id
     * @param password 修改后的密码
     * @return 修改条数
     */
    int updatePassword(int id, String password);
}
