package com.SamuelBusses.ExpensesTracker.Repositories;

import com.SamuelBusses.ExpensesTracker.Models.Expenses;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends CrudRepository <Expenses, Long> {



}
