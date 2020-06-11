package com.summersky.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.summersky.guli.service.edu.entity.Teacher;
import com.summersky.guli.service.edu.entity.vo.TeacherQueryVo;
import com.summersky.guli.service.edu.mapper.TeacherMapper;
import com.summersky.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author zengfanbin
 * @since 2020-06-11
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public Page<Teacher> selectPage(Page<Teacher> pageParam, TeacherQueryVo teacherQueryVo) {
        //显示分页列表
        // 1、排序，根据sort字段（前端传）
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        // 2、查询
        if (teacherQueryVo==null){
            return baseMapper.selectPage(pageParam,queryWrapper);
        }
        // 3、条件查询
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        // 非空判断
        if (!StringUtils.isEmpty(name)){
            queryWrapper.likeRight("name",name);
        }

        if (level!=null){
            queryWrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(joinDateBegin)){
            // ge 大于等于
            queryWrapper.ge("join_date",joinDateBegin);
        }

        if (!StringUtils.isEmpty(joinDateEnd)){
            // ge 小于等于
            queryWrapper.le("join_date",joinDateEnd);
        }
        return baseMapper.selectPage(pageParam,queryWrapper);
    }
}
