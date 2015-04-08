package com.buzz.electronicvoucher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
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
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.StringUtil;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.jfree.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.buzz.electronicvoucher.data.DataFiller;
import com.buzz.electronicvoucher.data.VoucherDataFiller;
import com.buzz.electronicvoucher.schema.v1_0_0.ComprobanteRetencion;
import com.buzz.electronicvoucher.schema.v1_1_0.Factura;
import com.buzz.electronicvoucher.schema.v1_1_0.Factura.InfoFactura;
import com.buzz.electronicvoucher.schema.v1_1_0.GuiaRemision;
import com.buzz.electronicvoucher.schema.v1_1_0.InfoTributaria;
import com.buzz.electronicvoucher.schema.v1_1_0.NotaCredito;
import com.buzz.electronicvoucher.schema.v1_1_0.NotaDebito;
import com.buzz.electronicvoucher.util.Modulo11;
import com.buzz.electronicvoucher.util.SOAPClient;
import com.buzz.electronicvoucher.util.SignatureUtil;
import com.buzz.tools.PropertiesHandler;
import com.buzz.tools.ReportManager;
import com.buzz.tools.TokenCreator;

public class ElectronicVoucherSender {

    private ElectronicVoucherTypes voucherType;
    private DataFiller dataFiller;
    private String sessionToken = TokenCreator.createToken();
    private String signedVoucher;
    private Object objeto;
    private Logger log = Logger.getLogger(ElectronicVoucherSender.class);
    private Configuration properties;

    // public static final Logger LOGGER = FitbankLogger.getLogger();

