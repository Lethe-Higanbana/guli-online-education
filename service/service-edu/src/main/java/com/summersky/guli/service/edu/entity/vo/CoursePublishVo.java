package com.summersky.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author helen
 * @since 2020/4/21
 */
@Data
public class CoursePublishVo implements Serializable{

    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectParentTitle;
    private String subjectTitle;
    private String teacherName;
    private String price;
}