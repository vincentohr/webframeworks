package app.utility;


import app.APIConfig;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JWToken {
    private static final String JWT_CALLNAME_CLAIM = "sub";
    private static final String JWT_USERID_CLAIM = "id";
    private static final String JWT_ROLE_CLAIM = "role";
    private static final String JWT_IPADDRESS_CLAIM = "ipa";
    public static final String JWT_ATTRIBUTE_NAME = "JWTokenInfo";

    private String callName;
    private Long userId;
    private String role;
    private String ipAddress;

    public JWToken(String callName, Long userId, String role) {
        this.callName = callName;
        this.userId = userId;
        this.role = role;
    }

    public String getIpAddress(HttpServletRequest request) {
        // obtain the source IP-address of the current request
        String ipAddress = null;
        if (APIConfig.IP_FORWARDED_FOR != null) {
            ipAddress = request.getHeader(APIConfig.IP_FORWARDED_FOR);
        }
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String encode(String issuer, String passphrase, int expiration){
        Key key = getKey(passphrase);

        return Jwts.builder()
                .claim(JWT_CALLNAME_CLAIM, this.callName)
                .claim(JWT_USERID_CLAIM, this.userId)
                .claim(JWT_ROLE_CLAIM, this.role)
                .claim(JWT_IPADDRESS_CLAIM, this.ipAddress != null ? this.ipAddress : "1.1.1.1")
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration + 1000L))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    private static Key getKey(String passphrase){
        byte[] hmacKey = passphrase.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(hmacKey, SignatureAlgorithm.HS512.getJcaName());
    }

    public static JWToken decode(String token, String passphrase) throws ExpiredJwtException, MalformedJwtException{
        // validate the token
        Key key = getKey(passphrase);
        Jws<Claims> jws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        Claims claims = jws.getBody();

        JWToken jwToken = new JWToken(claims.get(JWT_CALLNAME_CLAIM).toString(),
                Long.valueOf(claims.get(JWT_USERID_CLAIM).toString()),
                claims.get(JWT_ROLE_CLAIM).toString());

        jwToken.setIpAddress((String) claims.get(JWT_IPADDRESS_CLAIM));
        return jwToken;
    }

}
