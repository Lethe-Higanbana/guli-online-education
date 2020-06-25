package com.summersky.guli.service.sms.controller.api;

import com.aliyuncs.exceptions.ClientException;
import com.summersky.guli.common.base.result.R;
import com.summersky.guli.common.base.result.ResultCodeEnum;
import com.summersky.guli.common.base.util.FormUtils;
import com.summersky.guli.common.base.util.RandomUtils;
import com.summersky.guli.service.base.exception.GuliException;
import com.summersky.guli.service.sms.service.SmsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author helen
 * @since 2020/4/29
 */
@RestController
@RequestMapping("/api/sms")
@Api(description = "短信管理")
@CrossOrigin //跨域
@Slf4j
public class ApiSmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("send/{mobile}")
    public R getCode(@PathVariable String mobile) throws ClientException {

        //校验手机号是否合法
        if(StringUtils.isEmpty(mobile) || !FormUtils.isMobile(mobile)){
            log.error("手机号不正确");
            new GuliException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
//            return R.error().message("手机号不正确").code(28001);
        }

        //生成验证码
        String checkCode = RandomUtils.getFourBitRandom();

        //发送验证码
        //smsService.send(mobile, checkCode);

        // 阿里短信收费了，这里以日志形式查看验证码
        log.info("验证码为："+checkCode);

        //存储验证码到redis
        redisTemplate.opsForValue().set(mobile, checkCode, 5, TimeUnit.MINUTES);

        return R.ok().message("短信发送成功");
    }

}
