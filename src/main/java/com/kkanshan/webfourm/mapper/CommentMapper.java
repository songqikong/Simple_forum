package com.kkanshan.webfourm.mapper;

import com.kkanshan.webfourm.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentMapper {

    @Insert("insert into comment(parent_id,type,commenter,createTime,content) " +
            "values (#{parent_id},#{type},#{commenter},#{createTime},#{content})")
    void insert(Comment comment);

}
