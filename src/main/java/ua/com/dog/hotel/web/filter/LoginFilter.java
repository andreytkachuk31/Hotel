package ua.com.dog.hotel.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import ua.com.dog.hotel.model.user.User;
import ua.com.dog.hotel.model.user.UserRole;
import ua.com.dog.hotel.util.DomXMLParser;

public class LoginFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(LoginFilter.class);
	private List<String> excludeList = new ArrayList<>();
	private Map<UserRole, Pattern> roles;

	@Override
	public void init(FilterConfig config) throws ServletException {
		excludeList = Arrays.asList(config.getInitParameter("excludeList").split(";"));
		try {
			roles = DomXMLParser.parseXML(config.getServletContext().getRealPath("") + "/WEB-INF/roles.xml");
		} catch (SAXException | IOException | ParserConfigurationException e) {
			LOG.error("Parsing exception: " + e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		String page = getPage(request.getRequestURI());
		User user = (User) session.getAttribute("user");
		if (user == null) {
			if (excludeList.contains(page) || isStaticResource(page)) {
				chain.doFilter(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/login");
			}
		} else {
			UserRole userRole = UserRole.valueOf(user.getRoleId());
			String url = request.getRequestURL().toString();
			if (isAccessLimited(url).equals(userRole) || excludeList.contains(page) || isStaticResource(page)) {
				chain.doFilter(request, response);
			} else {
				request.getSession().setAttribute("error", "Access on this page for you denied");
				response.sendRedirect(request.getContextPath() + "/error");
			}
		}
	}

	@Override
	public void destroy() {

	}

	private String getPage(String requestURI) {
		return requestURI.substring(requestURI.lastIndexOf("/") + 1);
	}

	private boolean isStaticResource(String page) {
		return page.contains(".");
	}

	private UserRole isAccessLimited(String url) {
		for (Map.Entry<UserRole, Pattern> rolePattern : roles.entrySet()) {
			UserRole role = rolePattern.getKey();
			Pattern pattern = rolePattern.getValue();
			Matcher matcher = pattern.matcher(url);
			if (matcher.matches()) {
				return role;
			}
		}
		return UserRole.CLIENT;
	}
}
