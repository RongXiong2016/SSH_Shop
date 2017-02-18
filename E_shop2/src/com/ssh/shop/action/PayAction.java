/**
 * 
 */
package com.ssh.shop.action;

import java.util.Map;

import javax.enterprise.inject.New;

import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.shop.model.BackData;
import com.ssh.shop.model.Forder;
import com.ssh.shop.model.SendData;
import com.ssh.shop.model.User;

/**
 * @author Administrator
 *
 */
@Controller("payActtion")
@Scope("prototype")
public class PayAction extends BaseAction<Object> implements ParameterAware{
	
	private Map<String, String[]> parameters;
	
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		 this.parameters = parameters;

	}
 

	@Override
	public Object getModel() {
		if (parameters.get("pd_FrpId") != null) {
			model = new SendData();
		}else {
			model = new BackData();
		}
		return model;
	}


	public String goBank(){
		SendData sendData = (SendData)model;

		//1. 补全参数:P2 p3 pd Pa，需要从session中获取
		// pa_MP;//商户扩展信息 ,pd_FrpId;//支付通道编码，即银行 p2_Order;//商户订单号         p3_Amt;//支付金额
		Forder forder = (Forder) session.get("oldForder");
		User user = (User) session.get("user");
		sendData.setP2_Order(forder.getId().toString()); //商户订单号
		sendData.setP3_Amt(forder.getTotal()+""); //支付金额
		sendData.setPa_MP(user.getEmail() + "," + user.getPhone()); //商户扩展信息

		//2. 对参数进行追加		
		//3. 加密获取签名		
		//4. 存储到request域中
		payService.saveDataToRequest(request, sendData); //完成2,3,4
		//5. 跳转到支付页面

		return "pay";
	}

	 public void backBank(){
		 BackData backData = (BackData)model;
		 System.out.println(model);
		 boolean isOk = payService.checkBackData(backData);
		 if (isOk) {
			 //1. 更新订单状态,参数是自己根据数据库中的情况传进去的，用来测试
		        forderService.updateStatusById(Integer.valueOf(201605006), 2);
		        //2. 根据user邮箱地址，发送邮件
		        String emailAddress = backData.getR8_MP().split(",")[0];
		        emailUtil.sendEmail(emailAddress, backData.getR6_Order());
		        //3. 发送手机短信
		        String phoneNum = backData.getR8_MP().split(",")[1];
		        messageUtil.sendMessage(phoneNum, backData.getR6_Order());
		        System.out.println("----success!!----");
		}else {
			System.out.println("----error----");
		}
	 }
	 
}

