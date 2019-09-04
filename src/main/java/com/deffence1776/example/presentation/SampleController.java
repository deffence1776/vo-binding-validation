package com.deffence1776.example.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SampleController {

    @ModelAttribute
    public SampleForm getSampleForm(){
        return new SampleForm();
    }

    @GetMapping
    public String index(){

        return "index";
    }

    @PostMapping
    public String post(@Validated SampleForm sampleForm, BindingResult result, Model model){
        if (result.hasErrors()){
            return "index";
        }
        model.addAttribute("msg","成功です");
        return "index";
    }
}
