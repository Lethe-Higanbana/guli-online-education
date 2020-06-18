package com.summersky.guli.service.edu.service;

import com.summersky.guli.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.summersky.guli.service.edu.entity.vo.SubjectVo;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zengfanbin
 * @since 2020-06-11
 */
public interface SubjectService extends IService<Subject> {

    /**
     * 批量导入
     * @param inputStream
     *
     */
    void batchImport(InputStream inputStream);

    /**
     *
     * @return
     */
    List<SubjectVo> nestedList();
}
