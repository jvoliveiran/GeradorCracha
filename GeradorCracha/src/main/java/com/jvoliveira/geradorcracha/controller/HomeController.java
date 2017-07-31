/**
 * 
 */
package com.jvoliveira.geradorcracha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author João Victor
 *
 */
@Controller("/")
public class HomeController {

	@RequestMapping(value={"/","/index","/home"})
	public String getIndex(){
		return "/index";
	}
	
}
