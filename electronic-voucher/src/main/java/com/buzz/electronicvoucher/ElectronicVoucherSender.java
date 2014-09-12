package com.buzz.electronicvoucher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.print.attribute.HashAttributeSet;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.buzz.electronicvoucher.data.DataFiller;
import com.buzz.electronicvoucher.data.VoucherDataFiller;
import com.buzz.electronicvoucher.schema.v1_0_0.ComprobanteRetencion;
import com.buzz.electronicvoucher.schema.v1_1_0.Factura;
import com.buzz.electronicvoucher.schema.v1_1_0.InfoTributaria;
import com.buzz.electronicvoucher.util.Modulo11;
import com.buzz.electronicvoucher.util.SOAPClient;
import com.buzz.electronicvoucher.util.SignatureUtil;
import com.buzz.persistence.util.JPASession;
import com.buzz.persistence.voucher.Tusuario;
import com.buzz.persistence.voucher.Tusuarioid;
import com.buzz.tools.ReportManager;
import com.buzz.tools.TokenCreator;

public class ElectronicVoucherSender {
	
	private ElectronicVoucherTypes voucherType;
	private DataFiller dataFiller;
	private String sessionToken = TokenCreator.createToken();
	private String signedVoucher;
	private Object objeto;
	private String nombre;
	private Logger log = Logger.getLogger(ElectronicVoucherSender.class);
	//public static final Logger LOGGER = FitbankLogger.getLogger();
	
