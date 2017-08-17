/**
 * 
 */
package com.jvoliveira.geradorcracha.controller.form;

import java.io.File;

/**
 * Formato: NOME;TITULO;SUBTITULO;EQUIPISTA
 * @author João Victor
 *
 */
public class FileImportForm {

	private File csvFile;

	public File getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(File csvFile) {
		this.csvFile = csvFile;
	}
	
}
