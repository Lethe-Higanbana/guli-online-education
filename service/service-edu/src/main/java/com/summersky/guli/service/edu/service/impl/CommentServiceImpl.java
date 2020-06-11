package com.summersky.guli.service.edu.service.impl;

import com.summersky.guli.service.edu.entity.Comment;
import com.summersky.guli.service.edu.mapper.CommentMapper;
import com.summersky.guli.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author zengfanbin
 * @since 2020-06-11
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
