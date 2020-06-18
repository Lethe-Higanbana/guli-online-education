package com.summersky.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zengfanbin
 * @since 2020/6/14
 */
@Data
public class SubjectVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer sort;

    private List<SubjectVo> children = new ArrayList<>();
}
