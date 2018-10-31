package com.codegym.blog.controller;

import com.codegym.blog.model.Blog;
import com.codegym.blog.service.Blog_service;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
//@RequestMapping("/blog")
public class Blog_controller {
    @Autowired
    Blog_service blogService;

    @RequestMapping(value = {"/home","/"})
    ModelAndView showHomePage(){
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("blog",new Blog());
        return modelAndView;
    }

    @PostMapping("add")
    ModelAndView addBlog(Blog blog){
        blogService.save(blog);
        ModelAndView model = new ModelAndView("home");
        model.addObject("message","Success!!");
        return model;
    }

    @GetMapping("list")
    ModelAndView showListPage(){
        ModelAndView modelAndView = new ModelAndView("list");
        List<Blog> blogs = blogService.findAll();
        modelAndView.addObject("blogs",blogs);
        return modelAndView;
    }

    @GetMapping("view/{id}")
    ModelAndView showViewPage(@PathVariable("id") Long id ){
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("blog",blogService.findById(id));
        return modelAndView;
    }

    @GetMapping("edit/{id}")
    ModelAndView showEditPage(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView(("edit"));
        modelAndView.addObject("blog",blogService.findById(id));
        return modelAndView;
    }

    @PostMapping("edit/{id}")
    ModelAndView editBlog(Blog blog){
        ModelAndView modelAndView = new ModelAndView("edit");
        blogService.save(blog);
        modelAndView.addObject("message","Success!!");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    ModelAndView showDeletePage(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("blog",blogService.findById(id));
        return modelAndView;
    }

    @PostMapping("delete/{id}")
    ModelAndView deleteBlog(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("delete");
        blogService.remove(id);
        modelAndView.addObject("message","Success!!");
        return modelAndView;
    }
}
