package com.example.sell.service.impl;

import com.example.sell.config.WechatAccountConfig;
import com.example.sell.dto.OrderDTO;
import com.example.sell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        try {
            WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
            templateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("orderStatus"));
            templateMessage.setToUser(orderDTO.getBuyerOpenid());
            List<WxMpTemplateData> data = Arrays.asList(
                    new WxMpTemplateData("first","亲，请记得收货"),
                    new WxMpTemplateData("keyword1","微信点餐"),
                    new WxMpTemplateData("keyword2",orderDTO.getBuyerPhone()),
                    new WxMpTemplateData("keyword3",orderDTO.getOrderId()),
                    new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnum().getMessage()),
                    new WxMpTemplateData("keyword5","￥" + orderDTO.getOrderAmount()),
                    new WxMpTemplateData("remark","欢迎再次光临")
            );
            templateMessage.setData(data);
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】发送失败，{}", e);
        }
    }
}
