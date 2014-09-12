package com.buzz.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.configuration.Configuration;
import org.bouncycastle.util.encoders.Base64;
import org.json.JSONObject;


public class ReportManager extends Thread
{
  private InputStream reportData;
  private ByteArrayOutputStream output;
  private String type;
  private Map<String, Object> parameterValues;
  private final Map<String, Object> sessionParameterValues = new HashMap();
  private ConcurrentHashMap<String, Object> parameters;
  private String reportName;
  private Configuration properties;


  public ReportManager(String reportName, ConcurrentHashMap<String, Object> parameters, String type)throws Exception{
	  this.properties = PropertiesHandler.getInstance("buzzsri");
	  this.reportName = reportName;
	  this.parameters = parameters;
	  this.type = type;
	  parameters.put("SUBREPORT_DIR", properties.getString("report.dir"));
  }
  
  public JSONObject executeReport() throws Exception {
      ByteArrayOutputStream reportFile = new ByteArrayOutputStream();
      
      //ConcurrentHashMap<String, Object> parameters = getReportParameters();
      try {
          //reportFile = this.getFile(vReadDatabase, reportName);
    	  //Configuration configFitReports = PropertiesHandler.getConfig("fitreports");
          //String path = configFitReports.getString("report.receipt.path") + reportName + ".jasper";
          File file = new File(properties.getString("report.dir") + reportName + ".jasper");
          //FileInputStream in = new FileInputStream(file);
          this.reportData = new FileInputStream(file);
          /*byte b[] = new byte[9999];
          int car;
          do {
              car = in.read(b);
              if (car > 0) {
                  reportFile.write(b);
              }
          } while (car > 0);*/
      } catch (Exception e) {
          //throw new FitbankException("REP017", "REPORTE {0} NO ENCONTRADO", e, reportName);
      }
      //InputStream bin = new ByteArrayInputStream(reportFile.toByteArray());
      
    	    //this.reportData = bin;
    	    this.output = reportFile;
    	    //this.parameterValues = pParameters;
    	    evalReport();
    	    JSONObject json = new JSONObject();
    	    FileOutputStream out = new FileOutputStream(new File(properties.getString("report.dir.out") + reportName + System.currentTimeMillis() +".pdf"));
    	    out.write(output.toByteArray());
    	    out.close();
    	    json.append("generatedReport", Base64.encode(output.toByteArray()).toString());
    	    json.append("content-type", getContentType());
	      
    	    output.close();
    	    
    	    return json;

      //bin.close();
  }
  
  private void evalReport() throws Exception {
	    JRExporter exporter = getExporter();
	    JasperReport report = null;
	    try {
	      report = (JasperReport)JRLoader.loadObject(this.reportData);
	    } catch (JRException e) {
	      //FitbankLogger.getLogger().error(e.getMessage(), e);
	      throw e;
	    }

	    //validateReportParameters(report);
	    setparameters(report);
	    /*
	    String xPath = det.findFieldByNameCreate("XPATH").getStringValue();
	    String xReport = det.findFieldByNameCreate("XREPORT").getStringValue();
	    if ("true".equals(xReport)) {
	      readXmlDataSource(xPath, report);
	    }*/
	    //this.parameterValues.put("REPORT_LOCALE", new Locale(det.getLanguage().toLowerCase()));
	    try
	    {
	      JasperPrint impresor = null;
	      //Connection connection = (det.findFieldByNameCreate("AUX_CONNECTION").getStringValue() != null) && (det.findFieldByNameCreate("AUX_CONNECTION").getStringValue().equals("1")) ? Helper.getConnection(HbSessionAuxiliar.getInstance().openSession()) : Helper.getConnection();

	      //if ((report != null) && (connection != null)) {
	        impresor = JasperFillManager.fillReport(report, this.parameters);
	      //}
	      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, this.output);
	      exporter.setParameter(JRExporterParameter.JASPER_PRINT, impresor);

	      /*if (this.type.compareTo("TEXT1") == 0) {
	        exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, Float.valueOf(CONFIG.getString("report.character.width")));

	        exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, Float.valueOf(CONFIG.getString("report.character.height")));
	      }*/

	      exporter.exportReport();
	    }
	    catch (Exception ex) {
	      //FitbankLogger.getLogger().error(ex.getMessage(), ex);
	      throw ex;
	    }
	  }

  /*
  public Blob compile(String pSource) throws Exception {
    ByteArrayInputStream bin = new ByteArrayInputStream(pSource.getBytes());
    JasperDesign design = JRXmlLoader.load(bin);
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    JasperCompileManager.compileReportToStream(design, bout);
    bout.close();
    bin.close();
    return (Blob)BeanManager.convertObject(bout.toByteArray(), Blob.class);
  }*/

  private JRExporter getExporter() throws Exception {
    int cont = 1;
    String originType = this.type;
    JRExporter exporter = null;
    exporter = ReportTypes.valueOf(this.type).getExporter();
    return exporter;
  }

  
