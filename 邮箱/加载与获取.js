/** 
 * ClassName: EmailTemplet.java <br>
 * Description: <br>
 * Create by: name：yuxin <br>email: wsjkcxs@foxmail.com <br>
 * Create Time: 2020<br>
 */
public class EmailTemplet {

    public static String getHtml(String title,String userName,String type,String captcha) {
        String emailTemplet = System.getProperty("emailTemplet");
        emailTemplet = emailTemplet.replace("$(title)", title);
        emailTemplet = emailTemplet.replace("$(userName)", userName);
        emailTemplet = emailTemplet.replace("$(type)", type);
        emailTemplet = emailTemplet.replace("$(captcha)", captcha);
        return emailTemplet;
    }

    /** 
     * Description：容器初始化是加载
     * @return void
     * @author name：yuxin <br>email: yuruixin_china@163.com
     **/
    public static void initEmailTemplet() {
        String url = HospitalUtils.class.getResource("/emailTemplet.html")
                .getFile();
        url = url.substring(1, url.length());
        try {
            String encoding = "UTF-8";
            File file = new File(url);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                StringBuilder sb = new StringBuilder();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt);
                    sb.append(lineTxt);
                }
                System.setProperty("emailTemplet", sb.toString());
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
}
