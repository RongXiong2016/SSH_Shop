package com.ssh.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if (req.getSession().getAttribute("user")==null) {
			//保存用户想要去的url
			String goUrl = req.getServletPath();
			String param = req.getQueryString();
			
			if (param!=null) {
				goUrl = goUrl+"?"+param; 
			}
			//把当前客户想要访问的地址，存储到session中  
			req.getSession().setAttribute("goUrl", goUrl);
			//非法请求，跳转到登陆页面  
			req.getSession().setAttribute("error", "非法请求，请先登录");
			
			resp.sendRedirect(req.getContextPath()+"/ulogin.jsp");
		}else{
			//如果没有过滤器
			chain.doFilter(request, response);
		}


	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
