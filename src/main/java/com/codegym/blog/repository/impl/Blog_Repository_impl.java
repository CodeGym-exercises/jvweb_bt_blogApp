package com.codegym.blog.repository.impl;

import com.codegym.blog.model.Blog;
import com.codegym.blog.repository.Blog_repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class Blog_Repository_impl implements Blog_repository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> query = em.createQuery("select b from Blog b",Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(Long id) {
        TypedQuery<Blog> query = em.createQuery("select b from Blog b where b.id=:id",Blog.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public void save(Blog model) {
        if(model.getId() != null){
            em.merge(model);
        } else {
            em.persist(model);
        }
    }

    @Override
    public void remove(Long id) {
        Blog blog = findById(id);
        if(blog != null){
            em.remove(blog);
        }
    }
}
