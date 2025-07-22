package com.data.service;

import com.data.dto.TransactionDTO;
import com.data.entity.Account;
import com.data.entity.Notification;
import com.data.entity.Transaction;
import com.data.repository.AccountRepository;
import com.data.repository.NotificationRepository;
import com.data.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final NotificationRepository notificationRepository;

    public void processTransaction(TransactionDTO dto) {
        if (dto.getSenderId() == null || dto.getReceiverId() == null) {
            throw new IllegalArgumentException("Sender và Receiver không được null");
        }

        Account sender = accountRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tài khoản gửi"));

        Account receiver = accountRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tài khoản nhận"));

        if (sender.getMoney() < dto.getMoney()) {
            saveTransaction(dto, sender, receiver, "thất bại");
            throw new IllegalArgumentException("Số dư không đủ");
        }

        // Cập nhật số dư
        sender.setMoney(sender.getMoney() - dto.getMoney());
        receiver.setMoney(receiver.getMoney() + dto.getMoney());

        accountRepository.save(sender);
        accountRepository.save(receiver);

        // Lưu giao dịch
        saveTransaction(dto, sender, receiver, "thành công");

        // Gửi thông báo
        createNotification(sender, "Bạn đã chuyển " + dto.getMoney() + " VND. Số dư hiện tại: " + sender.getMoney());
        createNotification(receiver, "Bạn nhận được " + dto.getMoney() + " VND. Số dư hiện tại: " + receiver.getMoney());
    }

    private void saveTransaction(TransactionDTO dto, Account sender, Account receiver, String status) {
        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setMoney(dto.getMoney());
        transaction.setNote(dto.getNote());
        transaction.setStatus(status);
        transaction.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now());

        transactionRepository.save(transaction);
    }

    private void createNotification(Account account, String content) {
        Notification noti = new Notification();
        noti.setAccount(account);
        noti.setContent(content);
        noti.setStatus("chưa đọc");
        noti.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(noti);
    }
}

