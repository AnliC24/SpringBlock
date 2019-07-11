package tern.block.demo.Handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;

/**
 * 登录失败后调用的处理
 * */

@Component
public class AuthenticationFailurHandler extends SimpleUrlAuthenticationFailureHandler{
	
	private Logger 	Log = LogManager.getLogger(this.getClass());
	
	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
         
//		Log.info(exception.getLocalizedMessage());
//		response.sendRedirect("/error");
        super.onAuthenticationFailure(request, response, exception);
    }
}
