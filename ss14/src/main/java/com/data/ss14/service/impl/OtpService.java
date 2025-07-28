package com.data.ss14.service.impl;

import com.data.ss14.model.entity.User;
import com.data.ss14.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    public void generateAndSendOtp(User user) {
        String otp = String.format("%06d", new Random().nextInt(1000000));
        user.setOtp(otp);
        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);
    }
    public void sendOtp(String to, String otp) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Your OTP Code");
        msg.setText("Your OTP is: " + otp);
        mailSender.send(msg);
    }

    public boolean verifyOtp(User user, String otp) {
        return otp.equals(user.getOtp());
    }


}
