/**
 * 
 */
package com.jvoliveira.geradorcracha.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jvoliveira.geradorcracha.domain.Pessoa;

/**
 * @author João Victor
 *
 */
public class ParseCSVToPessoa {

	public static List<Pessoa> parse(File file){
		 List<Pessoa> resultado = new ArrayList<Pessoa>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			StringBuffer fileContents = new StringBuffer();
		    String pessoaTexto = br.readLine();
		    while (pessoaTexto != null && !pessoaTexto.trim().equals("")) {
		        fileContents.append(pessoaTexto);
		        if(isPessoaTextoValida(pessoaTexto)){
					Pessoa pessoa = parseTextoToPessoa(pessoaTexto);
					resultado.add(pessoa);
				}
		        pessoaTexto = br.readLine();
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	private static Pessoa parseTextoToPessoa(String pessoaTexto) {
		String[] dadosPessoa = pessoaTexto.split(";");
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(dadosPessoa[0]);
		pessoa.setTitulo(dadosPessoa[1]);
		pessoa.setSubtitulo(dadosPessoa[2]);
		pessoa.setEquipista(Boolean.getBoolean(dadosPessoa[3]));
		return pessoa;
	}

	private static boolean isPessoaTextoValida(String pessoaTexto) {
		return true;
	}
	
	public static File convert(MultipartFile file)
	{    
	    File convFile = new File(file.getOriginalFilename());
	    try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile); 
		    fos.write(file.getBytes());
		    fos.close(); 
		    return convFile;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convFile; 
	    
	}
	
}
