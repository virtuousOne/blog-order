package com.dao;

import com.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;
/**
 * @Description: 分类Dao
 * @Author: 吴宸煊
 * @Date: Created in  2017/12/25
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
  //通过分类名字查找分类
  Type findByName(String name);

  //按照分类由博客大小取
  @Query("select t from Type t")
  List<Type> findTop(Pageable pageable);


}
