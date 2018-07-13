package ar.edu.undav.colaboreitor;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ar.edu.undav.colaboreitor.domain.Cuenta;
import ar.edu.undav.colaboreitor.repository.CuentaRepo;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	public CuentaRepo cuentaRepo;
	
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
    	if (request.getRequestURI().equals("/cuenta") && request.getMethod().equals("POST")) {
            filterChain.doFilter(request, response);
            return;
    	}
    	
        String xAuth = request.getHeader("X-Authorization");
        
        if (xAuth == null) {
            //throw new SecurityException();
        	System.out.println("No X-Authorization header");
        	return;
        }

        int colon = xAuth.indexOf(':');
        String username = xAuth.substring(0, colon);
        String password = xAuth.substring(colon + 1);
        
        // validate the value in xAuth
        if(isValid(username, password) == false){
            //throw new SecurityException("Usuario o password invalidos");
        	System.out.println("Wrong user/pass");
        	return;
        }                            
        
        // Create our Authentication and let Spring know about it
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(auth);            

        filterChain.doFilter(request, response);
    }

	private boolean isValid(String username, String password) {
		Cuenta c = cuentaRepo.findByUsername(username);
		return c != null && c.getPassword().equals(password);
	}

}