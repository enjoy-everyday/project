package com.sise.provider.controller;

import com.sise.provider.entity.Book;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloud
 * @description: Lab10
 * @author: wxy
 * @create: 2020-01-26 14:34
 **/

@RestController
public class BookController {

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book findBook(@PathVariable Integer id){
        Book book = new Book();
        book.setId(id);
        book.setName("SpringCloud");
        book.setAuthor("Tommy");
        return book;
    }

}
