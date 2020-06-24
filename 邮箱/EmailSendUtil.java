import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailSendUtil {
    private final static String host = "smtp.exmail.qq.com"; //163�ķ�����
    private final static String formName = "wsjkcxs@foxmail.com";//�������
    private final static String password = "hwnfrzzzgoigbhab"; //��Ȩ��
    private final static String replayAddress = "wsjkcxs@foxmail.com"; //�������


    public static void sendHtmlMail(EmailMailInfo info)throws Exception{
        info.setHost(host);
        info.setFormName(formName);
        info.setFormPassword(password); hwnfrzzzgoigbhab  //�����������Ȩ��~��һ��������
        info.setReplayAddress(replayAddress);

        Message message = getMessage(info);
        // MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ���
        Multipart mainPart = new MimeMultipart();
        // ����һ������HTML���ݵ�MimeBodyPart
        BodyPart html = new MimeBodyPart();
        // ����HTML����
        html.setContent(info.getContent(), "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        // ��MiniMultipart��������Ϊ�ʼ�����
        message.setContent(mainPart);
        Transport.send(message);
    }

    public static void sendTextMail(EmailMailInfo info) throws Exception {

        info.setHost(host);
        info.setFormName(formName);
        info.setFormPassword(password); hwnfrzzzgoigbhab  //�����������Ȩ��~��һ��������
        info.setReplayAddress(replayAddress);
        Message message = getMessage(info);
        //��Ϣ���͵�����
        message.setText(info.getContent());

        Transport.send(message);
    }

    private static Message getMessage(EmailMailInfo info) throws Exception{
        final Properties p = System.getProperties() ;
        p.setProperty("mail.smtp.host", info.getHost());
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.user", info.getFormName());
        p.setProperty("mail.smtp.pass", info.getFormPassword());

        // �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
        Session session = Session.getInstance(p, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(p.getProperty("mail.smtp.user"),p.getProperty("mail.smtp.pass"));
            }
        });
        session.setDebug(true);
        Message message = new MimeMessage(session);
        //��Ϣ���͵�����
        message.setSubject(info.getSubject());
        //������Ϣ����
        message.setReplyTo(InternetAddress.parse(info.getReplayAddress()));
        //��Ϣ�ķ�����
        message.setFrom(new InternetAddress(p.getProperty("mail.smtp.user"),"���Ǽ��ͳ�����"));
        // �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(info.getToAddress()));
        // ��Ϣ���͵�ʱ��
        message.setSentDate(new Date());


        return message ;
