/**
 * 
 */
package com.jvoliveira.geradorcracha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jvoliveira.geradorcracha.controller.form.CrachaForm;
import com.jvoliveira.geradorcracha.domain.Pessoa;

/**
 * @author João Victor
 *
 */

@Controller("/pessoa")
public class PessoaController {

	@PostMapping(name="/cadastrar_pessoa")
	public ModelAndView cadastrarPessoa(Pessoa pessoa){
		ModelAndView view = new ModelAndView("/index");
		//TODO: Cadastrar pessoa
		view.addObject("pessoa", new Pessoa());
		view.addObject("crachaForm", new CrachaForm());
		return view;
	}
	
}
