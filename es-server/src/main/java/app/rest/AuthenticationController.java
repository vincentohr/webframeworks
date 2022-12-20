package app.rest;

import app.APIConfig;
import app.Exceptions.NotAcceptableException;
import app.models.User;
import app.utility.JWToken;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    APIConfig apiConfig;

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody ObjectNode login) {
        String email = login.get("email").asText();
        String password = login.get("password").asText();

        int firstPartEmail = email.indexOf("@");
        String subString = "";
        if (firstPartEmail != -1) {
            subString = email.substring(0, firstPartEmail);
        }

        if (!password.equals(subString)) {
            throw new NotAcceptableException("Wrong password");
        }

        User user = new User(email);
        user.setName(email.substring(0, email.indexOf("@")));

        // Issue a token for the account, valid for some time
        JWToken jwToken = new JWToken(user.getName(), user.getId(), user.getRole());
        String tokenString = jwToken.encode(this.apiConfig.getIssuer(),
                this.apiConfig.getPassphrase(),
                this.apiConfig.getTokenDurationOfValidity());

        return ResponseEntity.accepted()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenString)
                .body(user);
    }
}
