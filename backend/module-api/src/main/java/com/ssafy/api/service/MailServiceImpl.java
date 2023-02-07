package com.ssafy.api.service;

import com.ssafy.api.request.MailDto;


import com.ssafy.api.request.UserEmailReq;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.entity.rdbms.User;
import com.ssafy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    private final JavaMailSender javaMailSender;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @Override
    public MailDto makeLinkMail(UserEmailReq userEmailReq) throws MessagingException {
        User user = userRepository.findByUserEmail(userEmailReq.getUserEmail()).orElseThrow(NoSuchElementException::new);
        MailDto mailDto = MailDto.builder()
                .toAddress(userEmailReq.getUserEmail())
                .subject("email 인증 링크입니다.")
                .content("<div>\n" +
                        "    <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #ffffff;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "    <tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
                        "      \n" +
                        "  \n" +
                        "  <div style=\"padding: 0px;background-color: transparent\">\n" +
                        "    <div style=\"Margin: 0 auto;min-width: 320px;max-width: 500px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n" +
                        "      <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                        "  <div style=\"max-width: 320px;min-width: 500px;display: table-cell;vertical-align: top;\">\n" +
                        "    <div style=\"height: 100%; width: 100% !important;\">\n" +
                        "<div style=\"height: 100%; padding: 0px; border-width: 0px; border-style: solid; border-color: transparent; box-sizing: border-box;\">\n" +
                        "    \n" +
                        "  <table style=\"font-family:arial,helvetica,sans-serif;\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                        "    <tbody><tr><td align=\"left\" style=\"overflow-wrap:break-word;word-break:break-word;padding:50px 20px 40px;font-family:arial,helvetica,sans-serif;\">\n" +
                        "          \n" +
                        "  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                        "    <tbody><tr><td align=\"left\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                        "        \n" +
                        "      </td></tr></tbody></table>\n" +
                        "  \n" +
                        "        </td></tr></tbody>\n" +
                        "  </table>\n" +
                        "  \n" +
                        "  <table style=\"font-family:arial,helvetica,sans-serif;\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                        "    <tbody><tr><td align=\"left\" style=\"overflow-wrap:break-word;word-break:break-word;padding:0px 20px 30px;font-family:arial,helvetica,sans-serif;\">\n" +
                        "          \n" +
                        "    <h1 style=\"margin: 0px; line-height: 140%; text-align: left; word-wrap: break-word; font-weight: normal; font-family: arial,helvetica,sans-serif; font-size: 28px;\"><strong>[Agora] 고객님의<br>임시비밀번호입니다.</strong></h1>\n" +
                        "  \n" +
                        "        </td></tr></tbody>\n" +
                        "  </table>\n" +
                        "  </div>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "  \n" +
                        "  \n" +
                        "  \n" +
                        "  <div style=\"padding: 0px;background-color: transparent\">\n" +
                        "    <div style=\"Margin: 0 auto;min-width: 320px;max-width: 500px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n" +
                        "      <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                        "  <div style=\"max-width: 320px;min-width: 500px;display: table-cell;vertical-align: top;\">\n" +
                        "    <div style=\"height: 100%; width: 100% !important; border-radius: 0px;\">\n" +
                        "<div style=\"height: 100%; padding: 0px; border-width: 0px; border-style: solid; border-color: transparent; border-radius: 0px; box-sizing: border-box;\">\n" +
                        "    \n" +
                        "  <table style=\"font-family:arial,helvetica,sans-serif;\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                        "    <tbody><tr><td align=\"left\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 20px 20px;font-family:arial,helvetica,sans-serif;\">\n" +
                        "          \n" +
                        "    <div style=\"line-height: 140%; text-align: left; overflow-wrap: break-word; height: 100%;\">\n" +
                        "      <p style=\"font-size: 14px; line-height: 140%; margin: 0px;\"><span style=\"font-size: 16px; line-height: 22.4px;\">안녕하세요 "+user.getName()+" 님</span></p>\n" +
                        "  <p style=\"font-size: 14px; line-height: 140%; margin: 0px;\"><span style=\"font-size: 16px; line-height: 22.4px;\">요청하신 비밀번호 변경 링크를 알려드립니다.</span></p>\n" +
                        "  <p style=\"font-size: 14px; line-height: 140%; margin: 0px;\">&nbsp;</p>\n" +
                        "  <p style=\"font-size: 14px; line-height: 140%; margin: 0px;\"><span style=\"font-size: 16px; line-height: 22.4px;\">고객님의 개인정보 보호를 위해서 Agora 웹사이트에 로그인을 하신 후 설정 페이지에서 발급 받으신 임시비밀번호를 새로운 비밀번호로 재설정한 후에 이용해 주세요.</span></p>\n" +
                        "    </div>\n" +
                        "  \n" +
                        "        </td></tr></tbody>\n" +
                        "  </table>\n" +
                        "  \n" +
                        "  <table style=\"font-family:arial,helvetica,sans-serif;\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                        "    <tbody><tr><td align=\"left\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 20px;font-family:arial,helvetica,sans-serif;\">\n" +
                        "          \n" +
                        "    <table align=\"center\" height=\"0px\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #d7dee3;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                        "      <tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                        "            <span>&nbsp;</span>\n" +
                        "          </td></tr></tbody>\n" +
                        "    </table>\n" +
                        "  \n" +
                        "        </td></tr></tbody>\n" +
                        "  </table>\n" +
                        "  \n" +
                        "  <table style=\"font-family:arial,helvetica,sans-serif;\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                        "    <tbody><tr><td align=\"left\" style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 20px 16px;font-family:arial,helvetica,sans-serif;\">\n" +
                        "          \n" +
                        "    <div style=\"line-height: 140%; text-align: center; overflow-wrap: break-word; height: 100%;\">\n" +
                        "  <p style=\"font-size: 14px; line-height: 140%; margin: 0px;\"><strong><span style=\"font-size: 24px; line-height: 33.6px;\">"+
                        "</span></strong></p>\n" +
                        "    </div>\n" +
                        "  \n" +
                        "        </td></tr></tbody>\n" +
                        "  </table>\n" +
                        "  \n" +
                        "  <table style=\"font-family:arial,helvetica,sans-serif;\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                        "    <tbody><tr><td align=\"left\" style=\"overflow-wrap:break-word;word-break:break-word;padding:0px 20px 20px;font-family:arial,helvetica,sans-serif;\">\n" +
                        "            <div align=\"center\" style=\"height: 100%;\">\n" +
                        "      <a href=\" "+ "localhost:8080/user/password?"+"authorization="+jwtTokenUtil.createAccessToken(userEmailReq.getUserEmail())+ '"' +" target=\"_blank\" style=\"box-sizing: border-box;display: inline-block;font-family:arial,helvetica,sans-serif;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #ffffff; background-color: #ff6905; border-radius: 4px;-webkit-border-radius: 4px; -moz-border-radius: 4px; :auto; max-:100%; overflow-wrap: break-word; word-break: break-word; word-wrap:break-word; mso-border-alt: none;\" rel=\"noreferrer noopener\">\n" +
                        "        <span style=\"display:block;padding:10px 16px;line-height:120%;\"><strong><span style=\"font-size: 16px; line-height: 19.2px;\">비밀번호 변경하기 &gt;</span></strong></span>\n" +
                        "      </a>\n" +
                        "  </div>\n" +
                        "  \n" +
                        "        </td></tr></tbody>\n" +
                        "  </table>\n" +
                        "  \n" +
                        "  <table style=\"font-family:arial,helvetica,sans-serif;\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                        "    <tbody><tr><td align=\"left\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 20px;font-family:arial,helvetica,sans-serif;\">\n" +
                        "          \n" +
                        "    <table align=\"center\" height=\"0px\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #d7dee3;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                        "      <tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                        "            <span>&nbsp;</span>\n" +
                        "          </td></tr></tbody>\n" +
                        "    </table>\n" +
                        "  \n" +
                        "        </td></tr></tbody>\n" +
                        "  </table>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "  <div style=\"padding: 0px;background-color: transparent\">\n" +
                        "    <div style=\"Margin: 0 auto;min-width: 320px;max-width: 500px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n" +
                        "      <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                        "        <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"padding: 0px;background-color: transparent;\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:500px;\"><tr style=\"background-color: transparent;\"><![endif]--><!--[if (mso)|(IE)]><td align=\"center\" valign=\"top\" width=\"500\" style=\"width: 500px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><![endif]-->\n" +
                        "  <div style=\"max-width: 320px;min-width: 500px;display: table-cell;vertical-align: top;\">\n" +
                        "    <div style=\"height: 100%; width: 100% !important; border-radius: 0px;\">\n" +
                        "    <!--[if (!mso)&(!IE)]><!-- --><div style=\"height: 100%; padding: 0px; border-width: 0px; border-style: solid; border-color: transparent; border-radius: 0px; box-sizing: border-box;\"><!--&lt;![endif]-->\n" +
                        "    \n" +
                        "  <table style=\"font-family:arial,helvetica,sans-serif;\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                        "    <tbody><tr><td align=\"left\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 20px 20px;font-family:arial,helvetica,sans-serif;\">\n" +
                        "          \n" +
                        "    <div style=\"line-height: 140%; text-align: left; overflow-wrap: break-word; height: 100%;\">\n" +
                        "  \n" +
                        "  <p style=\"font-size: 14px; line-height: 140%; margin: 0px;\"><span style=\"font-size: 16px; line-height: 22.4px;\">감사합니다.</span></p>\n" +
                        "  <p style=\"font-size: 14px; line-height: 140%; margin: 0px;\"><span style=\"font-size: 16px; line-height: 22.4px;\">Agora 고객센터 드림</span></p>\n" +
                        "    </div>\n" +
                        "        </td></tr></tbody>\n" +
                        "  </table>\n" +
                        "</div>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "  </td>\n" +
                        "  </tr>\n" +
                        "  </tbody>\n" +
                        "  </table>\n" +
                        "  </div>\n" +
                        "  ").build();
        return mailDto;
    }

    @Override
    public void sendMail(MailDto mailDto) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(mailDto.getToAddress());
            helper.setSubject(mailDto.getSubject());
            helper.setText(mailDto.getContent(),true);

            javaMailSender.send(message);

    }
}