/*
  public void readXmlDataSource(String xPath, JasperReport pReport) throws Exception {
    Document doc = loadDocumentFromFile(new File(xPath));
    setReportParameter("XML_DATA_DOCUMENT", doc);
  }

  public Document loadDocumentFromFile(File file) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    return builder.parse(file);
  }
  */
  
  public String getContentType() throws Exception {
    return ReportTypes.valueOf(this.type).getContentType();
  }

  public OutputStream getOutput() {
    return this.output;
  }

  private void manageParameter(JRParameter parameter) throws Exception
  {
    if ((!parameter.isSystemDefined()) && (parameter.isForPrompting())) {
      String name = parameter.getName();
      if (!this.parameterValues.containsKey(name)) {
        if (this.sessionParameterValues.containsKey(name))
          setReportParameter(name, this.sessionParameterValues.get(name));
        else {
          //throw new FitbankException("REP004", "EL PARAMETRO ({0}){1} NO TIENE VALOR", new Object[] { name, StringUtils.defaultIfEmpty(parameter.getDescription(), "") });
        }

      }

      Object value = this.parameterValues.get(name);
      //FitbankLogger.getLogger().info("PARAMETRO PARA EL REPORTE " + name + ">>" + value);
      if (value.getClass().getSimpleName().compareTo(parameter.getValueClass().getSimpleName()) != 0) {
        //value = BeanManager.convertObject(value, parameter.getValueClass());
        this.parameterValues.put(name, value);
      }
    } else {
      //FitbankLogger.getLogger().debug("PARAMETRO DEL SISTEMA O NO DE INGRESO " + parameter.getName());
    }
  }

  private void setReportParameter(String parameter, Object value) {
    if (!this.parameters.containsKey(parameter))
    	this.parameterValues.put(parameter, value);
  }

  private void validateReportParameters(JasperReport pReport) throws Exception
  {
    addDefaultParameters();
    JRParameter[] parameters = pReport.getParameters();
    for (JRParameter parameter : parameters)
      manageParameter(parameter);
  }

  private void addDefaultParameters()
    throws Exception
  {
    /*setReportParameter("FITBANK_HEADER_LOGO", CONFIG.getString("report.header.logo"));
    setReportParameter("FITBANK_IMAGES", CONFIG.getString("report.images"));
    setReportParameter("FHASTA", FormatDates.getDefaultExpiryTimestamp());
    setReportParameter("detail", this.detail);
    setReportParameter("IDIOMA", this.detail.getLanguage());
    setReportParameter("IDM", this.detail.getLanguage());
    setReportParameter("CIA", this.detail.getCompany().toString());

    String reportName = "REPORTE SIN NOMBRE";
    Field fReportName = this.detail.findFieldByName("NAME");
    if ((fReportName != null) && (fReportName.getValue() != null) && (StringUtils.isNotBlank(fReportName.getStringValue())))
    {
      reportName = fReportName.getStringValue();
    }

    setReportParameter("_TITLE", reportName);

    String companyName = "COMPANIA SIN NOMBRE";
    Tperson tp = (Tperson)Helper.getBean(Tperson.class, new TpersonKey(this.detail.getCompany(), ApplicationDates.DEFAULT_EXPIRY_TIMESTAMP));
    if ((tp != null) && (StringUtils.isNotBlank(tp.getNombrelegal()))) {
      companyName = tp.getNombrelegal();
    }

    setReportParameter("_COMPANY_NAME", companyName);
    setReportParameter("_ACCOUNTING_DATE", this.detail.getAccountingDate());
    setReportParameter("_REAL_DATE", ApplicationDates.getDBDate());
    setReportParameter("_USER", this.detail.getUser());

    Date fechaDesde = ApplicationDates.getDBDate();
    Field fFechaDesde = this.detail.findFieldByName("R_FMIN");
    if ((fFechaDesde != null) && (fFechaDesde.getValue() != null) && (StringUtils.isNotBlank(fFechaDesde.getStringValue())))
    {
      fechaDesde = fFechaDesde.getRealDateValue();
    }

    putParameter("FMIN", fechaDesde);

    Date fechaHasta = ApplicationDates.DEFAULT_EXPIRY_DATE;
    Field fFechaHasta = this.detail.findFieldByName("R_FMAX");
    if ((fFechaHasta != null) && (fFechaHasta.getValue() != null) && (StringUtils.isNotBlank(fFechaHasta.getStringValue())))
    {
      fechaHasta = fFechaHasta.getRealDateValue();
    }

    putParameter("FMAX", fechaHasta);*/
  }

  private void setparameters(JasperReport report) throws Exception
  {/*
    if (CONFIG.getBoolean("report.nopaging")) {
      this.parameterValues.put("IS_IGNORE_PAGINATION", Boolean.TRUE);
    }
    if ((CONFIG.getBoolean("report.nopaging.txt")) && (this.type.compareTo(ReportTypes.TEXT.name()) == 0)) {
      this.parameterValues.put("IS_IGNORE_PAGINATION", Boolean.TRUE);
    }
    if ((CONFIG.getBoolean("report.nopaging.xls")) && (this.type.compareTo(ReportTypes.XLS.name()) == 0)) {
      this.parameterValues.put("IS_IGNORE_PAGINATION", Boolean.TRUE);
    }
    if (this.type.compareTo(ReportTypes.XLS.name()) == 0)
      report.getPropertiesMap().setProperty("net.sf.jasperreports.export.xls.white.page.background", "false");
      */
  }
}