    /**
     * Proceso principal para el manejo de Comprobantes
     * 
     * @param pObject
     * @return
     */
    public JSONObject processVoucher(Object pObject) throws Exception {
        this.properties = PropertiesHandler.getInstance("buzzsri");
        String username = "ADMIN";
        /*
         * Map<String, Object> params = new HashMap<String, Object>();
         * params.put("user", username); Tusuarioid tusuarioid =
         * (Tusuarioid)JPASession
         * .getQueryBean("from Tusuarioid a where cusuario =:user", params);
         */
        Integer ccontribuyente = 1;
        InfoTributaria infoTributaria = (InfoTributaria) pObject.getClass()
                .getDeclaredMethod("getInfoTributaria").invoke(pObject);

        System.setProperty("javax.net.ssl.trustStore", properties
                .getString("signature.jks." + infoTributaria.getRuc()));
        System.setProperty(
                "javax.net.ssl.trustStorePassword",
                properties.getString("signature.jks.password."
                        + infoTributaria.getRuc()));
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        /*
         * String tipo = (String) pDetail.findFieldByNameCreate("MOSTRARDOC")
         * .getValue();
         */
        // Integer company = pDetail.getCompany();
        String pathFileJks = properties.getString("signature.p12."
                + infoTributaria.getRuc());
        String jksPasswordStore = properties
                .getString("signature.jks.password." + infoTributaria.getRuc());
        String jksPassPrivate = properties.getString("signature.jks.password."
                + infoTributaria.getRuc());
        /*
         * String jksPassAlias = PropertiesHandler.getConfig("security")
         * .getString("jks.passAlias");
         */

        JSONObject jsonResponse = new JSONObject();
        // StringWriter voucherWriter = new StringWriter();
        // try{

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document voucherDocument = builder.newDocument();
        JAXBContext context = JAXBContext.newInstance((pObject.getClass()));
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Modulo11 modulo11 = new Modulo11();
        String accessKey = modulo11.obtainEncoding(infoTributaria
                .getClaveAcceso());
        infoTributaria.setClaveAcceso(accessKey);
        log.info("PROCESSING ACCESSKEY: " + accessKey);
        Schema schema = null;
        String nombreDoc = StringUtils.EMPTY;
        String dir = "generados";
        String fechaemision = StringUtils.EMPTY;
        Object objeto = StringUtils.EMPTY;
        if (pObject instanceof Factura) {
            this.voucherType = ElectronicVoucherTypes.BILL;
            schema = schemaFactory.newSchema(Thread.currentThread()
                    .getContextClassLoader().getResource("factura.xsd"));
            nombreDoc = "FACTURA";
            objeto = (Factura) pObject;
            fechaemision = infoTributaria.getClaveAcceso().substring(0, 8);
            // FileOutputStream fos = new
            // FileOutputStream("/Users/santteegt/Desktop/test.xml");
            // guardamos el objeto serializado en un documento XML
            // marshaller.marshal((Factura)pObject, fos);
            // fos.close();
            // marshaller.marshal(pObject, voucherWriter);
            // marshaller.marshal(pObject, voucherDocument);
        }
        if (pObject instanceof ComprobanteRetencion) {
            this.voucherType = ElectronicVoucherTypes.RETENTION;
            schema = schemaFactory.newSchema(Thread.currentThread()
                    .getContextClassLoader()
                    .getResource("comprobanteRetencion1.xsd"));
            marshaller.setSchema(schema);
            nombreDoc = "RETENCION";
            objeto = (ComprobanteRetencion) pObject;

        }
        if (pObject instanceof NotaCredito) {
            this.voucherType = ElectronicVoucherTypes.CREDITNOTE;
            schema = schemaFactory.newSchema(Thread.currentThread()
                    .getContextClassLoader().getResource("notacredito.xsd"));
            marshaller.setSchema(schema);
            nombreDoc = "NOTACREDITO";
            objeto = (NotaCredito) pObject;

        }
        if (pObject instanceof NotaDebito) {
            this.voucherType = ElectronicVoucherTypes.DEBITNOTE;
            schema = schemaFactory.newSchema(Thread.currentThread()
                    .getContextClassLoader().getResource("notaDebito1.xsd"));
            marshaller.setSchema(schema);
            nombreDoc = "NOTADEBITO";
            objeto = (NotaDebito) pObject;

        }

        if (pObject instanceof GuiaRemision) {
            this.voucherType = ElectronicVoucherTypes.GUIDEREMISSION;
            schema = schemaFactory.newSchema(Thread.currentThread()
                    .getContextClassLoader().getResource("guiaremision.xsd"));
            marshaller.setSchema(schema);
            nombreDoc = "GUIAREMISION";
            objeto = (GuiaRemision) pObject;
        }
        this.saveXML(nombreDoc, dir, marshaller,
                infoTributaria.getClaveAcceso(), infoTributaria.getRuc(),
                pObject.getClass(), objeto);
        marshaller.setSchema(schema);
        marshaller.marshal(pObject, voucherDocument);
        this.dataFiller = (DataFiller) Class.forName(
                this.voucherType.getdataFillerClass()).newInstance();
        // ---------INICIO ANTERIOR COD
        SignatureUtil sgp = new SignatureUtil("PKCS12", pathFileJks,
                jksPasswordStore, jksPassPrivate);
        String signedDoc = sgp.processXMLString(voucherDocument,
                infoTributaria.getRuc(), infoTributaria.getClaveAcceso(),
                nombreDoc);
        dir = "firmados";
        String ambiente = infoTributaria.getAmbiente();
        String endpoint = StringUtils.EMPTY;
        String endpointR = StringUtils.EMPTY;
        // objeto=this.readXMLFirmado(signedDoc);
        // this.saveXML(nombreDoc,dir,marshaller,infoTributaria.getClaveAcceso(),
        // infoTributaria.getRuc(), pObject.getClass(), objeto);
        if ("1".equals(ambiente)) {
            endpoint = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes";
        } else if ("2".equals(ambiente)) {
            endpoint = "https://cel.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes";
        }

        String reqEnv = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body><ns1:validarComprobante xmlns:ns1=\"http://ec.gob.sri.ws.recepcion\"><xml xsi:type=\"xsd:base64Binary\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">{0}</xml></ns1:validarComprobante></soapenv:Body></soapenv:Envelope>";
        reqEnv = reqEnv.replaceAll("\\{0\\}",
                org.apache.axis.encoding.Base64.encode(signedDoc.getBytes()));
        log.info("SOAP MESSAGE: " + reqEnv);
        String soapResponse = SOAPClient.getInstance().soapSendReal(endpoint,
                reqEnv);

        this.manageServiceResponse(this.readXMLDocumentEnveloped(soapResponse),
                jsonResponse);
        // jsonResponse.append("fullMessage", soapResponse);
        if ("1".equals(ambiente)) {
            endpointR = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes";
        } else if ("2".equals(ambiente)) {
            endpointR = "https://cel.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes";
        }
        String sopRes = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body><autorizacionComprobante xmlns=\"http://ec.gob.sri.ws.autorizacion\"><claveAccesoComprobante xmlns=\"\">{0}</claveAccesoComprobante></autorizacionComprobante></soapenv:Body></soapenv:Envelope>";
        sopRes = sopRes.replaceAll("\\{0\\}", accessKey);
        String printedSoapResponse = StringUtils.EMPTY;
        Integer intentos = properties.getInt("contador_envios");
 /*     Desdocumentar si queda a eleccion del usuario ejecutar en línea o por lotes asincronamente
        if(chkEnLinea==true)
        {
            Integer cont = 0;
            do {
                printedSoapResponse = SOAPClient.getInstance().soapSendReal(
                        endpointR, sopRes);
                cont = cont + 1;
            } while (printedSoapResponse.contains("AUTORIZADO") == false
                    && cont < intentos);
        }
        else
        {*/
            ElectronicVoucherThread thread = new ElectronicVoucherThread(printedSoapResponse, endpointR, sopRes, intentos);
            thread.start();  
            do
            {
                printedSoapResponse = thread.getPrintedSoapResponse();
            }while(printedSoapResponse.compareTo("")==0);
            thread.terminate();
            thread.join();
            
/*        } */
        Boolean isAuthorized = this.readDocumentReturnSri(printedSoapResponse,
                voucherDocument, jsonResponse, infoTributaria.getRuc(),
                nombreDoc + infoTributaria.getClaveAcceso());
        // jsonResponse.append("fullMessage2", printedSoapResponse);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("status",
                ElectronicVoucherStatusTypes.AUTHORIZED.getStatus());
        parameters.put("jsonResponse", jsonResponse);
        parameters.put("sessionToken", this.sessionToken);
        parameters.put("signedVoucher", this.signedVoucher);

