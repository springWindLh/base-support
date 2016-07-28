package lh.email.main;

import org.apache.commons.mail.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by lh on 2016/5/23.
 */
public class Mail {
    private static String host = "smtp.163.com";
    private static int port;
    private static String sender = "luohuan0717@163.com";
    private static String senderName = "测试网站";
    private static String userName = "luohuan0717@163.com";
    private static String password = "297750341lh";
    private static final String PARAM_MARK = "\\{\\}";

    public static void send(String subject, String msg, String receiver) throws EmailException {
        Email email = getSimpleEmail();
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(receiver);
        email.send();
    }

    public static void send(String subject, String msg, List<String> recivies) throws EmailException {
        Email email = getSimpleEmail();
        email.setSubject(subject);
        email.setMsg(msg);
        email.setTo(recivies);
        email.send();
    }

    public static void send(String subject, String msg, String receiver, String... params) throws EmailException {
        Email email = getSimpleEmail();
        email.setSubject(subject);
        email.setMsg(translateMsg(msg, params));
        email.addTo(receiver);
        email.send();
    }

    public static void send(String subject, String msg, List<String> recivies, String... params) throws EmailException {
        Email email = getSimpleEmail();
        email.setSubject(subject);
        email.setMsg(translateMsg(msg, params));
        email.setTo(recivies);
        email.send();
    }

    public static void sendWithAttachment(String subject, String msg, String receiver, EmailAttachment attachment) throws EmailException {
        MultiPartEmail email = getMultiPartEmail();
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(receiver);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        email.attach(attachment);
        email.send();
    }

    public static void sendWithAttachment(String subject, String msg, List<String> recivies, EmailAttachment attachment) throws EmailException {
        MultiPartEmail email = getMultiPartEmail();
        email.setSubject(subject);
        email.setTo(recivies);
        email.setMsg(msg);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        email.attach(attachment);
        email.send();
    }

    public static void sendWithAttachment(String subject, String msg, String receiver, EmailAttachment attachment, String... params) throws EmailException {
        MultiPartEmail email = getMultiPartEmail();
        email.setSubject(subject);
        email.setMsg(translateMsg(msg, params));
        email.addTo(receiver);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        email.attach(attachment);
        email.send();
    }

    public static void sendWithAttachment(String subject, String msg, List<String> recivies, EmailAttachment attachment, String... params) throws EmailException {
        MultiPartEmail email = getMultiPartEmail();
        email.setSubject(subject);
        email.setMsg(translateMsg(msg, params));
        email.setTo(recivies);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        email.attach(attachment);
        email.send();
    }

    public static void sendWithHtml(String subject, String htmlMsg, String receiver) throws EmailException {
        HtmlEmail email = getHtmlEmail();
        email.setSubject(subject);
        email.setHtmlMsg(translateHtmlMsg(htmlMsg));
        email.addTo(receiver);
        email.send();
    }

    public static void sendWithHtml(String subject, String htmlMsg, String receiver, String... params) throws EmailException {
        HtmlEmail email = getHtmlEmail();
        email.setSubject(subject);
        email.setHtmlMsg(translateHtmlMsg(htmlMsg, params));
        email.addTo(receiver);
        email.send();
    }

    public static void sendWithHtml(String subject, String htmlMsg, List<String> recivies) throws EmailException {
        HtmlEmail email = getHtmlEmail();
        email.setSubject(subject);
        email.setHtmlMsg(translateHtmlMsg(htmlMsg));
        email.setTo(recivies);
        email.send();
    }

    public static void sendWithHtml(String subject, String htmlMsg, List<String> recivies, String... params) throws EmailException {
        HtmlEmail email = getHtmlEmail();
        email.setSubject(subject);
        email.setHtmlMsg(translateHtmlMsg(htmlMsg, params));
        email.setTo(recivies);
        email.send();
    }

    private static Email getSimpleEmail() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(host);
        email.setFrom(sender, senderName);
        email.setAuthentication(userName, password);
        email.setCharset("utf-8");
        email.setSSL(true);
        return email;
    }

    private static MultiPartEmail getMultiPartEmail() throws EmailException {
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(host);
        email.setFrom(sender, senderName);
        email.setAuthentication(userName, password);
        email.setCharset("utf-8");
        email.setSSL(true);
        return email;
    }

    private static HtmlEmail getHtmlEmail() throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(host);
        email.setFrom(sender, senderName);
        email.setAuthentication(userName, password);
        email.setCharset("utf-8");
        email.setSSL(true);
        return email;
    }

    private static String translateHtmlMsg(String htmlMsg, String... params) {
        if (!Optional.ofNullable(params).isPresent() || params.length == 0) {
            String mark = PARAM_MARK.replaceAll("\\\\", "");
            if (htmlMsg.contains(mark)) {
                return htmlMsg.replaceAll(PARAM_MARK, "");
            }
            return htmlMsg;
        } else {
            if (!checkParams(htmlMsg, params)) {
                throw new IllegalArgumentException("params mismatch");
            }
            String[] msgs = htmlMsg.split(PARAM_MARK);
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < params.length; i++) {
                buffer.append(msgs[i]).append(params[i]);
            }
            if (msgs.length - params.length == 1) {
                buffer.append(msgs[params.length]);
            }
            return buffer.toString();
        }
    }

    private static String translateMsg(String msg, String... params) {
        if (!checkParams(msg, params)) {
            throw new IllegalArgumentException("params mismatch");
        }
        String[] msgs = msg.split(PARAM_MARK);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < params.length; i++) {
            buffer.append(msgs[i]).append(params[i]);
        }
        if (msgs.length - params.length == 1) {
            buffer.append(msgs[params.length]);
        }
        return buffer.toString();
    }

    private static boolean checkParams(String msg, String... params) {
        int count;
        if (Optional.ofNullable(msg).isPresent() && !msg.isEmpty()) {
            count = msg.split(PARAM_MARK).length;
            String mark = PARAM_MARK.replaceAll("\\\\", "");
            if (msg.length() - msg.lastIndexOf(mark) == mark.length()) {
                return count == params.length;
            } else {
                return count - 1 == params.length;
            }
        } else {
            throw new IllegalArgumentException("msg must not be null or empty");
        }
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        Mail.host = host;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Mail.port = port;
    }

    public static String getSender() {
        return sender;
    }

    public static void setSender(String sender) {
        Mail.sender = sender;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Mail.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Mail.password = password;
    }

}
