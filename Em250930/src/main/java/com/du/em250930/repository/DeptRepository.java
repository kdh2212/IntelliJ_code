package com.du.em250930.repository;

import com.du.em250930.entity.Dept;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeptRepository  {

    @PersistenceContext
    private EntityManager em;

    public void save(Dept dept){
        em.persist(dept);
    }

    public Dept findById(Long id){
        return em.find(Dept.class, id);
    }

    public List<Dept> findAll(){
        return em.createQuery("Select d from Dept d", Dept.class).getResultList();
    }
}
