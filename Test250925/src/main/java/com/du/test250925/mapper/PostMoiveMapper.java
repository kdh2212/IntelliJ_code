package com.du.test250925.mapper;

import com.du.test250925.domain.PostMovie;
import org.apache.ibatis.annotations.*;

import java.awt.print.Book;
import java.util.List;

@Mapper
public interface PostMoiveMapper {

    @Select("SELECT * from postMovie order by id DESC")
    List<PostMovie> findAll();

    @Select("SELECT * FROM postMovie WHERE id = #{id}")
    PostMovie findById(Long id);

    @Insert("INSERT INTO postMovie (title,content,director,genre,writer) VALUES (#{title},#{content}, #{director},#{genre},#{writer})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(PostMovie postMovie);


    @Update("UPDATE postMovie SET title = #{title}, content = #{content}, director = #{director}, genre = #{genre},  writer = #{writer} WHERE id = #{id}")
    void update(PostMovie postMovie);

    @Delete("DELETE FROM postMovie WHERE id = #{id}")
    void delete(Long id);

    @Update("UPDATE postMovie SET recommendations = recommendations + 1 WHERE id = #{id}")
    void updateRecommendations(PostMovie postMovie);

    @Update("UPDATE postMovie SET rejections  = rejections  + 1 WHERE id = #{id}")
    void updateRejections(PostMovie postMovie);

}
