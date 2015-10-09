/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.User;
import com.matrimony.model.SessionKey;
import com.matrimony.util.GeoIP;
import com.maxmind.geoip2.model.CountryResponse;

/**
 *
 * @author SON
 */
public class GlobalFilter implements Filter {

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
//		CountryResponse country = GeoIP.getCountry(req.getRemoteHost());
		User user = (User) request.getSession().getAttribute(SessionKey.USER);
//		System.out.println("Country info client: " + country);
		Cookie[] cookies = request.getCookies();
		Map<String, String> retriedCookies = new HashMap<String, String>();
		if (user == null && cookies!=null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equals("id")) {
					retriedCookies.put(c.getName(), c.getValue());
				} else if (c.getName().equals("login")) {
					retriedCookies.put(c.getName(), c.getValue());
				} else if (c.getName().equals("password")) {
					retriedCookies.put(c.getName(), c.getValue());
				} else if (c.getName().equals("keepLoggin")) {
					retriedCookies.put(c.getName(), c.getValue());
				}
			}
			if (retriedCookies.size() >= 4) {
				if (retriedCookies.get("keepLoggin").equals("true")) {
					User ssUser = UserDAO.findById(retriedCookies.get("id"));
					request.getSession().setAttribute(SessionKey.USER, ssUser);
				}
			}
		}
		try {
			chain.doFilter(request, response);
		} catch (ServletException e) {
			System.out.println("Global filter error-ServletException: ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("Global filter error-IOException: ");
			e.printStackTrace();
		}
	}
}