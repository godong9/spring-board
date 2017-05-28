package com.board.gd.mail;

import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by gd.godong9 on 2017. 4. 18.
 */
public interface MailService {
    @Async
    void send(MailMessage message) throws MailException;

    @Async
    void send(List<MailMessage> messages) throws MailException;
}
