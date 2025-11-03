package com.du.accountproj251001.repository;

import com.du.accountproj251001.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // 🔍 특정 사용자 ID로 지출 내역 조회
    List<Expense> findByUserId(Long userId);

    // 🔍 특정 날짜 이후의 지출 내역 조회
    List<Expense> findByUserIdAndDateAfter(Long userId, LocalDate date);

    // 🔍 카테고리별 지출 조회
    List<Expense> findByUserIdAndCategory(Long userId, String category);

    // 🔍 특정 월 범위 내 지출 조회 (예: 2025년 10월)
    List<Expense> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT DATE_FORMAT(date, '%Y-%m') AS month, SUM(amount) AS total " +
            "FROM expenses WHERE user_id = :userId " +
            "GROUP BY DATE_FORMAT(date, '%Y-%m') " +
            "ORDER BY month", nativeQuery = true)
    List<Object[]> getMonthlyExpenseSummary(@Param("userId") Long userId);




}
