package com.cuttingEdge.bunan.service;


import com.cuttingEdge.bunan.entity.EmailVerificationInfo;
import com.cuttingEdge.bunan.exception.AppException;
import com.cuttingEdge.bunan.exception.ErrorCode;
import com.cuttingEdge.bunan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final MemberService memberService;

    @Value("${spring.mail.username}")
    private String senderEmail;

    private final ConcurrentHashMap<String, EmailVerificationInfo> verificationMap = new ConcurrentHashMap<>();

    public void sendMail(String mail){
        MimeMessage message = CreateMail(mail);
//        TODO map 에서 email 이 이미 있는 경우에는?
        javaMailSender.send(message);

    }
    public boolean verifyEmail(String email, Integer code) {
        return verifyCode(email, code);
    }

    public MimeMessage CreateMail(String email){

        memberService.emailCheck(email);

        createVerificationCode(email);

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("[이메일 인증] 번안 사이트의 인증번호 입니다.");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + verificationMap.get(email).getVerificationCode() + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }



    public boolean checkCode(EmailVerificationInfo info,Integer inputCode) {
        if (info.getVerificationCode() == inputCode &&
                LocalDateTime.now().isBefore(info.getIssueTime().plusMinutes(30))) {
            info.verify();
            return true;
        }
        throw new AppException(ErrorCode.INVALID_EMAIL_CODE, "유효하지 않은 인증번호입니다.");
    }
    public void createVerificationCode(String email) {
        int code = (int)(Math.random() * 900000) + 100000;
        verificationMap.put(email, new EmailVerificationInfo(code));
        // 이메일로 code를 발송하는 로직 추가
    }
    public boolean verifyCode(String email, int code) {
        EmailVerificationInfo info = verificationMap.get(email);
        if (info != null) {
            return checkCode(info ,code);
        }
        return false;
    }
}
//출처 https://velog.io/@kjh950330/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85-%EC%9D%B4%EB%A9%94%EC%9D%BC-%EC%9D%B8%EC%A6%9D-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84