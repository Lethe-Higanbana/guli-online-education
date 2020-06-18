package com.summersky.guli.service.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Lenovo
 * @Authur:zengfanbin
 * @Date:2020-6-14
 * @Time:18:09
 * @Description:
 */
@Data
public class ExcelSubjectData {
    @ExcelProperty(value = "一级分类")
    private String levelOneTitle;

    @ExcelProperty(value = "二级分类")
    private String levelTwoTitle;
}
