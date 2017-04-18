package com.board.gd.mail;

import org.springframework.mail.MailException;

import java.util.List;

/**
 * Created by gd.godong9 on 2017. 4. 18.
 */
public interface MailService {
    void send(MailMessage message) throws MailException;

    void send(List<MailMessage> messages) throws MailException;
}
