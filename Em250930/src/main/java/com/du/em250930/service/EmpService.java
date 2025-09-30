package com.du.em250930.service;

import com.du.em250930.entity.Emp;
import com.du.em250930.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class EmpService {
    private final EmpRepository empRepository;


    public void save(Emp emp) {
        empRepository.save(emp);
    }

    public Emp getById(Long id) {
        return empRepository.findById(id);
    }
    public List<Emp> getAll() {
        return empRepository.findAll();
    }


    public void update(Emp emp) {
        empRepository.update(emp);
    }

    public void delete(Long id) {
        empRepository.delete(id);
    }
}
