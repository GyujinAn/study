package tacos.security;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * @author agj017@gmail.com
 * @since 2021/10/29
 */
public class TacoUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private boolean postOnly = true;
    private HashMap<String, String> jsonRequest;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(postOnly && !request.getMethod().equals("POST")){
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        if(request.getHeader("Content-Type").equals("application/json")){

            ObjectMapper mapper = new ObjectMapper();
            try {
                this.jsonRequest = mapper.readValue(request.getReader().lines().collect(Collectors.joining()),
                        new TypeReference<HashMap<String, String>>() {});
            } catch (IOException e) {
                e.printStackTrace();
                throw new AuthenticationServiceException("Request Content-Type(application/json) Parsing Error");
            }

        }

        String username = this.obtainUsername(request);
        username = username != null ? username : "";
        username = username.trim();
        String password = this.obtainPassword(request);
        password = password != null ? password : "";
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        this.setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        if(request.getHeader("Content-Type").equals("application/json")){
            return jsonRequest.get(super.getPasswordParameter());
        }
        return super.obtainPassword(request);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        if(request.getHeader("Content-Type").equals("application/json")){
            return jsonRequest.get(super.getUsernameParameter());
        }
        return super.obtainUsername(request);
    }

    @Override
    public void setPostOnly(boolean postOnly) {
        super.setPostOnly(postOnly);
    }
}

