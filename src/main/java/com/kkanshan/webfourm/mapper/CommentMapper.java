package com.kkanshan.webfourm.mapper;

import com.kkanshan.webfourm.entity.Comment;
import com.kkanshan.webfourm.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    @Insert("insert into comment(parent_id,type,commenter,createTime,content) " +
            "values (#{parent_id},#{type},#{commenter},#{createTime},#{content})")
    void insert(Comment comment);

    @Select("select * from comment where id=#{id}")
    Comment getById(int id);

    //@Select("select * from comment where parent_id=#{parentId}")
    //Comment getByParentId(int parentId);

    @Select("select * from comment where parent_id=#{parentId} ")
    List<Comment> getByParentId(int parentId);

}
