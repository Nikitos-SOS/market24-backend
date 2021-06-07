package com.market.marketplace.controllers;

import com.market.marketplace.models.AuthenticationRequest;
import com.market.marketplace.models.AuthenticationResponse;
import com.market.marketplace.models.Seller;
import com.market.marketplace.models.SellerDetails;
import com.market.marketplace.services.SellerDetailsService;
import com.market.marketplace.services.SellerService;
import com.market.marketplace.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "htpp://localhost:4200")
public class SellerResource {
    @Autowired
    private SellerDetailsService sellerDetailsService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/seller_signup")
    public ResponseEntity<?> signup(@RequestBody Seller seller){
        if(sellerService.existByName(seller.getUsername())){
            return ResponseEntity.badRequest().body("Error: Username is already taken.");
        }
        seller.setPassword(encoder.encode(seller.getPassword()));
        sellerService.addSeller(seller);
        return new ResponseEntity<>("User successfully created!", HttpStatus.CREATED);
    }

    @PostMapping("/seller_login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest seller)throws Exception{

        System.out.println(seller.getUsername() + seller.getPassword());

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(seller.getUsername(),seller.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password!", e);
        }

        final SellerDetails sellerDetails = sellerDetailsService.loadUserByUsername(seller.getUsername());
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("contactInfo",sellerDetails.getContactInfo());
        claims.put("inn", sellerDetails.getINN());
        claims.put("role", sellerDetails.getAuthorities());
        final String jwtToken = jwtTokenUtil.generateToken(sellerDetails,claims);
        final AuthenticationResponse response = new AuthenticationResponse(jwtToken);
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }
}
