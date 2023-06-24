package com.laioffer.onlineorder.controller;

import com.laioffer.onlineorder.entity.Customer;
import com.laioffer.onlineorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


//不要忘记加入Controller Annotation，否则rest中定一个API都不能被访问，返回404
//定义controller API
@Controller
public class SingUpController{
    private CustomerService customerService;
    //通过Autowired进行dependency injection customerSerice的object
    //将controller与customerService连接起来
    @Autowired
    public SingUpController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //HashMap的<key,value>pairs
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    //浏览器发送请求，以及是这个method时，做API对应的操作,返回response
    @ResponseStatus(value = HttpStatus.CREATED)
    public void signUp(@RequestBody Customer customer) {
        //RequestBody：http request传入时时json格式，@RequestBody帮助我们转换成一个customer object
        customerService.signUp(customer);
    }
}
