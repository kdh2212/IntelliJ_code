package com.du.baknfrnt1.controller;

import com.du.baknfrnt1.model.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/memos")
public class MemoController {

    private final List<Memo> memos = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    @GetMapping
    public List<Memo> getAllMemos() {
        return memos;
    }

    @PostMapping
    public Memo addMemo(@RequestBody Memo memo) {
        System.out.println("------------>" + memo);
        Memo newMemo = new Memo(counter.getAndIncrement(), memo.getContent());
        memos.add(newMemo);
        return newMemo;
    }
}

