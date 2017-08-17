/**
 * 
 */
package com.jvoliveira.geradorcracha.controller.form;

import javax.validation.constraints.NotNull;

/**
 * @author João Victor
 *
 */
public class CabecalhoCrachaForm {

	@NotNull
	private String cabecalho;
	
	@NotNull
	private String subcabecalho;

	public String getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

	public String getSubcabecalho() {
		return subcabecalho;
	}

	public void setSubcabecalho(String subcabecalho) {
		this.subcabecalho = subcabecalho;
	}
	
}
