package com.data.service;

import com.data.dto.TransactionCreditDTO;
import com.data.entity.*;
import com.data.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionCreditService {

    private final TransactionCreditRepository transactionCreditRepository;
    private final CreditCardRepository creditCardRepository;
    private final AccountRepository accountRepository;
    private final NotificationRepository notificationRepository;
    private final MailService mailService;

    public TransactionCredit processCreditTransaction(TransactionCreditDTO dto) {
        Account receiver = accountRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản nhận"));

        CreditCard card = creditCardRepository.findById(dto.getCreditCardId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thẻ tín dụng"));

        TransactionCredit transaction = new TransactionCredit();
        transaction.setAccountReceiver(receiver);
        transaction.setCreditCardSender(card);
        transaction.setNote(dto.getNote());
        transaction.setMoney(dto.getMoney());

        double futureSpent = card.getAmountSpent() + dto.getMoney();
        if (futureSpent > card.getSpendingLimit()) {
            transaction.setStatus("thất bại");
            transactionCreditRepository.save(transaction);
            log.warn("Giao dịch thất bại: Vượt hạn mức thẻ tín dụng.");
            return transaction;
        }

        card.setAmountSpent(futureSpent);
        creditCardRepository.save(card);

        transaction.setStatus("thành công");
        transactionCreditRepository.save(transaction);

        sendNotification(card.getAccount(), "- " + dto.getMoney() + " đ từ thẻ tín dụng.");
        sendNotification(receiver, "+ " + dto.getMoney() + " đ từ thẻ tín dụng.");

        return transaction;
    }

    private void sendNotification(Account account, String content) {
        Notification noti = new Notification();
        noti.setAccount(account);
        noti.setContent(content);
        noti.setStatus("chưa đọc");
        noti.setCreatedAt(java.time.LocalDateTime.now());
        notificationRepository.save(noti);
    }
    @Scheduled(cron = "0 0 12 20 * ?") // 12:00 trưa ngày 20 mỗi tháng
    public void sendMonthlyReport() {
        List<CreditCard> allCards = creditCardRepository.findAll();

        YearMonth currentMonth = YearMonth.now();
        LocalDateTime start = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime end = currentMonth.atEndOfMonth().atTime(23, 59, 59);

        for (CreditCard card : allCards) {
            Account account = card.getAccount();
            List<TransactionCredit> txs = transactionCreditRepository
                    .findAllByCreditCardSenderAndCreatedAtBetween(card, start, end);

            double total = txs.stream()
                    .filter(tx -> "thành công".equals(tx.getStatus()))
                    .mapToDouble(TransactionCredit::getMoney)
                    .sum();

            Notification noti = new Notification();
            noti.setAccount(account);
            noti.setContent("Tổng chi tiêu tháng " + currentMonth.getMonthValue() + ": " + total + " đ");
            noti.setStatus("chưa đọc");
            noti.setCreatedAt(LocalDateTime.now());
            notificationRepository.save(noti);

            StringBuilder report = new StringBuilder();
            report.append("<h3>Báo cáo chi tiêu tháng ").append(currentMonth.getMonthValue()).append("</h3>");
            report.append("<table border='1' cellpadding='5'><tr><th>Ngày</th><th>Số tiền</th><th>Ghi chú</th><th>Trạng thái</th></tr>");
            for (TransactionCredit tx : txs) {
                report.append("<tr>")
                        .append("<td>").append(tx.getId()).append("</td>")
                        .append("<td>").append(tx.getMoney()).append("</td>")
                        .append("<td>").append(tx.getNote()).append("</td>")
                        .append("<td>").append(tx.getStatus()).append("</td>")
                        .append("</tr>");
            }
            report.append("</table>");

            try {
                mailService.send(account.getEmail(), "Báo cáo chi tiêu tháng " + currentMonth.getMonthValue(), report.toString());
            } catch (Exception e) {
                log.error("Lỗi gửi email cho account " + account.getEmail(), e);
            }
        }
    }

}
