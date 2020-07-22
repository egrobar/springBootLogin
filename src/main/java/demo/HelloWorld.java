package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import models.AuthenticationRequest;
import models.AuthenticationResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import demo.utils.JwtUtil;


@RestController
public class HelloWorld {
  
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private MyUserDetailsService userDetailsService; 
  
  @Autowired
  private JwtUtil jwtTokenUtil;

  @RequestMapping( "/hello" )
  public String helloThere () { return "This is your new home page"; }

  @RequestMapping( value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    try {
      authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
    );
    } catch (BadCredentialsException e) {

      throw new Exception("Incorrect Username or Passowrd", e);
    }

    final UserDetails userDetails = userDetailsService
      .loadUserByUsername(authenticationRequest.getUsername());
    
    final String jwt = jwtTokenUtil.generateToken(userDetails);
    System.out.println(jwt);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

}

