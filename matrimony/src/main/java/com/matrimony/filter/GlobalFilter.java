/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SessionKey;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.User;
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
		System.out.println("Filter: IP client: "+req.getRemoteAddr());
		CountryResponse country = GeoIP.getCountry(req.getRemoteHost());
		System.out.println("Filter: Locate: " + country);
		User user = (User) request.getSession().getAttribute(SessionKey.USER);
		System.out.println("Filter: Current user: " + user);
		Cookie[] allCookie = request.getCookies();
		if (user == null) {
			boolean keepLogin = false;
			if (allCookie != null) {
				for (Cookie c : allCookie) {
					if ("keepLoggin".equals(c.getName())) {
						if ("true".equals(c.getValue())) {
							keepLogin = true;
							break;
						}
					}
				}
				for (Cookie c : allCookie) {
					if (keepLogin && "loginName".equals(c.getName())) {
						user = UserDAO.findByEmailOrContactNumberOrUsername(c.getValue());
						request.getSession().setAttribute(SessionKey.USER, user);
						System.out.println("Filter: User keep login: " + user);
						break;
					}
				}
			}
		}
		try {
			chain.doFilter(request, response);
		} catch (IOException ex) {
			System.out.println("Filter: "+ex);
		} catch (ServletException ex) {
			System.out.println("Filter: "+ex);
		}
	}
}