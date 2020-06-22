package com.summersky.guli.service.edu.controller.api;

import com.summersky.guli.common.base.result.R;
import com.summersky.guli.service.edu.entity.Course;
import com.summersky.guli.service.edu.entity.vo.ChapterVo;
import com.summersky.guli.service.edu.entity.vo.WebCourseQueryVo;
import com.summersky.guli.service.edu.entity.vo.WebCourseVo;
import com.summersky.guli.service.edu.service.ChapterService;
import com.summersky.guli.service.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zengfanbin
 * @since 2020/4/27
 */
@CrossOrigin
@Api(description="课程")
@RestController
@RequestMapping("/api/edu/course")
public class ApiCourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @ApiOperation("课程列表")
    @GetMapping("list")
    public R pageList(
            @ApiParam(value = "查询对象", required = true)
            WebCourseQueryVo webCourseQueryVo){

       List<Course> courseList = courseService.webSelectList(webCourseQueryVo);

       return R.ok().data("courseList", courseList);
    }

    @ApiOperation("根据id查询课程")
    @GetMapping("get/{courseId}")
    public R getById(
            @ApiParam(value = "课程id", required = true)
            @PathVariable String courseId){

        // 查询课程信息和讲师信息
        WebCourseVo webCourseVo = courseService.selectWebCourseVoById(courseId);

        // 查询当前课程的嵌套章节和课时信息
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);

        return R.ok().data("course", webCourseVo).data("chapterVoList", chapterVoList);
    }
}
