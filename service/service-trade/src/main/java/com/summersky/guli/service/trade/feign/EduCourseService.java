package com.summersky.guli.service.trade.feign;

import com.summersky.guli.common.base.result.R;
import com.summersky.guli.service.base.dto.CourseDto;
import com.summersky.guli.service.trade.feign.fallback.EduCourseServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zengfanbin
 * @since 2020/5/5
 */
@Service
@FeignClient(value = "service-edu", fallback = EduCourseServiceFallback.class)
public interface EduCourseService {

    @GetMapping("/api/edu/course/inner/get-course-dto/{courseId}")
    CourseDto getCourseDtoById(@PathVariable(value = "courseId") String courseId);
    @GetMapping("/api/edu/course/inner/update-buy-count/{id}")
    R updateBuyCountById(String courseId);
}
