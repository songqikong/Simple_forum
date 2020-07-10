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

    @Select("select * from question where createid=#{userid} limit #{offset},#{size}")
    List<Question> listbyid(@Param("userid") int userid, @Param("offset") int offset, @Param("size") int size);

    @Select("select count(1) from question where createid=#{userid}")
    int countbyid(int userid);
}
