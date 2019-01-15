package com.service;

import com.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Description: 标签service接口
 * @Author: 吴宸煊
 * @Date: Created in  2017/12/25
 */
public interface TagService {

    Tag saveTag(Tag type); //保存

    Tag getTag(Long id);//通过id查找

    Tag getTagByName(String name);//通过标签名字查找

    Page<Tag> listTag(Pageable pageable);//分页

    List<Tag> listTag();//显示所有

    List<Tag> listTag(String ids);//获取多个逗号隔开的标签

    Tag updateTag(Long id, Tag type);//更新

    List<Tag> listTagTop(Integer size);//前端头部查询

    void deleteTag(Long id);//删除
}
