package com.dao;

import com.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description: 标签Dao
 * @Author: 吴宸煊
 * @Date: Created in  2017/12/25
 */
public interface TagRepository extends JpaRepository<Tag,Long> {
    //通过名字查找标签
    Tag findByName(String name);
    //按照分类由博客大小取
    @Query("select t from Tag t")
    List<Tag> findTopBy(Pageable pageable);
}
