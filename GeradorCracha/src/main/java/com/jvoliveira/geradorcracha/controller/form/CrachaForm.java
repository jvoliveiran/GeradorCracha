/**
 * 
 */
package com.jvoliveira.geradorcracha.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author João Victor
 *
 */
public class CrachaForm {

	private String cabecalho;
	
	private String subCabecalho;
	
	@NotNull
    @Size(min=3, max=30)
	private String nome;
	
	@NotNull
	private String titulo;
	
	private String subTitulo;
	
	public String getCabecalho() {
		return cabecalho;
	}
	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}
	public String getSubCabecalho() {
		return subCabecalho;
	}
	public void setSubCabecalho(String subCabecalho) {
		this.subCabecalho = subCabecalho;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubTitulo() {
		return subTitulo;
	}
	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}
	
}
