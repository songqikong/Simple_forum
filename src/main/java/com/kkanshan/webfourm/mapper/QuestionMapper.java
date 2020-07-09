package com.kkanshan.webfourm.mapper;

import com.kkanshan.webfourm.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    @Select("insert into question(title,description,createId,tag,createTime) values (#{title},#{description},#{createId},#{tag},#{createTime})")
    void createQuestion(Question question);

    @Select("select * from question;")
    List<Question> list();
}
