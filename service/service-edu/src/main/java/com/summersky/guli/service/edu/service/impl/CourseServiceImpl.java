package com.summersky.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.summersky.guli.common.base.result.R;
import com.summersky.guli.service.edu.entity.*;
import com.summersky.guli.service.edu.entity.form.CourseInfoForm;
import com.summersky.guli.service.edu.entity.vo.CoursePublishVo;
import com.summersky.guli.service.edu.entity.vo.CourseQueryVo;
import com.summersky.guli.service.edu.entity.vo.CourseVo;
import com.summersky.guli.service.edu.feign.OssFileService;
import com.summersky.guli.service.edu.mapper.*;
import com.summersky.guli.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zengfanbin
 * @since 2020-06-11
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CourseCollectMapper courseCollectMapper;

    @Autowired
    private OssFileService ossFileService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //保存Course
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);
        course.setStatus(Course.COURSE_DRAFT);
        baseMapper.insert(course);

        //保存CourseDescription
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.insert(courseDescription);

        return course.getId();
    }

    @Override
    public CourseInfoForm getCourseInfoById(String id) {
        //根据id获取Course
        Course course = baseMapper.selectById(id);
        if(course == null){
            return null;
        }

        //根据id获取CourseDescription
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);

        //组装成CourseInfoForm
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course, courseInfoForm);
        courseInfoForm.setDescription(courseDescription.getDescription());

        return courseInfoForm;
    }

    @Override
    public void updateCourseInfoByIf(CourseInfoForm courseInfoForm) {
        //更新Course
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);
        baseMapper.updateById(course);

        //更新CourseDescription
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(courseInfoForm.getId());
        courseDescriptionMapper.updateById(courseDescription);
    }

    @Override
    public IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo) {
        //组装查询条件
        QueryWrapper<CourseVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("c.gmt_create");

        String title = courseQueryVo.getTitle();
        String teacherId = courseQueryVo.getTeacherId();
        String subjectParentId = courseQueryVo.getSubjectParentId();
        String subjectId = courseQueryVo.getSubjectId();

        if(!StringUtils.isEmpty(title)){
            queryWrapper.like("c.title", title);
        }

        if(!StringUtils.isEmpty(teacherId)){
            queryWrapper.eq("c.teacher_id", teacherId);
        }

        if(!StringUtils.isEmpty(subjectParentId)){
            queryWrapper.eq("c.subject_parent_id", subjectParentId);
        }

        if(!StringUtils.isEmpty(subjectId)){
            queryWrapper.eq("c.subject_id", subjectId);
        }

        //组装分页
        Page<CourseVo> pageParam = new Page<>(page, limit);

        //执行分页查询
        //只需要在mapper层传入封装好的分页组件即可，sql分页条件组装的过程由mp自动完成
        List<CourseVo> records = baseMapper.selectPageByCourseQueryVo(pageParam, queryWrapper);
        //将records设置到pageParam中
        return pageParam.setRecords(records);
    }

    @Override
    public boolean removeCoverById(String id) {
        //根据id获取讲师avatar 的 url
        Course course = baseMapper.selectById(id);
        if(course != null){
            String cover = course.getCover();
            if(!StringUtils.isEmpty(cover)){
                R r = ossFileService.removeFile(cover);
                return r.getSuccess();
            }
        }

        return false;
    }

    /**
     * 数据库中外键约束的设置：
     *   互联网分布式项目中不允许使用外键与级联更新，一切设计级联的操作不要依赖数据库层，要在业务层解决
     *
     * 如果业务层解决级联删除功能
     *   那么先删除子表数据，再删除父表数据

     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeCourseById(String id) {
        //根据courseId删除Video(课时)
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", id);
        videoMapper.delete(videoQueryWrapper);

        //根据courseId删除Chapter(章节)
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", id);
        chapterMapper.delete(chapterQueryWrapper);

        //根据courseId删除Comment(评论)
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("course_id", id);
        commentMapper.delete(commentQueryWrapper);

        //根据courseId删除CourseCollect(课程收藏)
        QueryWrapper<CourseCollect> courseCollectQueryWrapper = new QueryWrapper<>();
        courseCollectQueryWrapper.eq("course_id", id);
        courseCollectMapper.delete(courseCollectQueryWrapper);

        //根据courseId删除CourseDescription(课程详情)
        courseDescriptionMapper.deleteById(id);

        //删除课程
        return this.removeById(id);
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourseById(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(Course.COURSE_NORMAL);
        return this.updateById(course);
    }
}