        VoucherDataFiller processedVoucher = new VoucherDataFiller();
        processedVoucher.setParameters(parameters,
                processedVoucher.getReportName(), infoTributaria.getRuc());
        processedVoucher.fillData(ccontribuyente, pObject);

        if (isAuthorized) {
            // this.dataFiller.fillData(pk, voucher);
        }
        return jsonResponse;
    }

    public String signAndSendSRI(String accessKey, String dir,
            String nombreDoc, String pathFileJks, String jksPasswordStore,
            String jksPassPrivate, InfoTributaria infoTributaria,
            Marshaller marshaller, Document voucherDocument, Object pObject,
            JSONObject jsonResponse, Object objeto) throws Exception {
        SignatureUtil sgp = new SignatureUtil("PKCS12", pathFileJks,
                jksPasswordStore, jksPassPrivate);
        String signedDoc = sgp.processXMLString(voucherDocument,
                infoTributaria.getRuc(), infoTributaria.getClaveAcceso(),
                nombreDoc);
        dir = "firmados";
        this.saveXML(nombreDoc, dir, marshaller,
                infoTributaria.getClaveAcceso(), infoTributaria.getRuc(),
                pObject.getClass(), objeto);
        String endpoint = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes";
        String reqEnv = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body><ns1:validarComprobante xmlns:ns1=\"http://ec.gob.sri.ws.recepcion\"><xml xsi:type=\"xsd:base64Binary\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">{0}</xml></ns1:validarComprobante></soapenv:Body></soapenv:Envelope>";
        reqEnv = reqEnv.replaceAll("\\{0\\}",
                org.apache.axis.encoding.Base64.encode(signedDoc.getBytes()));
        try {
            log.info("SOAP MESSAGE: " + reqEnv);
            String soapResponse = SOAPClient.getInstance().soapSendReal(
                    endpoint, reqEnv);
            this.manageServiceResponse(
                    this.readXMLDocumentEnveloped(soapResponse), jsonResponse);
        } catch (Exception e) {
            return "SINCONEXION";
        }
        // jsonResponse.append("fullMessage", soapResponse);
        String endpointR = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes";
        String sopRes = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body><autorizacionComprobante xmlns=\"http://ec.gob.sri.ws.autorizacion\"><claveAccesoComprobante xmlns=\"\">{0}</claveAccesoComprobante></autorizacionComprobante></soapenv:Body></soapenv:Envelope>";
        sopRes = sopRes.replaceAll("\\{0\\}", accessKey);
        // ----fin
        try {
            return SOAPClient.getInstance().soapSendReal(endpointR, sopRes);
        } catch (Exception e) {
            return "SINCONEXION";
        }
    }

    public String saveNoSend(String dir, String fechaemision, String nombreDoc,
            InfoTributaria infoTributaria, Object pObject,
            Marshaller marshaller, Object objeto) throws Exception {
        if ("no_enviados".equals(dir)) {
            File archivo = new File(
                    "/home/diego/BUZZSRI/clavescontingenciapruebas.txt");
            File aux = new File("/home/diego/BUZZSRI/aux.txt");
            FileReader fr = new FileReader(archivo);
            FileReader fraux = new FileReader(aux);
            BufferedReader br = new BufferedReader(fr);
            BufferedReader braux = new BufferedReader(fraux);
            FileWriter fw = new FileWriter(aux, false);
            FileWriter fwaux = new FileWriter(archivo, false);
            // Lectura del fichero
            String bandera = "0";
            String linea;
            String cadena;
            String claveContingencia = StringUtils.EMPTY;
            while ((linea = br.readLine()) != null) {
                cadena = linea.substring(0, 2);
                if (!"--".equals(cadena) && "0".equals(bandera)) {
                    claveContingencia = linea;
                    fw.write("--" + linea + "\n");
                    bandera = "1";
                } else {
                    fw.write(linea + "\n");
                }
            }
            fw.close();
            while ((linea = braux.readLine()) != null) {
                fwaux.write(linea + "\n");
            }
            if (claveContingencia != "" || claveContingencia != null) {

            }
            fwaux.close();
            infoTributaria.setClaveAcceso(calculatePassword(
                    infoTributaria.getCodDoc(), fechaemision,
                    claveContingencia, infoTributaria.getTipoEmision()));
            this.saveXML(nombreDoc, dir, marshaller,
                    infoTributaria.getClaveAcceso(), infoTributaria.getRuc(),
                    pObject.getClass(), objeto);
            return claveContingencia;
        }
        return null;

    }

    public String calculatePassword(String comprobante, String fechaEmision,
            String claveContingencia, String emision) {
        Integer suma = 0;
        Integer sumt = 0;
        Integer con = 2;
        Integer j = 48;
        String clave = StringUtils.EMPTY;
        clave = fechaEmision + comprobante + claveContingencia + emision;
        for (int i = clave.length(); i > 0; i--) {
            suma = Integer.parseInt(clave.substring(i - 1, i)) * con;
            sumt = sumt + suma;
            j = j - 1;
            if (con == 7) {
                con = 2;
            } else {
                con = con + 1;
            }
        }
        sumt = 11 - (sumt % 11);
        if (sumt == 11) {
            sumt = 0;
        }
        if (sumt == 10) {
            sumt = 1;
        }
        clave = clave + sumt.toString();
        return clave;
    }

    private void manageServiceResponse(Document doc, JSONObject json)
            throws Exception {
        String status = doc.getElementsByTagName("estado").item(0)
                .getChildNodes().item(0).getNodeValue();
        // json.append("estado", status);
        NodeList voucherList = doc.getElementsByTagName("comprobantes").item(0)
                .getChildNodes();
        JSONArray voucherArray = new JSONArray();
        JSONObject headResponseAdicional = new JSONObject();
        for (int i = 0; i < voucherList.getLength(); i++) {
            // JSONObject voucher = new JSONObject();
            Element voucherNode = (Element) voucherList.item(i);
            String accessKey = voucherNode.getElementsByTagName("claveAcceso")
                    .item(0).getChildNodes().item(0).getNodeValue();
            // voucher.append("claveAcceso", accessKey);
            NodeList errorMessages = voucherNode
                    .getElementsByTagName("mensajes").item(0).getChildNodes();

            JSONArray errorArray = this.getResponseErrorMessages(errorMessages);
            JSONArray errorArrayAdicional = this
                    .createNodoErrorJson(errorMessages);
            if (errorArray.length() > 0) {
                // voucher.append("mensajes", errorArray);
                // voucherArray.put(voucher);
                headResponseAdicional.append("mensajesdelSRI",
                        errorArrayAdicional);
                voucherArray.put(headResponseAdicional);
            }
        }
        if (voucherArray.length() > 0) {
            // json.append("comprobantes", voucherArray);
            json.append("SRImensajeResponse", headResponseAdicional);
        }
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

    private Document readXMLDocumentEnveloped(String xml) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource archivo = new InputSource();
        archivo.setCharacterStream(new StringReader(xml));
        Document documento = db.parse(archivo);
        documento.getDocumentElement().normalize();
        return documento;
    }

    private Document readXMLFirmado(String xml) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource archivo = new InputSource();
        archivo.setCharacterStream(new StringReader(xml));
        Document documento = db.parse(archivo);
        documento.getDocumentElement().normalize();
        return documento;
    }

    public Boolean readDocumentReturnSri(String xml, Document voucherDocument,
            JSONObject jsonResponse, String ruc, String nombreDoc)
            throws Exception {
        Boolean result = false;
        Document documento = this.readXMLDocumentEnveloped(xml);
        Boolean rompeFor = false;
        NodeList nodeList = documento.getElementsByTagName("autorizacion");
        for (int s = 0; s < nodeList.getLength(); s++) {
            Element authorization = (Element) nodeList.item(s);
            String estado;
            String numeroAutorizacion;
            String fechaAutorizacion;
            String ambiente;
            String authorizationStatus = authorization
                    .getElementsByTagName("estado").item(0).getChildNodes()
                    .item(0).getNodeValue();

            JSONObject headResponse = new JSONObject();
            JSONObject headResponseAdicional = new JSONObject();
            // headResponse.append("estado", authorizationStatus);
            String authorizationDate = authorization
                    .getElementsByTagName("fechaAutorizacion").item(0)
                    .getChildNodes().item(0).getNodeValue();
            String authorizacionProcessEnviroment = authorization
                    .getElementsByTagName("ambiente").item(0).getChildNodes()
                    .item(0).getNodeValue();

            // headResponse.append("fechaAutorizacion", authorizationDate);
            // headResponse.append("ambiente", authorizacionProcessEnviroment);
            JSONArray autorizadoArray = new JSONArray();
            JSONObject autorizado = new JSONObject();
            if (ElectronicVoucherStatusTypes.AUTHORIZED.getStatus().equals(
                    authorizationStatus)) {
                result = true;
                String authorizationNumber = authorization
                        .getElementsByTagName("numeroAutorizacion").item(0)
                        .getChildNodes().item(0).getNodeValue();

                this.signedVoucher = authorization
                        .getElementsByTagName("comprobante").item(0)
                        .getChildNodes().item(0).getNodeValue();

                // headResponse.append("numeroAutorizacion",
                // authorizationNumber);
                autorizado.append("estado", "AUTORIZADO");
                autorizado.append("informacion", authorizationNumber);
                autorizado.append("identificador", "00");
                autorizadoArray.put(autorizado);
                headResponseAdicional.append("mensajesdelSRI", autorizadoArray);
                this.readDocumentXML(voucherDocument, authorizationNumber,
                        authorizationDate);
                // jsonResponse.append("voucherReport", "");
                String pathNotAutorized = properties
                        .getString("documents.dir.aut." + ruc);
                FileOutputStream file = new FileOutputStream(pathNotAutorized
                        + nombreDoc + ".xml");
                file.write(xml.getBytes("UTF-8"));
                file.close();
            } else if (ElectronicVoucherStatusTypes.NONAUTHORIZED.getStatus()
                    .equals(authorizationStatus)) {
                rompeFor = true;
                String pathNotAutorized = properties
                        .getString("documents.dir.no_aut." + ruc);
                FileOutputStream file = new FileOutputStream(pathNotAutorized
                        + nombreDoc + ".xml");
                file.write(xml.getBytes("UTF-8"));
                file.close();
                NodeList errorMessages = authorization
                        .getElementsByTagName("mensajes").item(0)
                        .getChildNodes();
                // JSONArray errorArray = this
                // .getResponseErrorMessages(errorMessages);
                JSONArray errorArrayAdicional = this
                        .createNodoErrorJson(errorMessages);
                if (errorArrayAdicional.length() > 0) {
                    // headResponse.append("mensajes", errorArray);
                    headResponseAdicional.append("mensajesdelSRI",
                            errorArrayAdicional);
                }

            } else {
                autorizado.append("estado", "Sin Respuesta");
                autorizado.append("informacion",
                        "Error en la respuesta del SRI");
                autorizado.append("identificador", "99");
                autorizadoArray.put(autorizado);
                headResponseAdicional.append("mensajesdelSRI", autorizadoArray);
                rompeFor = true;
            }

            // jsonResponse.append("SRIresponse", headResponse);
            if (rompeFor == true) {
                jsonResponse
                        .append("SRImensajeResponse", headResponseAdicional);
                break;
            }
            jsonResponse.append("SRImensajeResponse", headResponseAdicional);
        }
        return result;
    }

    private JSONArray createNodoErrorJson(NodeList errorMessages)
            throws DOMException, JSONException {
        JSONArray errorArrayAdicional = new JSONArray();
        JSONObject error = new JSONObject();
        Element errorNode = (Element) errorMessages.item(0);
        error.append("estado", "NO AUTORIZADO");
        error.append("informacion", errorNode.getElementsByTagName("mensaje")
                .item(0).getChildNodes().item(0).getNodeValue());
        error.append("identificador",
                errorNode.getElementsByTagName("identificador").item(0)
                        .getChildNodes().item(0).getNodeValue());
        errorArrayAdicional.put(error);
        return errorArrayAdicional;
    }

    private JSONArray getResponseErrorMessages(NodeList errorMessages)
            throws Exception {
        JSONArray errorArray = new JSONArray();
        for (int j = 0; j < errorMessages.getLength(); j++) {
            JSONObject error = new JSONObject();
            Element errorNode = (Element) errorMessages.item(j);
            error.append("identificador",
                    errorNode.getElementsByTagName("identificador").item(0)
                            .getChildNodes().item(0).getNodeValue());
            error.append("tipo", errorNode.getElementsByTagName("tipo").item(0)
                    .getChildNodes().item(0).getNodeValue());
            error.append("mensaje", errorNode.getElementsByTagName("mensaje")
                    .item(0).getChildNodes().item(0).getNodeValue());
            if (errorNode.getElementsByTagName("informacionAdicional")
                    .getLength() > 0) {
                error.append("informacionAdicional", errorNode
                        .getElementsByTagName("informacionAdicional").item(0)
                        .getChildNodes().item(0).getNodeValue());
            }
            errorArray.put(error);
        }
        return errorArray;
    }

    public JSONObject readDocumentXML(Document document,
            String authorizationNumber, String authorizationDate)
            throws Exception {
        ConcurrentHashMap<String, Object> reportParameters = new ConcurrentHashMap<String, Object>();

        // Document doc = this.loadDocumentFromFile(new File(pathXML));
        /*
         * TransformerFactory tf = TransformerFactory.newInstance(); Transformer
         * transformer; transformer = tf.newTransformer(); StringWriter writer =
         * new StringWriter(); transformer.transform(new DOMSource(doc), new
         * StreamResult(writer)); String xml = writer.getBuffer().toString();
         * DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         * DocumentBuilder db = dbf.newDocumentBuilder(); InputSource archivo =
         * new InputSource(); archivo.setCharacterStream(new StringReader(xml));
         * Document documento = db.parse(archivo);
         * documento.getDocumentElement().normalize();
         */
        NodeList nodeinfoTributaria = document
                .getElementsByTagName("infoTributaria");
        String ruc = StringUtils.EMPTY;
        String pathLogo = StringUtils.EMPTY;
        String pathMarcaAgua = StringUtils.EMPTY;
        for (int s = 0; s < nodeinfoTributaria.getLength(); s++) {
            Node primerNodo = nodeinfoTributaria.item(s);
            String ambiente;
            String claveAcceso;
            String razonSocial;
            String dirMatriz;
            String tipoEmision;
            String nombreComercial;
            String estab;
            String ptoEmi;
            String secuencial;

            Element primerElemento = (Element) primerNodo;
            ambiente = primerElemento.getElementsByTagName("ambiente").item(0)
                    .getChildNodes().item(0).getNodeValue();

            ruc = primerElemento.getElementsByTagName("ruc").item(0)
                    .getChildNodes().item(0).getNodeValue();

            claveAcceso = primerElemento.getElementsByTagName("claveAcceso")
                    .item(0).getChildNodes().item(0).getNodeValue();

            razonSocial = primerElemento.getElementsByTagName("razonSocial")
                    .item(0).getChildNodes().item(0).getNodeValue();

            dirMatriz = primerElemento.getElementsByTagName("dirMatriz")
                    .item(0).getChildNodes().item(0).getNodeValue();

            tipoEmision = primerElemento.getElementsByTagName("tipoEmision")
                    .item(0).getChildNodes().item(0).getNodeValue();

            nombreComercial = primerElemento
                    .getElementsByTagName("nombreComercial").item(0)
                    .getChildNodes().item(0).getNodeValue();

            estab = primerElemento.getElementsByTagName("estab").item(0)
                    .getChildNodes().item(0).getNodeValue();

            ptoEmi = primerElemento.getElementsByTagName("ptoEmi").item(0)
                    .getChildNodes().item(0).getNodeValue();

            secuencial = primerElemento.getElementsByTagName("secuencial")
                    .item(0).getChildNodes().item(0).getNodeValue();

            pathLogo = properties.getString("report.dir.logo." + ruc);
            pathMarcaAgua = properties.getString("report.dir.logofondo");
            reportParameters.put("LOGO", pathLogo);
            if ("1".equals(ambiente)) {
                reportParameters.put("AMBIENTE", "PRUEBAS");
                reportParameters.put("MARCA_AGUA", pathMarcaAgua
                        + "pruebas.jpeg");
            } else {
                reportParameters.put("AMBIENTE", "PRODUCCION");
                reportParameters.put("MARCA_AGUA", pathMarcaAgua
                        + "produccion.jpeg");
            }
            if ("1".equals(tipoEmision)) {
                reportParameters.put("TIPO_EMISION", "NORMAL");
            } else {
                reportParameters.put("TIPO_EMISION", "CONTINGENCIAS");
            }
            reportParameters.put("RUC", ruc);
            reportParameters.put("CLAVE_ACC", claveAcceso);
            reportParameters.put("RAZON_SOCIAL", razonSocial);
            reportParameters.put("NUM_FACT", estab + "-" + ptoEmi + "-"
                    + secuencial);
            reportParameters.put("DIR_MATRIZ", dirMatriz);            
            reportParameters.put("NOM_COMERCIAL", nombreComercial);
            reportParameters.put("EXENTO_IVA", "0");            
            reportParameters.put("NUM_AUT", authorizationNumber);
            reportParameters.put("FECHA_AUT", authorizationDate);

        }
        String reportName = this.dataFiller.fillReportData(document,
                reportParameters);

        reportParameters.put("XML_DATA_DOCUMENT", document);
        return new ReportManager(ruc, reportName, reportParameters,
                authorizationNumber).executeReport();

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

    public void saveXML(String nombre, String dir, Marshaller marshaller,
            String clave, String ruc, Class clase, Object documento)
            throws Exception {
        String path = StringUtils.EMPTY;
        if ("generados".equals(dir)) {
            path = properties.getString("documents.dir.gen." + ruc);
        }
        if ("firmados".equals(dir)) {
            path = properties.getString("documents.dir.fir." + ruc);
        }
        if ("no_enviados".equals(dir)) {
            path = properties.getString("documents.dir.no_env." + ruc);
        }
        FileOutputStream file = new FileOutputStream(path + nombre + clave);
        marshaller.marshal(documento, file);
        file.close();
    }
}