	/**
     * Proceso principal para el manejo de Comprobantes
     * @param pObject
     * @return
     */
    public JSONObject processVoucher(Object pObject)throws Exception {
    	String username = "ADMIN";
    	/*Map<String, Object> params = new HashMap<String, Object>();
    	params.put("user", username);
    	Tusuarioid tusuarioid = (Tusuarioid)JPASession.getQueryBean("from Tusuarioid a where cusuario =:user", params);
    	*/
    	Integer ccontribuyente = 1;
    	
    	System.setProperty("javax.net.ssl.trustStore",
				"/Users/santteegt/Desktop/sri/jks/keystore.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "Musho2014");
		System.setProperty("javax.net.ssl.trustStoreType", "JKS");
		/*String tipo = (String) pDetail.findFieldByNameCreate("MOSTRARDOC")
				.getValue();*/
		//Integer company = pDetail.getCompany();
		String pathFileJks = "/Users/santteegt/Desktop/sri/jks/ivan_mauricio_amay_aviles.p12";
		String jksPasswordStore = "Musho2014";
		String jksPassPrivate = "Musho2014";
		/*String jksPassAlias = PropertiesHandler.getConfig("security")
				.getString("jks.passAlias");*/
		
    	JSONObject jsonResponse = new JSONObject();
    	//StringWriter voucherWriter = new StringWriter();
    	//try{
	    	
	    	DocumentBuilderFactory factory =
	    		      DocumentBuilderFactory.newInstance();
	    	DocumentBuilder builder =
	    	        factory.newDocumentBuilder();
	    	Document voucherDocument = builder.newDocument();
	    	JAXBContext context = JAXBContext.newInstance((pObject.getClass()));
	    	Marshaller marshaller = context.createMarshaller();
	    	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    	SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	    	
	    	InfoTributaria infoTributaria = (InfoTributaria)pObject.getClass()
	    			.getDeclaredMethod("getInfoTributaria").invoke(pObject);
	    	Modulo11 modulo11 = new Modulo11();
	    	String accessKey = modulo11.obtainEncoding(infoTributaria.getClaveAcceso());
	    	infoTributaria.setClaveAcceso(accessKey);
	    	log.info("PROCESSING ACCESSKEY: "+ accessKey);
	    	
	    	if(pObject instanceof Factura) {
	    		this.voucherType = ElectronicVoucherTypes.BILL;
	    		Schema schema = schemaFactory.newSchema(
	    				Thread.currentThread().getContextClassLoader()
	    				.getResource("factura.xsd"));
	    		marshaller.setSchema(schema);
	    		
				
				//FileOutputStream fos = new FileOutputStream("/Users/santteegt/Desktop/test.xml");
		        //guardamos el objeto serializado en un documento XML
		        //marshaller.marshal((Factura)pObject, fos);
		        //fos.close();
		        //marshaller.marshal(pObject, voucherWriter);
		        //marshaller.marshal(pObject, voucherDocument);
	    	}
	    	if (pObject instanceof ComprobanteRetencion){
	    		this.voucherType = ElectronicVoucherTypes.RETENTION;
	    		Schema schema = schemaFactory.newSchema(
	    				Thread.currentThread().getContextClassLoader()
	    				.getResource("comprobanteRetencion1.xsd"));
                marshaller.setSchema(schema);
	    		
	    	}
	    	marshaller.marshal(pObject, voucherDocument);
	    	
	    	this.dataFiller = (DataFiller)Class.forName(this.voucherType.getdataFillerClass()).newInstance();
	    	
	    	SignatureUtil sgp = new SignatureUtil("PKCS12", pathFileJks,
					jksPasswordStore, jksPassPrivate);
	    	String signedDoc = sgp.processXMLString(voucherDocument);
	    	
			String endpoint = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes";
			String reqEnv = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body><ns1:validarComprobante xmlns:ns1=\"http://ec.gob.sri.ws.recepcion\"><xml xsi:type=\"xsd:base64Binary\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">{0}</xml></ns1:validarComprobante></soapenv:Body></soapenv:Envelope>";
			reqEnv = reqEnv.replaceAll("\\{0\\}",
					org.apache.axis.encoding.Base64.encode(signedDoc.getBytes()));
			log.info("SOAP MESSAGE: " + reqEnv);
			String soapResponse = SOAPClient.getInstance()
					.soapSendReal(
					endpoint, reqEnv);
			
			this.manageServiceResponse(this.readXMLDocumentEnveloped(soapResponse), jsonResponse);
    		jsonResponse.append("fullMessage", soapResponse);
			
    		
    		String endpointR = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes";
			String sopRes = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body><autorizacionComprobante xmlns=\"http://ec.gob.sri.ws.autorizacion\"><claveAccesoComprobante xmlns=\"\">{0}</claveAccesoComprobante></autorizacionComprobante></soapenv:Body></soapenv:Envelope>";
			sopRes = sopRes.replaceAll("\\{0\\}", accessKey);
			String printedSoapResponse = SOAPClient.getInstance().soapSendReal(
					endpointR, sopRes);
			
			Boolean isAuthorized = this.readDocumentReturnSri(printedSoapResponse, voucherDocument, jsonResponse);
			jsonResponse.append("fullMessage2", printedSoapResponse);
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("status", ElectronicVoucherStatusTypes.AUTHORIZED.getStatus());
			parameters.put("jsonResponse", jsonResponse);
			parameters.put("sessionToken", this.sessionToken);
			parameters.put("signedVoucher", this.signedVoucher);
			
			VoucherDataFiller processedVoucher = new VoucherDataFiller();
			processedVoucher.setParameters(parameters,
					processedVoucher.getReportName());
			processedVoucher.fillData(ccontribuyente, pObject);
			
			if(isAuthorized) {
				//this.dataFiller.fillData(pk, voucher);		
			}
    	return jsonResponse;
    }
    
    private void manageServiceResponse(Document doc, JSONObject json)throws Exception{
    	String status = doc.getElementsByTagName("estado").item(0).getChildNodes().item(0).getNodeValue();
    	json.append("estado", status);
    	NodeList voucherList = doc.getElementsByTagName("comprobantes").item(0).getChildNodes();
    	JSONArray voucherArray = new JSONArray();
    	for(int i=0; i< voucherList.getLength();i++) {
    		JSONObject voucher = new JSONObject();
    		Element voucherNode = (Element)voucherList.item(i);
    		String accessKey = voucherNode.getElementsByTagName("claveAcceso").item(0).getChildNodes().item(0).getNodeValue();
    		voucher.append("claveAcceso", accessKey);
    		NodeList errorMessages = voucherNode.getElementsByTagName("mensajes").item(0).getChildNodes();

    		JSONArray errorArray = this.getResponseErrorMessages(errorMessages);

    		if(errorArray.length() > 0)
				voucher.append("mensajes", errorArray);
    		voucherArray.put(voucher);
    	}
    	if(voucherArray.length() > 0)
    		json.append("comprobantes", voucherArray);
    }
    
    private String nodeToString(Node pNode) throws Exception {
    	String xmlstring = StringUtils.EMPTY;
        DOMSource domSource = new DOMSource(pNode);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            xmlstring = writer.toString();
        } finally {
            writer.close();
        }
        return xmlstring;
    }
	
