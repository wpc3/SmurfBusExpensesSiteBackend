package com.SamuelBusses.ExpensesTracker.Repositories;

import com.SamuelBusses.ExpensesTracker.Models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository <Account, Long> {

    boolean existsByUsername(String username);
      Optional<Account> findByUsername(String username);
}
