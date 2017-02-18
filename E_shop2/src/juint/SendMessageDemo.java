/**
 * 
 */
package juint;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @author Administrator
 *
 */
public class SendMessageDemo {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws HttpException
	 */
	public static void main(String[] args) throws HttpException, IOException {
	 
		 //1. 打开浏览器
        HttpClient client = new HttpClient();
        //2. 创建请求的方式：get/post
        PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn/");//刚刚我们申请的那个服务器地址
        //3. 设置请求的参数信息
        post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");//设置转码
        post.setParameter("Uid", "一年不易");//参数都和上面的一样
        post.setParameter("Key", "247d671ecfa4734f73a2");
        post.setParameter("smsMob", "13221053569");
        post.setParameter("smsText", "测试一下！");
        //4. 提交请求并返回状态码
        int code = client.executeMethod(post);
        System.out.println("http返回的状态码：" + code);//如果打印出200说明正常
        //5. 获取服务器端返回的数据信息
        String result = post.getResponseBodyAsString();
        System.out.println("短信发送结果为：" + result);//如果返回1说明发送正常   
	}
}
