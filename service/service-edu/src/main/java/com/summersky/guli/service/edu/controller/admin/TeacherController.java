package com.summersky.guli.service.edu.controller.admin;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.summersky.guli.common.base.result.R;
import com.summersky.guli.service.edu.entity.Teacher;
import com.summersky.guli.service.edu.entity.vo.TeacherQueryVo;
import com.summersky.guli.service.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    /**
     * 讲师分页列表
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation("讲师分页查询")
    @GetMapping("page/{page}/{limit}")
    public R listPage(@ApiParam("当前页") @PathVariable(required = true) Long page, @ApiParam("每页记录数") @PathVariable(required = true) Long limit,
                      @ApiParam("讲师查询列表对象") TeacherQueryVo teacherQueryVo){
        Page<Teacher> pageParam = new Page<>(page, limit);
        Page<Teacher> iPage = teacherService.selectPage(pageParam,teacherQueryVo);
        List<Teacher> records = iPage.getRecords();
        long total = iPage.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }
}

