package com.sise.zuulSale.service;

import com.sise.zuulSale.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @program: SpringCloud
 * @description: Lab10
 * @author: wxy
 * @create: 2020-01-26 14:39
 **/

@FeignClient("book-server")        //声明调用服务
public interface BookService {

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    Book getBook(@PathVariable("id") Integer id);

}
