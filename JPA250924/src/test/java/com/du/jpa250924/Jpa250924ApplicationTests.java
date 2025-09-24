package com.du.jpa250924;

import com.du.jpa250924.entity.MyData;
import com.du.jpa250924.repository.MyDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class Jpa250924ApplicationTests {

    @Autowired
    private MyDataRepository myDataRepository;


    @Test
    void contextLoads() { // insert
        MyData myData = MyData.builder()
                .name("홍길순")
                .age(26)
                .email("hong2@korea.com")
                .memo("연습입니다.2")
                .build();
        myDataRepository.save(myData);

    }

    @Test
    void contextLoads_findById(){ // select One
        Optional<MyData> myData = myDataRepository.findById(1L);
        System.out.println(myData.get().getName());
    }

    @Test
    void contextLoads_findAll(){
        List<MyData> myData = myDataRepository.findAll();
        for (MyData myData1 : myData) {
            System.out.println(myData1);
        }
    }
}
