package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class Hello {
    @GetMapping("/helloServlet")
    public String hello(HttpServletRequest request) {
        request.setAttribute("name", "Servlet");
        return "hello";
    }

    @GetMapping("/helloModel")
    public String helloModel(Model model) {
        model.addAttribute("name", "Model");
        return "hello";
    }

    @GetMapping("/helloModelAndView")
    public ModelAndView helloModelAndView() {
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("name", "ModelAndView");
        return mv;
    }
}
