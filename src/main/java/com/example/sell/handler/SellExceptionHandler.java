package com.example.sell.handler;

import com.example.sell.VO.ResultVO;
import com.example.sell.config.ProjectUrlConfig;
import com.example.sell.exception.ResponseBankException;
import com.example.sell.exception.SellException;
import com.example.sell.exception.SellerAuthorizeExcption;
import com.example.sell.utils.ResultVOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeExcption.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
        .concat(projectUrlConfig.getWecharOpenAuthorize())
        .concat("/sell/wechat/qrAuthorize")
        .concat("?returnUrl=")
        .concat(projectUrlConfig.getSell())
        .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e) {
        return ResultVOUtils.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handlerResponseBankException() {

    }
}
