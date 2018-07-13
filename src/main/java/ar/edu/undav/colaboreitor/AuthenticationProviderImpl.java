package ar.edu.undav.colaboreitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import ar.edu.undav.colaboreitor.domain.Cuenta;
import ar.edu.undav.colaboreitor.repository.CuentaRepo;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    // This would be a JPA repository to snag your user entities
    @Autowired private CuentaRepo cuentaRepo;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        UsernamePasswordAuthenticationToken userAuthentication = (UsernamePasswordAuthenticationToken) authentication;        
        Cuenta cuenta = cuentaRepo.findByUsername(userAuthentication.getName());
        
        if(cuenta == null){
            throw new UnknownUserException("No se pudo encontrar cuenta: " + authentication.getName());
        }
        
        return cuenta;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}