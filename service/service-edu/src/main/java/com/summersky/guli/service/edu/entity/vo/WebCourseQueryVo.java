package com.summersky.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author helen
 * @since 2020/4/27
 */
@Data
public class WebCourseQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String subjectParentId;
    private String subjectId;
    private String buyCountSort;
    private String gmtCreateSort;
    private String priceSort;

    private Integer type;
}
