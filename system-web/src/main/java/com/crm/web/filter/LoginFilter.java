package com.crm.web.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.gacl.domain.CUser;
import me.gacl.utils.LoginManagerUtils;

public class LoginFilter implements Filter {

	private Pattern noFilterPattern = null;// 对一些配置的url不进行拦截

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String noFilterRegex = filterConfig.getInitParameter("noFilterRegex");
		if (null != noFilterRegex) {
			noFilterRegex = noFilterRegex.trim();
			noFilterPattern = Pattern.compile(noFilterRegex);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String servletPath = httpServletRequest.getServletPath();
		String url = httpServletRequest.getRequestURI();
		if (isNoFilter(url)) {
			chain.doFilter(httpServletRequest, httpServletResponse);
		}else {
			boolean isMust = false;
			isMust = LoginManagerUtils.isMustLogin(servletPath);
			if (isMust) {
				httpServletResponse.sendRedirect("http://localhost:8080/user/login.htm");
			}else {
				chain.doFilter(httpServletRequest, httpServletResponse);
			}
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	private boolean isNoFilter(String uri) {
		if (null != noFilterPattern) {
			return noFilterPattern.matcher(uri).matches();
		}
		return false;
	}

	private void setUserAttribute(ServletRequest request, CUser cUserVo) {
		request.setAttribute("userInfo", cUserVo);// 登录基本信息
	}
}
