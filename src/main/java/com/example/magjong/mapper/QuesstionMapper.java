package com.example.magjong.mapper;

import com.example.magjong.model.Quesstion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface QuesstionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag,comment_cont) value (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag},#{commentCont})")
    public void createQuesstion(Quesstion quesstion);
    @Select("select * from question limit #{offzise},3")
    public List<Quesstion> getList(Integer offsize);
    @Select("select * from question")
    public List<Quesstion> list();
    @Select("select count(1) from question")
    public Integer getCommationLine();
    @Select("select * from question where creator = #{token}")
    public List<Quesstion> myQuestion(String token);
    @Select("select * from question where creator=#{token} ORDER BY id limit #{offzise},3")
    public List<Quesstion> getMyQuestionList(Integer offsize,String token);
    @Select("select * from question where id=${id}")
    public Quesstion getById(Long id);
    @Update("UPDATE question set title=#{title},description=#{description},tag=#{tag},gmt_modified=#{gmtModified} where id=#{id};")
    public void update(Quesstion quesstion);

}
