package com.summersky.guli.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.summersky.guli.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.summersky.guli.service.edu.entity.form.CourseInfoForm;
import com.summersky.guli.service.edu.entity.vo.*;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zengfanbin
 * @since 2020-06-11
 */
public interface CourseService extends IService<Course> {
    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    void updateCourseInfoByIf(CourseInfoForm courseInfoForm);

    IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo);

    boolean removeCoverById(String id);

    boolean removeCourseById(String id);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    List<Course> webSelectList(WebCourseQueryVo webCourseQueryVo);

    /**
     * 获取课程信息并更新浏览量
     * @param id
     * @return
     */
    WebCourseVo selectWebCourseVoById(String id);
}
