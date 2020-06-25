package com.summersky.guli.service.ucenter.service;

import com.summersky.guli.service.ucenter.entity.Member;
import com.summersky.guli.service.ucenter.entity.vo.LoginVo;
import com.summersky.guli.service.ucenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author zengfanbin
 * @since 2020-04-29
 */
public interface MemberService extends IService<Member> {

    void register(RegisterVo registerVo);

    String login(LoginVo loginVo);

    Member getByOpenid(String openid);
}
