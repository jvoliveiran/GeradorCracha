/**
 * 
 */
package com.jvoliveira.geradorcracha.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jvoliveira.geradorcracha.controller.form.CrachaForm;

/**
 * @author João Victor
 *
 */
@Controller("/")
public class HomeController {

	@GetMapping(value={"/","/index","/home"})
	public String getIndex(CrachaForm crachaForm){
		return "/index";
	}

	@PostMapping(value={"/","/index","/home"})
	public String criarCracha(@Valid CrachaForm crachaForm, BindingResult result){
		if (result.hasErrors()) 
            return "index";
		
		processarCracha();
		
        return "redirect:/";
	}

	private void processarCracha() {
		// TODO Auto-generated method stub
		
	}
}
