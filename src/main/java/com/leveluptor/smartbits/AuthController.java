package com.leveluptor.smartbits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MyService myService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        String outcome = myService.addUser(username, password);
        if (outcome.isEmpty()) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            authenticationManager.authenticate(token);

            if (token.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(token);
                return new ModelAndView("redirect:/messages");
            }
        } else {
            return new ModelAndView("register", "error", outcome);
        }

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/register")
    //todo is it possible to remove this method and leave only mappings from config?
    public String register() {
        return "register";
    }


}
