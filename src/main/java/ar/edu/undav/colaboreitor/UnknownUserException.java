package ar.edu.undav.colaboreitor;

import org.springframework.security.core.AuthenticationException;

public class UnknownUserException extends AuthenticationException {

	public UnknownUserException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
