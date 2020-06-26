package com.summersky.guli.service.trade.feign.fallback;

import com.summersky.guli.service.base.dto.CourseDto;
import com.summersky.guli.service.trade.feign.EduCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zengfanbin
 * @since 2020/5/5
 */
@Service
@Slf4j
public class EduCourseServiceFallback implements EduCourseService {

    @Override
    public CourseDto getCourseDtoById(String courseId) {
        log.info("熔断保护");
        return null;
    }
}
