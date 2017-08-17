/**
 * 
 */
package com.jvoliveira.geradorcracha.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jvoliveira.geradorcracha.domain.Pessoa;

/**
 * @author João Victor
 *
 */
public class CrachaForm {

	private String cabecalho;
	
	private String subcabecalho;
	
	@NotNull
    @Size(min=3, max=30)
	private String nome;
	
	@NotNull
	private String titulo;
	
	private String subtitulo;
	
	private boolean equipista;
	
	public CrachaForm(){
		
	}
	
	public CrachaForm(Pessoa pessoa){
		this.nome = pessoa.getNome();
		this.titulo = pessoa.getTitulo();
		this.subtitulo = pessoa.getSubtitulo();
		this.equipista = pessoa.isEquipista();
	}
	
	public CrachaForm(CabecalhoCrachaForm cabecalho, Pessoa pessoa){
		this.cabecalho = cabecalho.getCabecalho();
		this.subcabecalho = cabecalho.getSubcabecalho(); 
		
		this.nome = pessoa.getNome();
		this.titulo = pessoa.getTitulo();
		this.subtitulo = pessoa.getSubtitulo();
		this.equipista = pessoa.isEquipista();
	}
	
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
	public String getSubtitulo() {
		return subtitulo;
	}
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	public boolean isEquipista() {
		return equipista;
	}
	public void setEquipista(boolean equipista) {
		this.equipista = equipista;
	}
	
}
