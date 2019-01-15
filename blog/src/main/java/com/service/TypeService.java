package com.service;

import com.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Description: 分类service接口
 * @Author: 吴宸煊
 * @Date: Created in  2017/12/25
 */
public interface TypeService {

    Type saveType(Type type);//保存

    Type getType(Long id);//通过id查找

    Type getTypeByName(String name);//通过分类名称查找

    Page<Type> listType(Pageable pageable);//分页

    List<Type> listType();//查找所有的

    List<Type> listTypeTop(Integer size);//拿到数据的多少

    Type updateType(Long id, Type type);//更新

    void deleteType(Long id);//删除
}
