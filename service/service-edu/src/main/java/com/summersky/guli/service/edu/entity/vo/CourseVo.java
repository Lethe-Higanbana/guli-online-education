package com.summersky.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zengfanbin
 * @since 2020/4/21
 */
@Data
public class CourseVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    /**
     * 一级分类标题
     */
    private String subjectParentTitle;
    /**
     * 二级分类标题
     */
    private String subjectTitle;
    /**
     * 讲师姓名
     */
    private String teacherName;
    private Integer lessonNum;
    private String price;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private String status;
    private String gmtCreate;
}