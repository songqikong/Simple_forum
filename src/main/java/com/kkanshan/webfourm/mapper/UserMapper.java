package com.kkanshan.webfourm.mapper;


import com.kkanshan.webfourm.entity.Comment;
import com.kkanshan.webfourm.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Insert("insert into user(name,password,token) values (#{name},#{password},#{token})")
    void insert(User user);

    @Select("select * from user where name=#{name} and password=#{password}")
    User select(User user);

    @Select("select * from user where name=#{name}")
    User selectRegister(User user);

    @Select("select  * from user where token=#{token}")
    User findBytoken(String token);

    @Select("select * from user where id=#{createid}")
    User findById(int createid);


    @Select("select user.name from user,comment where comment.parent_id=#{Id} and user.id=comment.commenter")
    List<User> listFindByCommentId(int Id);

}
