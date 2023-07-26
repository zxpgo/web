package com.example.springbootvue3.mapper;

import com.example.springbootvue3.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where name like concat('%', #{name},'%') or date like concat('%', #{name},'%') or address like concat('%', #{name},'%') order by id desc")
    List<User> selectAll(String  name);

    @Select("select * from user where name like concat('%', #{name},'%') or date like concat('%', #{name},'%') or address like concat('%', #{name},'%') order by id desc limit #{offset}, #{pageSize}")
    List<User> selectPage(@Param("name") String  name, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from user where name like concat('%', #{name},'%') or date like concat('%', #{name},'%') or address like concat('%', #{name},'%')")
    Integer selectCount(String  name);

    @Insert("insert into user(name, date, address, user_num) values(#{name}, #{date}, #{address}, #{userNum})")
    void insert(User user);

    @Update("update user set name = #{name}, date = #{date}, address = #{address}, user_num = #{userNum} where id = #{id}")
    void update(User user);

    @Delete("delete from user where id = #{id}")
    void delete(Integer id);
}
