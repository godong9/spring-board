package com.board.gd.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gd.godong9 on 2017. 4. 18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTests {
    @MockBean
    private MailService mailService;

    @Test
    public void success_send() {
        // given
        MailMessage mailMessage = new MailMessage();
        mailMessage.setFrom("dev.godong9@gmail.com");
        mailMessage.setTo("godong9@gmail.com");
        mailMessage.setSubject("메일 테스트");
        mailMessage.setText("메일 테스트 내용");
        mailMessage.setEncoding("UTF-8");

        // when
        mailService.send(mailMessage);
    }

    @Test
    public void success_send_list() {
        // given
        List<MailMessage> mailMessageList = new ArrayList();
        MailMessage mailMessage = new MailMessage();
        mailMessage.setFrom("dev.godong9@gmail.com");
        mailMessage.setTo("godong9@gmail.com");
        mailMessage.setSubject("메일 테스트");
        mailMessage.setText("메일 테스트 내용");
        mailMessage.setEncoding("UTF-8");

        mailMessageList.add(mailMessage);
        mailMessageList.add(mailMessage);

        // when
        mailService.send(mailMessageList);
    }
}
