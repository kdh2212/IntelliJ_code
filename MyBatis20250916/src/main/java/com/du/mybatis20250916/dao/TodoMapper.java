package com.du.mybatis20250916.dao;



import com.du.mybatis20250916.model.Todo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TodoMapper {

    @Select("SELECT * FROM todos")
    List<Todo> findALL();

    @Insert("Insert INTO todos (title) values (#{title})")
    void add(String title);

    @Delete("DELETE FROM todos WHERE id = #{id}")
    void delete(int id);

    @Update("UPDATE todos SET completed = NOT completed WHERE id = #{id}")
    void toggleCompleted(int id);

    @Update("UPDATE todos set title = #{title} where id = #{id}")
    void update(Todo todo);

    @Select("SELECT * FROM todos WHERE id = #{id}")
    Todo findById(int id);
}
