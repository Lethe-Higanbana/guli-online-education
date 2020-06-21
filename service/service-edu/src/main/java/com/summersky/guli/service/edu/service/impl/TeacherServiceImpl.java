package com.summersky.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.summersky.guli.common.base.result.R;
import com.summersky.guli.service.edu.entity.Course;
import com.summersky.guli.service.edu.entity.Teacher;
import com.summersky.guli.service.edu.entity.vo.TeacherQueryVo;
import com.summersky.guli.service.edu.feign.OssFileService;
import com.summersky.guli.service.edu.mapper.CourseMapper;
import com.summersky.guli.service.edu.mapper.TeacherMapper;
import com.summersky.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private OssFileService ossFileService;
    @Autowired
    private CourseMapper courseMapper;

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

    @Override
    public List<Map<String, Object>> selectNameList(String key) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name");
        queryWrapper.likeRight("name", key);
        List<Map<String, Object>> list = baseMapper.selectMaps(queryWrapper);
        return list;
    }

    @Override
    public boolean removeAvatarById(String id) {

        // 根据ID获取讲师头像
        Teacher teacher = baseMapper.selectById(id);
        if (teacher!=null){
            String avatar = teacher.getAvatar();
            if (!StringUtils.isEmpty(avatar)){
                R r = ossFileService.removeFile(avatar);
                return r.getSuccess();
            }
        }

        return false;
    }

    @Override
    public Map<String, Object> selectTeacherInfoById(String id) {
        Teacher teacher = baseMapper.selectById(id);

        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("teacher_id", id);
        List<Course> courseList = courseMapper.selectList(courseQueryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("teacher", teacher);
        map.put("courseList", courseList);

        return map;
    }
}
