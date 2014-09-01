package com.buzz.electronicvoucher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.concurrent.ConcurrentHashMap;

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

import com.buzz.electronicvoucher.schema.v1_0_0.ComprobanteRetencion;
import com.buzz.electronicvoucher.schema.v1_1_0.Factura;
import com.buzz.electronicvoucher.schema.v1_1_0.InfoTributaria;
import com.buzz.electronicvoucher.util.Modulo11;
import com.buzz.electronicvoucher.util.SOAPClient;
import com.buzz.electronicvoucher.util.SignatureUtil;
import com.buzz.tools.ReportManager;

public class ElectronicVoucherSender {
	
	private Object objeto;
	private String nombre;
	private Logger log = Logger.getLogger(ElectronicVoucherSender.class);
	//public static final Logger LOGGER = FitbankLogger.getLogger();
	
	/**
     * Proceso principal para el manejo de Comprobantes
     * @param pObject
     * @return
     */
    public JSONObject processVoucher(Object pObject) {
    	
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
    	try{
	    	
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
	    		Schema schema = schemaFactory.newSchema(
	    				Thread.currentThread().getContextClassLoader()
	    				.getResource("comprobanteRetencion1.xsd"));
                marshaller.setSchema(schema);
	    		
	    	}
	    	marshaller.marshal(pObject, voucherDocument);
	    	
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
			
			this.readDocumentReturnSri(printedSoapResponse, voucherDocument, jsonResponse);
			jsonResponse.append("fullMessage2", printedSoapResponse);
			
    	}catch(Exception e){
    		
    		log.info(e);
    		log.error(e);
    		try{
	    		jsonResponse = new JSONObject();
	    		jsonResponse.append("estado", "Internal Error");
	    		jsonResponse.append("fullMessage", e.getMessage());
	    		jsonResponse.append("stacktrace", ExceptionUtils.getStackTrace(e));
    		}catch(JSONException ejson) {
    			log.info(e.getMessage());
    		}
    	}finally{
    		
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
    		/*JSONArray errorArray = new JSONArray();
    		for(int j=0;j< errorMessages.getLength() ; j++) {
    			JSONObject error = new JSONObject();
    			Element errorNode = (Element)errorMessages.item(j);
    			error.append("identificador",
    					errorNode.getElementsByTagName("identificador").item(0).getChildNodes().item(0).getNodeValue());
    			error.append("tipo",
    					errorNode.getElementsByTagName("tipo").item(0).getChildNodes().item(0).getNodeValue());
    			error.append("mensaje",
    					errorNode.getElementsByTagName("mensaje").item(0).getChildNodes().item(0).getNodeValue());
    			error.append("informacionAdicional",
    					errorNode.getElementsByTagName("informacionAdicional").item(0).getChildNodes().item(0).getNodeValue());
    			errorArray.put(error);
    		}
    		voucher.append("mensajes", errorArray);*/
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

	public void readDocumentReturnSri(String xml, Document voucherDocument, JSONObject jsonResponse)
			throws Exception {
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
				
				String authorizationNumber = authorization
						.getElementsByTagName("numeroAutorizacion").item(0)
						.getChildNodes().item(0).getNodeValue();
				
				String signedXMLFile =  authorization
						.getElementsByTagName("comprobante").item(0)
						.getChildNodes().item(0).getNodeValue();
				
				headResponse.append("numeroAutorizacion", authorizationNumber);
								
				jsonResponse.append("voucherReport", 
						this.readDocumentXML(voucherDocument) );
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

	public JSONObject readDocumentXML(Document document)
			throws Exception {
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
		NodeList nodeinfoFactura = document
				.getElementsByTagName("infoFactura");
		NodeList nodetotalImpuesto = document
				.getElementsByTagName("totalImpuesto");
		for (int s = 0; s < nodeinfoTributaria.getLength(); s++) {
			Node primerNodo = nodeinfoTributaria.item(s);
			String ruc;
			String claveAcceso;
			String razonSocial;
			String dirMatriz;
			String tipoEmision;
			String nombreComercial;
			String estab;
			String ptoEmi;
			String secuencial;
			String ivas = "0";
			//if (primerNodo.getNodeType() == Node.ELEMENT_NODE) {
				Element primerElemento = (Element) primerNodo;
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
				reportParameters.put("IVA_0", ivas);
				reportParameters.put("IVA_12", ivas);
				reportParameters.put("NO_OBJETO_IVA", ivas);
			//}
		}
		for (int s = 0; s < nodeinfoFactura.getLength(); s++) {
			Node primerNodo = nodeinfoFactura.item(s);
			String fechaEmision;
			String contribuyenteEspecial;
			String obligadoContabilidad;
			String razonSocialComprador;
			String identificacionComprador;
			String propina;
			String importeTotal;
			String totalSinImpuestos;
			String totalDescuento;
			//if (primerNodo.getNodeType() == Node.ELEMENT_NODE) {
				Element primerElemento = (Element) primerNodo;
				fechaEmision = primerElemento
						.getElementsByTagName("fechaEmision").item(0)
						.getChildNodes().item(0).getNodeValue();

				contribuyenteEspecial = primerElemento
						.getElementsByTagName("contribuyenteEspecial").item(0)
						.getChildNodes().item(0).getNodeValue();

				obligadoContabilidad = primerElemento
						.getElementsByTagName("obligadoContabilidad").item(0)
						.getChildNodes().item(0).getNodeValue();

				razonSocialComprador = primerElemento
						.getElementsByTagName("razonSocialComprador").item(0)
						.getChildNodes().item(0).getNodeValue();

				identificacionComprador = primerElemento
						.getElementsByTagName("identificacionComprador").item(0)
						.getChildNodes().item(0).getNodeValue();

				propina = primerElemento
						.getElementsByTagName("propina").item(0)
						.getChildNodes().item(0).getNodeValue();

				importeTotal = primerElemento
						.getElementsByTagName("importeTotal").item(0)
						.getChildNodes().item(0).getNodeValue();

				totalSinImpuestos = primerElemento
						.getElementsByTagName("totalSinImpuestos").item(0)
						.getChildNodes().item(0).getNodeValue();

				totalDescuento = primerElemento
						.getElementsByTagName("totalDescuento").item(0)
						.getChildNodes().item(0).getNodeValue();

				
				reportParameters.put("FECHA_EMISION", fechaEmision);
				reportParameters.put("CONT_ESPECIAL", 
						contribuyenteEspecial);
				reportParameters.put("LLEVA_CONTABILIDAD", 
						obligadoContabilidad);
				reportParameters.put("RS_COMPRADOR", 
						razonSocialComprador);
				reportParameters.put("RUC_COMPRADOR", 
						identificacionComprador);
				reportParameters.put("PROPINA", propina);
				reportParameters.put("SUBTOTAL", totalSinImpuestos);
				reportParameters.put("TOTAL_DESCUENTO", 
						totalDescuento);
				reportParameters.put("VALOR_TOTAL", importeTotal);
			//}
		}
		for (int s = 0; s < nodetotalImpuesto.getLength(); s++) {
			Node primerNodo = nodetotalImpuesto.item(s);
			String codigo;
			String valor;
			Element primerElemento = (Element) primerNodo;
			codigo = primerElemento
					.getElementsByTagName("codigo").item(0)
					.getChildNodes().item(0).getNodeValue();

			valor = primerElemento
					.getElementsByTagName("valor").item(0)
					.getChildNodes().item(0).getNodeValue();

			if ("3".equals(codigo)) {
				reportParameters.put("ICE", valor);
			} else {
				reportParameters.put("IVA", valor);
			}
		}
		reportParameters.put("XML_DATA_DOCUMENT", document);
		return new ReportManager("", reportParameters, "PDF").executeReport();
		
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
