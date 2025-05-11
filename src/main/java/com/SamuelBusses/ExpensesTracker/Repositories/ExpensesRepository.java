package com.SamuelBusses.ExpensesTracker.Repositories;

import com.SamuelBusses.ExpensesTracker.CustomQueryInterfaces.CostOfExpenseByDate;
import com.SamuelBusses.ExpensesTracker.Models.Expenses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepository extends CrudRepository <Expenses, Long> {

@Query(value = "SELECT category, SUM(cost) AS totalCost FROM expenses WHERE month = ?1 AND year = ?2 GROUP BY category", nativeQuery = true )
List<CostOfExpenseByDate> findSumOfExpensesByMonthAndYear(int month, int year);

@Query(value = "SELECT category, SUM(cost) AS totalCost FROM expenses WHERE year = ?1 GROUP BY category", nativeQuery = true)
List<CostOfExpenseByDate> findSumOfExpensesByYear(int year);

@Query(value = "SELECT * FROM expenses WHERE category = ?", nativeQuery = true)
 List<Expenses> findExpensesByCategory(String category);

@Query(value = "SELECT DISTINCT e.category FROM expenses e;" , nativeQuery = true)
 List<String> findAllCategories();

}
