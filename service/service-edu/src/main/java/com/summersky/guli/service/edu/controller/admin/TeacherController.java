package com.summersky.guli.service.edu.controller.admin;


import com.summersky.guli.common.base.result.R;
import com.summersky.guli.service.edu.entity.Teacher;
import com.summersky.guli.service.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("查询所有讲师数据")
    @GetMapping("list")
    public R listAll(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("list",list);
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除讲师")
    @DeleteMapping("remove/{id}")
    public R removeById(@PathVariable String id){
        boolean b = teacherService.removeById(id);
        if (b){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("数据不存在");
        }

    }
}

