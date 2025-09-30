package com.du.em250930.service;

import com.du.em250930.entity.Dept;
import com.du.em250930.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class DeptService {
    private final DeptRepository deptRepository;

    public void save(Dept dept){
        deptRepository.save(dept);
    }

    public Dept getById(Long id){
        return deptRepository.findById(id);
    }

    public List<Dept> getAll(){
        return deptRepository.findAll();
    }
}
