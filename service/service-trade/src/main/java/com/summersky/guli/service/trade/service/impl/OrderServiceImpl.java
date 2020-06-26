package com.summersky.guli.service.trade.service.impl;

import com.summersky.guli.service.trade.entity.Order;
import com.summersky.guli.service.trade.mapper.OrderMapper;
import com.summersky.guli.service.trade.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author zengfanbin
 * @since 2020-06-25
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
