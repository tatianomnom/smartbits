package com.leveluptor.smartbits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
public class MyController {

    private MyService myService;

    @Autowired
    public MyController(MyService myService) {
        this.myService = myService;
    }

    @RequestMapping("/messages")
    String home(Model model, Principal principal) {
        Optional<User> user = myService.findByUsername(principal.getName());
        model.addAttribute("msgs", myService.findMessages(user.get()));
        return "messages";
    }

    @RequestMapping("/messages/{id}")
    String showOne(@PathVariable("id") long id, Model model) {
        model.addAttribute("msg", myService.findMessageById(id));
        return "one";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public String addOne(@RequestParam("summary") String summary, @RequestParam("notes") String notes, Principal principal) {
        Optional<User> user = myService.findByUsername(principal.getName());
        myService.addMessage(new Message(summary, notes, user.get()));
        return "redirect:/messages";
    }

}
