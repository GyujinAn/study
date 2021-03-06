package kakaostyle.documentapproval.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("jwt.secret")
    private String secretKey;

    private Long tokenValidMillisecond = 60 * 60 * 1000L;

    private final KakaoUserDetailsService userDetailsService;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    public String createToken(String userId){
        Claims claims = Jwts.claims().setSubject(userId);
//        claims.put("roles", roles);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserId(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }


    public boolean validationToken(String token) {

        try{
            Jws<Claims> claimsJws = Jwts.parser().
                    setSigningKey(secretKey).
                    parseClaimsJws(token);

            return !claimsJws.getBody()
                    .getExpiration()
                    .before(new Date());
        }catch (Exception e){
            return false;
        }
    }


}
