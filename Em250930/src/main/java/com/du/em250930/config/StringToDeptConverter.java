package com.du.em250930.config;

import com.du.em250930.entity.Dept;
import com.du.em250930.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

// 문자열로 들어온 객체를 dept 객체로 comverter 해주겠다.
@Component
@RequiredArgsConstructor
public class StringToDeptConverter implements Converter<String, Dept> {

    private final DeptRepository deptRepository;

    @Override
    public Dept convert(String source) {
        try{
            Long id = Long.parseLong(source);
            return deptRepository.findById(id);

        }catch (Exception e){

            return null;
        }
    }
}
