package com.tipeaky.peakystore.services;

import com.tipeaky.peakystore.model.dtos.EmailDTO;
import com.tipeaky.peakystore.model.entities.Email;
import com.tipeaky.peakystore.model.enums.StatusEmailEnum;
import com.tipeaky.peakystore.model.forms.EmailForm;
import com.tipeaky.peakystore.repositories.EmailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    ModelMapper mapper;

    public EmailDTO sendEmail(EmailForm emailForm) {

        Email email = mapper.map(emailForm, Email.class);
        email.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(email.getEmailFrom());
            simpleMailMessage.setTo(email.getEmailTo());
            simpleMailMessage.setSubject(email.getSubject());
            simpleMailMessage.setText(email.getText());

            javaMailSender.send(simpleMailMessage);

            email.setStatusEmail(StatusEmailEnum.SEND);
        } catch (MailException mailException) {
            email.setStatusEmail(StatusEmailEnum.ERROR);
        } finally {
            emailRepository.save(email);
            EmailDTO emailDTO = mapper.map(email, EmailDTO.class);
            return emailDTO;
        }

    }

}
