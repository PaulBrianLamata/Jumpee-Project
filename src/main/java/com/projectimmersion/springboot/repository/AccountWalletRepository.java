package com.projectimmersion.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectimmersion.springboot.model.AccountWallet;
@Repository("accountWalletRepository")
public interface AccountWalletRepository extends JpaRepository<AccountWallet, Long> {

}
