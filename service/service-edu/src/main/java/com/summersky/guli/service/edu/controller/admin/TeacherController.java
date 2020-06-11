package com.summersky.guli.service.edu.controller.admin;


import com.summersky.guli.service.edu.entity.Teacher;
import com.summersky.guli.service.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zengfanbin
 * @since 2020-06-11
 */
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 获取所有讲师数据
     * @return
     */
    @GetMapping("list")
    public List<Teacher> listAll(){
        List<Teacher> list = teacherService.list();
        return list;
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @DeleteMapping("remove/{id}")
    public boolean removeById(@PathVariable String id){
        return teacherService.removeById(id);
    }
}

