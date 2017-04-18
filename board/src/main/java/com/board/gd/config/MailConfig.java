package com.board.gd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by gd.godong9 on 2017. 4. 18.
 */

@Configuration
public class MailConfig {
    @Value("${mail.protocol}")
    private String protocol;

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.encoding}")
    private String encoding;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.smtp.start-tls-enable}")
    private String smtpStartTlsEnable;

    @Value("${mail.smtp.auth}")
    private String smtpAuth;

    @Value("${mail.debug}")
    private String debug;

    @Bean
    public JavaMailSender getMailSender()
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setProtocol(protocol);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setDefaultEncoding(encoding);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setJavaMailProperties(getMailProperties());
        return mailSender;
    }

    private Properties getMailProperties()
    {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.starttls.required", smtpStartTlsEnable);
        properties.setProperty("mail.smtp.starttls.enable", smtpStartTlsEnable);
        properties.setProperty("mail.smtp.auth", smtpAuth);
        properties.setProperty("mail.debug", debug);
        properties.setProperty("mail.smtp.socketFactory.port", String.valueOf(port));
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return properties;
    }
}
