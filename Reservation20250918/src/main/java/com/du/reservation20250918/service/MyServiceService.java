package com.du.reservation20250918.service;



import com.du.reservation20250918.dao.ServiceMapper;
import com.du.reservation20250918.domain.MyService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class MyServiceService {
    @Autowired
    private ServiceMapper serviceMapper;

    public List<MyService> getAllServices() {
        return serviceMapper.findAll();
    }

    public void addService(MyService service) {
        serviceMapper.insert(service);
    }

    public void deleteService(Long id) {
        serviceMapper.delete(id);
    }
}

