package com.summersky.guli.service.edu.mapper;

import com.summersky.guli.service.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summersky.guli.service.edu.entity.vo.SubjectVo;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author zengfanbin
 * @since 2020-06-11
 */
public interface SubjectMapper extends BaseMapper<Subject> {
    /**
     * 存数据库
     * @param parentId
     * @return
     */
    List<SubjectVo> selectNestedListByParentId(String parentId);
}
