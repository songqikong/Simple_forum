package com.kkanshan.webfourm.mapper;

import com.kkanshan.webfourm.entity.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    @Select("insert into question(title,description,createId,tag,createTime) values (#{title},#{description},#{createId},#{tag},#{createTime})")
    void createQuestion(Question question);

    //@Select("select * from question;")
    //List<Question> list();

    @Select("select * from question order by id desc limit #{offset},#{size} ")
    List<Question> list(@Param("offset") int offset, @Param("size") int size);

    @Select("select count(1) from question")
    int count();

    @Select("select * from question where tag=#{tag} order by id desc limit #{offset},#{size}" )
    List<Question> listByTag(@Param("tag") int tag ,@Param("offset") int offset, @Param("size") int size);

    @Select("select count(1) from question where tag=#{tag}")
    int countTag(int tag);

    @Select("select * from question where createid=#{userid} limit #{offset},#{size}")
    List<Question> listById(@Param("userid") int userid, @Param("offset") int offset, @Param("size") int size);

    @Select("select count(1) from question where createid=#{userid}")
    int countById(int userid);

    @Select("select * from question where id=#{id}")
    Question getbyId(int id);

    @Update("update question set view_count=view_count+1 where id=#{id}")
    void updateView(int id);

    @Update("update question set comment_count=comment_count+1 where id=#{id}")
    void updateComment(int id);

    //@Select("insert into question(title,description,createId,tag,createTime) values (#{title},#{description},#{createId},#{tag},#{createTime})")
    //void createComment(Question question);

}
