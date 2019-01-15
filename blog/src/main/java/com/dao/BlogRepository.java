package com.dao;

import com.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Description: 博客Dao
 * @Author: 吴宸煊
 * @Date: Created in  2017/12/25
 */
public interface BlogRepository extends JpaRepository<Blog,Long> ,JpaSpecificationExecutor<Blog> {
    //查找推荐的博客
    @Query("select  b from Blog b where b.recommend=true ")
    List<Blog> findTop(Pageable pageable);

    //博客search查询
    @Query("select  b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    //归档查询
    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    //根据年份归档博客
    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);

    // 自定义更新
    @Transactional
    @Modifying
    @Query("update Blog b set b.views=b.views+1 where  b.id=?1")
    int updateViews(Long id);
}
