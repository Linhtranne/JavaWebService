package com.data.service;

import com.data.entity.Account;
import com.data.entity.Status;
import com.data.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    public Account addAccount(Account account) {
        account.setId(null);
        account.setStatus(Status.ACTIVE);
        return accountRepository.save(account);
    }

    public Account updateAccount(UUID id, Account updated) {
        Account old = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));

        log.info("Tài khoản cũ: {}", old);
        log.info("Tài khoản mới: {}", updated);

        updated.setId(id);
        return accountRepository.save(updated);
    }

    public void closeAccount(UUID id) {
        Account acc = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));

        acc.setStatus(Status.INACTIVE);
        accountRepository.save(acc);
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account findByCccd(String cccd) {
        return accountRepository.findByCccd(cccd)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản với CCCD: " + cccd));
    }
    public Account findByAccountId(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản với ID: " + accountId));
    }
}