package com.ssh.shop.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.shop.model.FileImage;
import com.ssh.shop.service.AccountService;
import com.ssh.shop.service.CategoryService;
import com.ssh.shop.service.ForderService;
import com.ssh.shop.service.PayService;
import com.ssh.shop.service.ProductService;
import com.ssh.shop.service.SorderService;
import com.ssh.shop.service.UserService;
import com.ssh.shop.utils.EmailUtil;
import com.ssh.shop.utils.FileUpload;
import com.ssh.shop.utils.MessageUtil;
import com.sun.mail.handlers.message_rfc822;

@SuppressWarnings("serial")
@Controller("baseAction")
@Scope("prototype")
public class BaseAction<T> extends ActionSupport
		implements RequestAware, SessionAware, ApplicationAware, ModelDriven<T> {
	
	@Resource
	protected MessageUtil messageUtil;
	
	public void setMessageUtil(MessageUtil messageUtil) {
		this.messageUtil = messageUtil;
	}

	@Resource
	protected EmailUtil emailUtil;
	
	
	
	public void setEmailUtil(EmailUtil emailUtil) {
		this.emailUtil = emailUtil;
	}

	@Resource
	protected PayService payService;
	
	public void setPayService(PayService payService) {
		this.payService = payService;
	}

	@Resource
	protected UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Resource
	protected SorderService sorderService;
	@Resource
	protected ForderService forderService;

	public void setSorderService(SorderService sorderService) {
		this.sorderService = sorderService;
	}

	public void setForderService(ForderService forderService) {
		this.forderService = forderService;
	}

	protected FileImage fileImage;

	public FileImage getFileImage() {
		return fileImage;
	}

	public void setFileImage(FileImage fileImage) {
		this.fileImage = fileImage;
	}

	@Resource
	protected FileUpload fileUpload;

	// 用来装有将要被打包成json格式返回给前台的数据，下面要实现get方法
	protected List<T> jsonList = null;

	public List<T> getJsonList() {
		return jsonList;
	}

	// 获取要删除的ids，要有get和set方法
	// 流是用来想前台返回数据的，这个数据是让struts获取的，然后通过流的形式传到前台，所以实现get方法即可
	protected String ids;
	protected InputStream inputStream;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	// page和rows和分页有关，pageMap存放查询的数据，然后打包成json格式用的
	// page和rows实现get和set方法，pageMap只需要实现get方法即可，因为pageMap不是接收前台参数的，是让struts获取的
	protected Integer page;
	protected Integer rows;

	protected Map<String, Object> pageMap = null;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Map<String, Object> getPageMap() {
		return pageMap;
	}

	// service对象
	@Resource
	protected CategoryService categoryService;
	@Resource
	protected AccountService accountService;
	@Resource
	protected ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;
	protected T model;

	@Override
	public void setApplication(Map<String, Object> arg0) {
		this.application = arg0;

	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	@Override
	public T getModel() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class clazz = (Class) type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return model;
	}

}
