package com.Jungle.jungleboard.global.auth.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Slf4j
@Component
@Service
public class JwtProvider {
    private final String secretKey;
    private final long expirationHours;
    private final String issuer;
    private String salt;


    //    private static final String SECRET_KEY= "abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd";
    public final static long ACCESS_TOKEN_VALIDATION_SECOND = 1000L * 60 * 60 * 12; //12시간


//    private final UserDetailsService userDetailsService;

//    @PostConstruct
//    protected void init() {
//        secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
//    }

    public JwtProvider(@Value("${jwt.secret.key}") String secretKey,
                       @Value("${jwt.secret.expiration-hours}") long expirationHours,
                       @Value("${jwt.secret.issuer}") String issuer) {
        this.secretKey = secretKey;
        this.expirationHours = expirationHours;
        this.issuer = issuer;
    }

    //토큰 생성
    public String createToken(String identity) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + ACCESS_TOKEN_VALIDATION_SECOND);
        return Jwts.builder()
                .setSubject(identity)//jwt 토큰 제목
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                .compact();
    }

    //토큰에 담겨있는 유저 identity 추출
    public String getIdentity(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return e.getClaims().getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //권한 정보 획득
//    public Authentication getAuthentication(String token) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getIdentity(token));
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }

    //token 검증
    public boolean validateToken(String token) {
        try {
            // Bearer 검증 + equalsIgnoreCase()를 사용하여 대소문자 구분없이 비교
            if (!token.substring(0, "BEARER ".length()).equalsIgnoreCase("BEARER ")) {
                return false;
            } else {
                token = token.split(" ")[1].trim();
                System.out.println("bearer검증 후" + token);
            }
            Jws<Claims> claims = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public String validateTokenAndGetSubject(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
