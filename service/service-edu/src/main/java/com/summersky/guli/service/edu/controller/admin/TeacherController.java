package com.summersky.guli.service.edu.controller.admin;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.summersky.guli.common.base.result.R;
import com.summersky.guli.service.edu.entity.Teacher;
import com.summersky.guli.service.edu.entity.vo.TeacherQueryVo;
import com.summersky.guli.service.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zengfanbin
 * @since 2020-06-11
 */
@CrossOrigin // 允许跨域访问，别的服务器可以发送ajax请求过来
@RestController
@RequestMapping("/admin/edu/teacher")
@Slf4j
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

        // 删除讲师头像
        boolean flag = teacherService.removeAvatarById(id);
        // 删除讲师
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

    /**
     * 新增
     * @param teacher
     * @return
     */
    @ApiOperation("新增讲师数据")
    @PostMapping("save")
    public R save(@ApiParam("讲师对象") @RequestBody Teacher teacher){
        teacherService.save(teacher);
        return R.ok().message("保存成功");
    }

    /**
     * 修改
     * @param teacher
     * @return
     */
    @ApiOperation("更新讲师数据")
    @PutMapping("update")
    public R update(@ApiParam("讲师对象") @RequestBody Teacher teacher){
        boolean b = teacherService.updateById(teacher);
        if (b){
            return R.ok().message("更新成功");
        }else {
            return R.ok().message("更新失败");
        }
    }
    /**
     * 根据ID获取讲师数据
     * @param id
     * @return
     */
    @ApiOperation("更新讲师数据")
    @GetMapping("get/{id}")
    public R get(@ApiParam("讲师ID") @PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        if (teacher!=null){
            return R.ok().data("items",teacher);
        }else {
            return R.error().message("数据不存在");
        }

    }

    /**
     * 根据ID删除
     * @param ids
     * @return
     */
    @ApiOperation("根据ID列表删除讲师")
    @DeleteMapping("batch-remove")
    public R batchRemove(@RequestBody List<String> ids){
        boolean b = teacherService.removeByIds(ids);
        if (b){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("数据不存在");
        }

    }

    @ApiOperation("根据关键字查询讲师名列表")
    @GetMapping("list/name/{key}")
    public R selectNameListByKey(
            @ApiParam(value = "关键字", required = true)
            @PathVariable String key){
        List<Map<String, Object>> nameList = teacherService.selectNameList(key);

        return R.ok().data("nameList", nameList);
    }

    @ApiOperation("测试并发")
    @GetMapping("test_concurrent")
    public R testConcurrent(){
        log.info("test_concurrent");
        return R.ok();
    }
}

