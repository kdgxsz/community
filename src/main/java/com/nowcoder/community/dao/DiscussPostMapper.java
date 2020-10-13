package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 帖子的Dao接口
 *
 * @author 尚郑
 */
@Mapper
public interface DiscussPostMapper {

    /**
     * 查询所有的帖子(拉黑除外)
     *
     * @param userId 用户id (可不传入)
     * @param offset 记录开始的index，从0开始，表示第一条记录
     * @param limit  每页展示行数
     * @return 帖子集合
     */
    List<DiscussPost> selectDiscussPost(int userId, int offset, int limit);


    /**
     * 帖子条数
     *
     * @param userId @Param注解用于给参数取别名,如果只有一个参数,并且在<if>里使用,则必须加别名
     * @return 帖子总数 (传用户id则查出该用户的总帖子数,否则查出拉黑之外总帖数)
     */
    int selectDiscussPostRows(@Param("userId") int userId);

    /**
     * 发布帖子
     *
     * @param discussPost 标题和内容
     * @return 添加条数
     */
    int insertDiscussPost(DiscussPost discussPost);

    /**
     * 通过id查询帖子
     *
     * @param id 帖子的id
     * @return 一个帖子对象
     */
    DiscussPost selectDiscussPostById(int id);

    /**
     * @param id
     * @param commentCount
     * @return
     */
    int updateCommentCount(int id, int commentCount);

    /**
     * 更改帖子类型
     * @param id 帖子Id
     * @param type 帖子类型 0-普通; 1-置顶
     * @return
     */
    int updateType(int id, int type);

    /**
     * 根据帖子id更改帖子状态
     * @param id  帖子id
     * @param status 帖子状态 0-正常; 1-精华; 2-拉黑;
     * @return
     */
    int updateStatus(int id, int status);


}
