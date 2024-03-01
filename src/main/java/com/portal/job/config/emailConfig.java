package com.portal.job.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class emailConfig {

    private String host;
    private int port;
    private String username;
    private String password;
    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    @Bean
    public JavaMailSender javaMailSender() {
        mailSender.setHost("smtp.gmail.com");  // Use the value from configuration
        mailSender.setPort(587);  // Use the value from configuration
        mailSender.setUsername("pankajkhotre@gmail.com");  // Use the value from configuration
        mailSender.setPassword("your password");  // Use the value from configuration

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS

        return mailSender;
    }

    // Getter and Setter methods for host, port, username, password
}
