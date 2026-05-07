package com.example.firstProject.ecommerce.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response,
							 Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		boolean loggedIn = session != null && session.getAttribute("userEmail") != null;
		if (!loggedIn) {
			sendRedirect(request, response, "/login");
			return false;
		}

		String path = request.getRequestURI();
		if (path != null && path.startsWith("/dashboard")) {
			Object roleAttr = session.getAttribute("userRole");
			boolean isAdmin = roleAttr instanceof String roleName && "ADMIN".equalsIgnoreCase(roleName);
			if (!isAdmin) {
				sendRedirect(request, response, "/store");
				return false;
			}
		}

		return true;
	}

	private static void sendRedirect(HttpServletRequest request,
									 HttpServletResponse response,
									 String location) throws IOException {
		String ctx = request.getContextPath();
		if (ctx == null || ctx.isBlank() || "/".equals(ctx)) {
			response.sendRedirect(location);
			return;
		}
		if (location.startsWith("/")) {
			response.sendRedirect(ctx + location);
		} else {
			response.sendRedirect(ctx + "/" + location);
		}
	}
}
