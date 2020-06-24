import java.util.Random;

public class CaptchaUtils {

    /** 

     * Description：生成验证码

     * @return

     * @return String

     * @author name：yuruixin <br>email: ruixin_yu@asdc.com.cn

     **/
    public static String creatCaptcha(){
        String captcha = captcha(6);
        return captcha;
    }

    /** 

     * Description：生成随机数

     * @param charCount

     * @return

     * @return String

     * @author name：yuruixin <br>email: ruixin_yu@asdc.com.cn

     **/
    public static String captcha(int charCount){
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }

    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
}
