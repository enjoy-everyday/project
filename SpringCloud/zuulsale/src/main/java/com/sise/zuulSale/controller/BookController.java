package com.sise.zuulSale.controller;

import com.sise.zuulSale.entity.Book;
import com.sise.zuulSale.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * @program: SpringCloud
 * @description: Lab10
 * @author: wxy
 * @create: 2020-01-26 14:42
 **/

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "saleBook/{id}", method = RequestMethod.GET)
    public String saleBook(@PathVariable Integer id){
        Book book = bookService.getBook(id);        //调用Book服务查看
        System.out.println("id：" + book.getId());       //控制台输出，模拟销售
        System.out.println("书名：" + book.getName());
        return "SUCCESS";
    }

}
