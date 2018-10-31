package com.codegym.blog.service;


import com.codegym.blog.model.Blog;

import java.util.List;

public interface Blog_service {
    List<Blog> findAll();

    Blog findById(Long id);

    void save(Blog blog);

    void remove(Long id);
}
