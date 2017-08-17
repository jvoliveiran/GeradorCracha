/**
 * 
 */
package com.jvoliveira.geradorcracha.controller.form;

import java.util.Arrays;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author João Victor
 *
 */
public class CrachaFormWrap {

	private CrachaForm crachaForm;
	private JRBeanCollectionDataSource datasourceCracha;

	public CrachaForm getCrachaForm() {
		return crachaForm;		
	}

	public CrachaFormWrap(CrachaForm crachaForm){
		this.crachaForm = crachaForm;
		setDatasourceCracha(new JRBeanCollectionDataSource(Arrays.asList(this.crachaForm)));
	}
	
	public void setCrachaForm(CrachaForm crachaForm) {
		this.crachaForm = crachaForm;
	}

	public JRBeanCollectionDataSource getDatasourceCracha() {
		return datasourceCracha;
	}

	public void setDatasourceCracha(JRBeanCollectionDataSource datasourceCracha) {
		this.datasourceCracha = datasourceCracha;
	}
	
}
