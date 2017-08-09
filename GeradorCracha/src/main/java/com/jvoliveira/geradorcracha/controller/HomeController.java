/**
 * 
 */
package com.jvoliveira.geradorcracha.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jvoliveira.geradorcracha.controller.form.CrachaForm;
import com.jvoliveira.geradorcracha.domain.Pessoa;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * @author João Victor
 *
 */
@Controller("/")
public class HomeController {

	@GetMapping(value={"/","/index","/home"})
	public ModelAndView getIndex(ModelAndView view){
		view.addObject("crachaForm", new CrachaForm());
		view.addObject("pessoa", new Pessoa());
		view.setViewName("/index");
		return view;
	}
	
	@PostMapping(value={"/","/index","/home"})
	@ResponseBody
	public void downloadCracha(@Valid CrachaForm crachaForm, BindingResult result, HttpServletResponse response){
		if(result.hasErrors())
			return;
		
		try{
			processarCracha(crachaForm, response);
		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processarCracha(CrachaForm crachaForm, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		InputStream inputStream = new FileInputStream("/Users/MacBook/JasperReports/report3.jrxml");
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

		HashMap<String,Object> parameters = new HashMap();
		List<CrachaForm> crachas = Arrays.asList(crachaForm);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(crachas);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
		
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=cracha.pdf");
		
		OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
}
