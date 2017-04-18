package com.board.gd.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gd.godong9 on 2017. 4. 18.
 */

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void send(MailMessage message) throws MailException {
        mailSender.send(message);
    }

    @Override
    public void send(List<MailMessage> messages) throws MailException {
        messages.forEach(mailMessage -> mailSender.send(mailMessage));
    }
}
