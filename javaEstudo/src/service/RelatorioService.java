package service;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private String SEPARATOR = File.separator;
	private String caminhoArquivoRelatorio = null;
	private JRExporter exporter = null;
	private String caminhoSubReport_Dir = "";
	private File arquivoGerado = null;
	
	public String gerarRelatorio(List<?> listaDataBeanColletion, HashMap parametrosRelatorio, String nomeRelatorioJasper, 
			String nomeRelatorioSaida, ServletContext servletContext) throws Exception {
		
		//colection data source = fonte de dados de coleta
		//Cria a lista de collectionDataSource de beans que carregam os dados para o relatorio
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanColletion);
		
		//Fornece o caminho fisico até a pasta que contem os relatórios .jasper
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
		
		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");
		
		if (caminhoRelatorio == null
				|| (caminhoRelatorio != null && caminhoRelatorio.isEmpty())
				|| !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
		}
		
		//Caminho para imagens
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		//Caminho completo até o relatório compilado indicado
		String caminhoArquivosJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";
		
		//Faz o carregamento do relatório
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJasper);
		
		//Seta parâmetros SUBREPORT_DIR com o caminho fisico para subreport
		caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);
		
		//Carrega o arquivo
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper , parametrosRelatorio, jrbcds)   ;
		
		//Definindo o tipo de exportação
		exporter = new JRPdfExporter();
		
		//Caminho do relatório exportado
		caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + ".pdf" ;
		
		//Criar novo arquivo exportado
		arquivoGerado = new File (caminhoArquivoRelatorio);
		
		//prepara a impressão
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		//Executa a exportação
		exporter.exportReport();
		
		//Remove o arquivo do servidor após ser realizado o download
		arquivoGerado.deleteOnExit();
		
		return caminhoArquivoRelatorio;
	}
	
	
}
