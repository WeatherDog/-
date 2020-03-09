package com.example.magjong.mapper;

import com.example.magjong.dto.ReplyQuestionDTO;
import com.example.magjong.model.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReplyMapper {
    @Insert("insert into reply values(#{description},#{rank},#{createTime},#{replyUserId},#{likeCount},#{replyCount},#{questionId},null,#{status},#{replyParmentId})")
    public void addRpely(Reply reply);
    @Select("select * from reply where question_id=#{quesitonId} and reply_user_id=#{quesitonId}")
    public void getByReplyUserIdAndQuestionId(Integer userId,Integer quesitonId);
    @Select("select * from reply where question_id = #{questionId}")
    public List<Reply> total(Integer questionId);
    @Select("select count(0) from reply where question_id = #{questionId}")
    public Integer count(Integer questionId);
    @Select("select id from reply where create_time=#{time}")
    public Integer getId(Long time);
    @Select("select status from reply where id=#{id}")
    public boolean getstatus(Integer id);
    @Select("select * from reply where question_id = #{questionId} and reply_parment_id=#{replyParentId}")
    public List<Reply> getSecondLevel(Integer questionId, Integer replyParentId);
}