	private Document readXMLDocumentEnveloped(String xml)throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource archivo = new InputSource();
		archivo.setCharacterStream(new StringReader(xml));
		Document documento = db.parse(archivo);
		documento.getDocumentElement().normalize();
		return documento;
	}

	public Boolean readDocumentReturnSri(String xml, Document voucherDocument, JSONObject jsonResponse)
			throws Exception {
		Boolean result = false;
		Document documento = this.readXMLDocumentEnveloped(xml);
		
		NodeList nodeList = documento.getElementsByTagName("autorizacion");
		for (int s = 0; s < nodeList.getLength(); s++) {
			Element authorization = (Element)nodeList.item(s);
			String estado;
			String numeroAutorizacion;
			String fechaAutorizacion;
			String ambiente;
			String authorizationStatus = authorization
					.getElementsByTagName("estado").item(0)
					.getChildNodes().item(0).getNodeValue();
			
			JSONObject headResponse = new JSONObject();
			headResponse.append("estado", authorizationStatus);
			String authorizationDate = authorization
					.getElementsByTagName("fechaAutorizacion").item(0)
					.getChildNodes().item(0).getNodeValue();
			String authorizacionProcessEnviroment = authorization
					.getElementsByTagName("ambiente").item(0)
					.getChildNodes().item(0).getNodeValue();
			
			headResponse.append("fechaAutorizacion", authorizationDate);
			headResponse.append("ambiente", authorizacionProcessEnviroment);
			
			if (ElectronicVoucherStatusTypes.AUTHORIZED
					.getStatus().equals(authorizationStatus)) {
				result = true;
				String authorizationNumber = authorization
						.getElementsByTagName("numeroAutorizacion").item(0)
						.getChildNodes().item(0).getNodeValue();
				
				this.signedVoucher =  authorization
						.getElementsByTagName("comprobante").item(0)
						.getChildNodes().item(0).getNodeValue();
				
				headResponse.append("numeroAutorizacion", authorizationNumber);
				
				this.readDocumentXML(voucherDocument, authorizationNumber, authorizationDate);
				jsonResponse.append("voucherReport", "");
			}
			if(ElectronicVoucherStatusTypes.NONAUTHORIZED
					.getStatus().equals(authorizationStatus)) {
				
				NodeList errorMessages = authorization.getElementsByTagName("mensajes").item(0).getChildNodes();
				JSONArray errorArray = this.getResponseErrorMessages(errorMessages);
				if(errorArray.length() > 0)
					headResponse.append("mensajes", errorArray);

			}
			jsonResponse.append("SRIresponse", headResponse);
		}
		return result;
	}
	
	private JSONArray getResponseErrorMessages(NodeList errorMessages)throws Exception {
		JSONArray errorArray = new JSONArray();
		for(int j=0;j< errorMessages.getLength() ; j++) {
			JSONObject error = new JSONObject();
			Element errorNode = (Element)errorMessages.item(j);
			error.append("identificador",
					errorNode.getElementsByTagName("identificador").item(0).getChildNodes().item(0).getNodeValue());
			error.append("tipo",
					errorNode.getElementsByTagName("tipo").item(0).getChildNodes().item(0).getNodeValue());
			error.append("mensaje",
					errorNode.getElementsByTagName("mensaje").item(0).getChildNodes().item(0).getNodeValue());
			if(errorNode.getElementsByTagName("informacionAdicional").getLength() > 0) {
				error.append("informacionAdicional",
						errorNode.getElementsByTagName("informacionAdicional").item(0).getChildNodes().item(0).getNodeValue());
			}
			errorArray.put(error);
		}
		return errorArray;
	}

	public JSONObject readDocumentXML(Document document, String authorizationNumber,
			String authorizationDate)throws Exception {
		ConcurrentHashMap<String, Object> reportParameters = new ConcurrentHashMap<String, Object>();
	      
		
		//Document doc = this.loadDocumentFromFile(new File(pathXML));
		/*TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		transformer = tf.newTransformer();
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		String xml = writer.getBuffer().toString();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource archivo = new InputSource();
		archivo.setCharacterStream(new StringReader(xml));
		Document documento = db.parse(archivo);
		documento.getDocumentElement().normalize();*/
		NodeList nodeinfoTributaria = document
				.getElementsByTagName("infoTributaria");
		
		for (int s = 0; s < nodeinfoTributaria.getLength(); s++) {
			Node primerNodo = nodeinfoTributaria.item(s);
			String ambiente;
			String ruc;
			String claveAcceso;
			String razonSocial;
			String dirMatriz;
			String tipoEmision;
			String nombreComercial;
			String estab;
			String ptoEmi;
			String secuencial;
			
			Element primerElemento = (Element) primerNodo;
			ambiente = primerElemento
					.getElementsByTagName("ambiente").item(0)
					.getChildNodes().item(0).getNodeValue();
			
			ruc = primerElemento
					.getElementsByTagName("ruc").item(0)
					.getChildNodes().item(0).getNodeValue();

			claveAcceso = primerElemento
					.getElementsByTagName("claveAcceso").item(0)
					.getChildNodes().item(0).getNodeValue();

			razonSocial = primerElemento
					.getElementsByTagName("razonSocial").item(0)
					.getChildNodes().item(0).getNodeValue();

			dirMatriz = primerElemento
					.getElementsByTagName("dirMatriz").item(0)
					.getChildNodes().item(0).getNodeValue();

			tipoEmision = primerElemento
					.getElementsByTagName("tipoEmision").item(0)
					.getChildNodes().item(0).getNodeValue();

			nombreComercial = primerElemento
					.getElementsByTagName("nombreComercial").item(0)
					.getChildNodes().item(0).getNodeValue();

			estab = primerElemento
					.getElementsByTagName("estab").item(0)
					.getChildNodes().item(0).getNodeValue();

			ptoEmi = primerElemento
					.getElementsByTagName("ptoEmi").item(0)
					.getChildNodes().item(0).getNodeValue();

			secuencial = primerElemento
					.getElementsByTagName("secuencial").item(0)
					.getChildNodes().item(0).getNodeValue();
			
			reportParameters.put("AMBIENTE", ambiente);
			reportParameters.put("RUC", ruc);
			reportParameters.put("CLAVE_ACC", claveAcceso);
			reportParameters.put("RAZON_SOCIAL", razonSocial);
			reportParameters.put("NUM_FACT", 
					estab + "-" + ptoEmi + "-" + secuencial);
			reportParameters.put("DIR_MATRIZ", dirMatriz);
			reportParameters.put("DIR_SUCURSAL", dirMatriz);
			reportParameters.put("NOM_COMERCIAL", 
					nombreComercial);
			reportParameters.put("TIPO_EMISION", tipoEmision);
			reportParameters.put("NUM_AUT", authorizationNumber);
			reportParameters.put("FECHA_AUT", authorizationDate);
			
		}
		String reportName = this.dataFiller.fillReportData(document, reportParameters);

		reportParameters.put("XML_DATA_DOCUMENT", document);
		return new ReportManager(reportName, reportParameters, authorizationNumber).executeReport();
		
	}

	public String readXML(String path) throws Exception {
		Document doc = this.loadDocumentFromFile(new File(path));
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		transformer = tf.newTransformer();
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		String xml = writer.getBuffer().toString();
		return xml;
	}

	private byte[] getBytes() {
		byte[] bytes = null;

		if (this.objeto == null) {
			return null;
		}

		if (this.objeto instanceof byte[]) {
			if (((byte[]) (byte[]) this.objeto).length < 1) {
				return null;
			}
			bytes = (byte[]) (byte[]) this.objeto;
		} else if (this.objeto instanceof String) {
			String objString = (String) this.objeto;
			bytes = Base64.decodeBase64(objString);
		}
		return bytes;
	}

	public Document loadDocumentFromFile(File file) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(file);
	}

}
