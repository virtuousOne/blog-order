package com.service;

import com.NotFoundException;
import com.dao.TypeRepository;
import com.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 实现分类service接口
 * @Author: 吴宸煊
 * @Date: Created in  2017/12/25
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    //保存
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    //通过id查找
    public Type getType(Long id) {
        return typeRepository.findOne(id);
    }

    //通过分类名称查找
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transactional
    //分页查询
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }


    public List<Type> listType() {
        return typeRepository.findAll();
    }


    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort);
        return typeRepository.findTop(pageable);
    }



    @Transactional
    //更新
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }



    @Transactional
  //删除
    public void deleteType(Long id) {
        typeRepository.delete(id);
    }
}
