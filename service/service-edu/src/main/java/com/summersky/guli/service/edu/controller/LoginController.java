package com.summersky.guli.service.edu.controller;

import com.summersky.guli.common.base.result.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lenovo
 * @Authur:zengfanbin
 * @Date:2020-6-12
 * @Time:13:09
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {

    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info(){
        return R.ok().data("name","admin").data("roles","[admin]").data("avatar","");
    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
