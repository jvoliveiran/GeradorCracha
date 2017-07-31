/**
 * 
 */
package com.jvoliveira.geradorcracha.domain;

/**
 * @author João Victor
 *
 */
public class Cracha {

	private String cabecalho;
	private String subCabecalho;
	
	private String nome;
	private String titulo;
	private String subTitulo;
	
	//TODO: Atributo para cor da borda
	//TODO: Atributo para imagem de fundo como marca dagua
	//TODO: Atributo para imagem lateral
	
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
