/**
 * 
 */
package com.jvoliveira.geradorcracha.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jvoliveira.geradorcracha.business.ParseCSVToPessoa;
import com.jvoliveira.geradorcracha.business.ParserPessoaToCrachaHelper;
import com.jvoliveira.geradorcracha.controller.form.CabecalhoCrachaForm;
import com.jvoliveira.geradorcracha.controller.form.CrachaForm;
import com.jvoliveira.geradorcracha.domain.Pessoa;
import com.jvoliveira.geradorcracha.repository.PessoaRepository;

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
	
	private PessoaRepository pessoaRepository;
	
	@Autowired
	public HomeController(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	@GetMapping(value={"/","/index","/home"})
	public ModelAndView getIndex(ModelAndView view){
		view.addObject("crachaForm", new CrachaForm());
		view.addObject("cabecalhoForm", new CabecalhoCrachaForm());
		view.addObject("pessoa", new Pessoa());
		view.addObject("pessoas", pessoaRepository.findAll());
		view.setViewName("/index");
		return view;
	}
	
	@PostMapping(value="/pessoa/cadastrar_pessoa")
	public String cadastrarPessoa(Pessoa pessoa){
		pessoaRepository.save(pessoa);
		
		return "redirect:/";
	}
	
	@PostMapping(value="/imprimir_lista_crachas")
	@ResponseBody
	public void imprimirListaCrachas(@Valid CabecalhoCrachaForm cabecalhoForm,  BindingResult result, HttpServletResponse response){
		if(result.hasErrors())
			return;
		
		try{
			Collection<Pessoa> pessoas =  (Collection<Pessoa>) pessoaRepository.findAll();
			List<CrachaForm> crachas = (List<CrachaForm>) ParserPessoaToCrachaHelper.parserCollectionPessoaToCrachaWithHeader(cabecalhoForm, pessoas);
			processarCracha(crachas,response);
		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(value={"/","/index","/home"})
	@ResponseBody
	public void downloadCracha(@Valid CrachaForm crachaForm, BindingResult result, HttpServletResponse response){
		if(result.hasErrors())
			return;
		
		try{
			processarCrachaSimples(crachaForm, response);
		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(value="/importar_pessoas")
	public String importarArquivoPessoas(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes){
		File fileCSV = ParseCSVToPessoa.convert(file);
		List<Pessoa> resultado = ParseCSVToPessoa.parse(fileCSV);
		pessoaRepository.save(resultado);
		return "redirect:/";
	}

	private void processarCrachaSimples(CrachaForm crachaForm, HttpServletResponse response) throws FileNotFoundException, JRException, IOException {
		List<CrachaForm> crachas = Arrays.asList(crachaForm);
		processarCracha(crachas,response);
		
	}
	
	private void processarCracha(List<CrachaForm> crachas, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		//InputStream inputStream = new FileInputStream("/Users/MacBook/JasperReports/report3.jrxml");
		InputStream inputStream = new FileInputStream("/Users/MacBook/JasperReports/crachas.jrxml");
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		HashMap<String,Object> parameters = new HashMap();
		
//		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(crachas);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(ParserPessoaToCrachaHelper.wrapCrachas(crachas));
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
		
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=cracha.pdf");
		
		OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
}
