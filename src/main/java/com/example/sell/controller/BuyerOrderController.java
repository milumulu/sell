package com.example.sell.controller;

import com.example.sell.VO.ResultVO;
import com.example.sell.convert.OrderForm2OrderDTOConvert;
import com.example.sell.dataobject.OrderDetail;
import com.example.sell.dto.OrderDTO;
import com.example.sell.enums.ResultEnum;
import com.example.sell.exception.SellException;
import com.example.sell.form.OrderForm;
import com.example.sell.service.BuyerService;
import com.example.sell.service.impl.OrderServiceImpl;
import com.example.sell.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private BuyerService buyerService;


    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【创建订单】 参数不正确 orderForm = {}", orderForm);
            throw new SellException(ResultEnum.PARAM_NOT_EXIST.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConvert.convert(orderForm);

        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EHPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtils.success(map);
    }
    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "row", defaultValue = "10") Integer row) {
        if(StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_NOT_EXIST);
        }
        PageRequest request = PageRequest.of(page, row);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);

        return ResultVOUtils.success(orderDTOPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("orderId") String orderId,
                                        @RequestParam("openid") String openid) {
        if(StringUtils.isEmpty(orderId)
        ||StringUtils.isEmpty(openid)) {
            log.error("【查询订单详情】 参数不正确。 orderId= {}, openid = {}", orderId, openid);
            throw new SellException(ResultEnum.PARAM_NOT_EXIST);
        }
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);

        return ResultVOUtils.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("orderId") String orderId,
                           @RequestParam("openid") String openid) {
        if(StringUtils.isEmpty(orderId)
                ||StringUtils.isEmpty(openid)) {
            log.error("【取消订单】 参数不正确。 orderId= {}, openid = {}", orderId, openid);
            throw new SellException(ResultEnum.PARAM_NOT_EXIST);
        }
        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtils.success();
    }
}
