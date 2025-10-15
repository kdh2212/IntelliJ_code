package com.du.accountproj251001.controller;

import com.du.accountproj251001.entity.Expense;
import com.du.accountproj251001.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseRepository expenseRepository;

    // ✅ 특정 사용자 지출 내역 조회
    @GetMapping("/user/{userId}")
    @ResponseBody
    public ResponseEntity<List<Expense>> getExpensesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(expenseRepository.findByUserId(userId));
    }

    // ✅ 특정 사용자 + 월별 지출 조회
    @GetMapping("/user/{userId}/month")
    @ResponseBody
    public ResponseEntity<List<Expense>> getMonthlyExpenses(
            @PathVariable Long userId,
            @RequestParam int year,
            @RequestParam int month
    ) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<Expense> expenses = expenseRepository.findByUserIdAndDateBetween(userId, start, end);
        return ResponseEntity.ok(expenses);
    }

    // ✅ 지출 등록
    @PostMapping
    @ResponseBody
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense saved = expenseRepository.save(expense);
        return ResponseEntity.ok(saved);
    }

    // ✅ 지출 단건 조회 (수정화면에 필요)
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Expense> getExpense(@PathVariable Long id) {
        return expenseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ 지출 수정
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Expense> updateExpense(
            @PathVariable Long id,
            @RequestBody Expense updatedExpense
    ) {
        return expenseRepository.findById(id)
                .map(expense -> {
                    expense.setDate(updatedExpense.getDate());
                    expense.setAmount(updatedExpense.getAmount());
                    expense.setCategory(updatedExpense.getCategory());
                    expense.setMemo(updatedExpense.getMemo());
                    Expense saved = expenseRepository.save(expense);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ 지출 삭제
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        if (!expenseRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        expenseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
