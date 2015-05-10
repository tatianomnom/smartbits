package com.leveluptor.smartbits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @Autowired
    private MyService myService;

    @RequestMapping("/messages")
    String home(Model model) {
        model.addAttribute("msgs", myService.findMessages());
        return "messages";
    }

    @RequestMapping("/messages/{id}")
    String showOne(@PathVariable("id") long id, Model model) {
        model.addAttribute("msg", myService.findMessageById(id));
        return "one";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ModelAndView addOne(@RequestParam("summary") String summary, @RequestParam("notes") String notes) {
        myService.addMessage(new Message(summary, notes));
        return new ModelAndView("redirect:/messages");
    }
}
