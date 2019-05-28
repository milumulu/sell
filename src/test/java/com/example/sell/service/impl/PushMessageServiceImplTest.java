package com.example.sell.service.impl;

import com.example.sell.config.WechatAccountConfig;
import com.example.sell.dto.OrderDTO;
import com.example.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PushMessageServiceImplTest {

    private static final String OPENID = "oGrBx5ipVBB9RRmmIx7PwRT0Fsgo";

    @Autowired
    private PushMessageServiceImpl pushMessageService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("1558247381709283892");
        pushMessageService.orderStatus(orderDTO);
    }

    @Test
    public void wifeLoveLetter() {
        try {
            WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
            templateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("wifeLoveLetter"));
            templateMessage.setToUser(OPENID);
            List<WxMpTemplateData> data = Arrays.asList(
                    new WxMpTemplateData("keyword1","这样看你"),
                    new WxMpTemplateData("keyword2","用所有的眼睛和所有的距离"),
                    new WxMpTemplateData("keyword3","就像风住了"),
                    new WxMpTemplateData("keyword4", "风又起。")
            );
            templateMessage.setData(data);
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】发送失败，{}", e);
        }
    }
}