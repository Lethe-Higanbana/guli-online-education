package com.summersky.guli.service.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.summersky.guli.service.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summersky.guli.service.edu.entity.vo.CoursePublishVo;
import com.summersky.guli.service.edu.entity.vo.CourseVo;
import com.summersky.guli.service.edu.entity.vo.WebCourseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author zengfanbin
 * @since 2020-06-11
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseVo> selectPageByCourseQueryVo(
            Page<CourseVo> pageParam,
            @Param(Constants.WRAPPER) QueryWrapper<CourseVo> queryWrapper);

    CoursePublishVo selectCoursePublishVoById(String id);

    WebCourseVo selectWebCourseVoById(String id);
}
