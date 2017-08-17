/**
 * 
 */
package com.jvoliveira.geradorcracha.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jvoliveira.geradorcracha.controller.form.CabecalhoCrachaForm;
import com.jvoliveira.geradorcracha.controller.form.CrachaForm;
import com.jvoliveira.geradorcracha.controller.form.CrachaFormWrap;
import com.jvoliveira.geradorcracha.domain.Pessoa;

/**
 * @author João Victor
 *
 */
public class ParserPessoaToCrachaHelper {

	public static Collection<CrachaForm> parserCollectionPessoaToCrachaWithHeader(CabecalhoCrachaForm headerForm, Collection<Pessoa> pessoas){
		Collection<CrachaForm> crachas = new ArrayList<>();
		
		for(Pessoa pessoa : pessoas)
			crachas.add(new CrachaForm(headerForm, pessoa));
		
		return crachas;
	}
	
	public static List<CrachaFormWrap> wrapCrachas(List<CrachaForm> crachas){
		List<CrachaFormWrap> crachasWrap = new ArrayList<>();
		for(CrachaForm cracha : crachas)
			crachasWrap.add(new CrachaFormWrap(cracha));
		return crachasWrap;
	}
	
}
