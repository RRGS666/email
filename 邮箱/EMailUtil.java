import javax.servlet.http.HttpSession;


/** 
 * ClassName: EMailUtil.java <br>
 * Description: <br>
 * Create by: name：yuxin <br>email: yuruixin_china@163.com <br>
 * Create Time: 2017年6月15日<br>
 */
public class EMailUtil {

    /** 
     * Description：发送注册邮件
     * @param email
     * @param session
     * @return
     * @return String
     * @author name：yuxin <br>email: yuruixin_china@163.com
     **/
    public static String sendForReg(String email,HttpSession session) {
        String captcha = CaptchaUtils.creatCaptcha();
        session.setAttribute("reg:"+email, captcha);
        String title = "用户注册";
        String content = EmailTemplet.getHtml(title, email, title, captcha);
        EmailMailInfo info = new EmailMailInfo();
        info.setToAddress(email);
        info.setSubject(title);
        info.setContent(content);
        try {
            // MailSendUtil.sendTextMail(info);
            EmailSendUtil.sendHtmlMail(info);
        } catch (Exception e) {
            System.out.print("'" + title + "'的邮件发送失败！");
            e.printStackTrace();
        }
        return captcha;
    }

    /** 
     * Description：发送密码重置邮件
     * @param email
     * @param session
     * @return
     * @return String
     * @author name：yuxin <br>email: yuruixin_china@163.com
     **/
    public static String sendForResetPassword(String email,HttpSession session) {
        String captcha = CaptchaUtils.creatCaptcha();
        session.setAttribute("restPassword:"+email, captcha);
        String title = "密码重置";
        String content = EmailTemplet.getHtml(title, email, title, captcha);
        EmailMailInfo info = new EmailMailInfo();
        info.setToAddress(email);
        info.setSubject(title);
        info.setContent(content);
        try {
            // MailSendUtil.sendTextMail(info);
            EmailSendUtil.sendHtmlMail(info);
        } catch (Exception e) {
            System.out.print("'" + title + "'的邮件发送失败！");
            e.printStackTrace();
        }
        return captcha;
    }

    /** 
     * Description：校验验证码
     * @param email
     * @param captcha
     * @param session
     * @return
     * @return boolean
     * @author name：yuxin <br>email: yuruixin_china@163.com
     **/
    public static boolean checkCaptcha(String email,String type,String captcha,HttpSession session){
        String captchaCache = null;
        switch (type) {
            case "reg":
                captchaCache = (String) session.getAttribute("reg:"+email);
                break;
            case "restPassword":
                captchaCache = (String) session.getAttribute("restPassword:"+email);
                break;
            default:
                break;
        }
        if(captchaCache != null){
            return captchaCache.equals(captcha);
        }
        return false;
    }
}
