package com.leveluptor.smartbits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    private MyService myService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, MyService myService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.myService = myService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute("form") @Valid RegistrationForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("register", "form", form);
//            modelAndView.addObject("form", form);
            return modelAndView;
        }
        String outcome = myService.addUser(form.getUsername(), form.getPassword());
        if (outcome.isEmpty()) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(form.getUsername());
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userDetails, form.getPassword(), userDetails.getAuthorities());
            authenticationManager.authenticate(token);

            if (token.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(token);
                return new ModelAndView("redirect:/messages");
            }
        } else {
//            bindingResult.rejectValue("username", "error.form", outcome);
            return new ModelAndView("register", "error", outcome);
        }

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    //todo is it possible to remove this method and leave only mappings from config?
    public String register(@ModelAttribute("form") @Valid RegistrationForm form) {
        return "register";
    }


}
