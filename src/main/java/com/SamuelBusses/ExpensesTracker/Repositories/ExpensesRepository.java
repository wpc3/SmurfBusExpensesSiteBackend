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

 @Query(value = "SELECT category, SUM(cost) AS totalCost FROM expenses WHERE month = ?1 AND year = ?2 AND account_id = ?3 GROUP BY category", nativeQuery = true )
 List<CostOfExpenseByDate> findSumOfExpensesByMonthAndYearAndAccount(int month, int year, long accountId);

@Query(value = "SELECT category, SUM(cost) AS totalCost FROM expenses WHERE year = ?1 GROUP BY category", nativeQuery = true)
List<CostOfExpenseByDate> findSumOfExpensesByYear(int year);

 @Query(value = "SELECT category, SUM(cost) AS totalCost FROM expenses WHERE year = ?1 AND account_id = ?2 GROUP BY category", nativeQuery = true)
 List<CostOfExpenseByDate> findSumOfExpensesByYearByAccount(int year, long accountId);

@Query(value = "SELECT * FROM expenses WHERE category = ?", nativeQuery = true)
 List<Expenses> findExpensesByCategory(String category);

 @Query(value = "SELECT * FROM expenses WHERE category = ?1 AND account_id = ?2", nativeQuery = true)
 List<Expenses> findExpensesByCategoryAndAccount(String category, long accountId);

@Query(value = "SELECT DISTINCT e.category FROM expenses e;" , nativeQuery = true)
 List<String> findAllCategories();

 @Query(value = "SELECT DISTINCT e.category FROM expenses e WHERE e.account_id = ?1;" , nativeQuery = true)
 List<String> findAllCategoriesByAccount(long accountId);

 List<Expenses> findAllExpensesByAccountId(long accountId);

}
