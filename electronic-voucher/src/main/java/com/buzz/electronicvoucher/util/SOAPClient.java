package com.buzz.electronicvoucher.util;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
//import org.apache.xml.serializer.ToXMLStream;
import org.w3c.dom.Node;
/*
import com.fitbank.common.FileHelper;
import com.fitbank.common.helper.XmlHelper;
import com.fitbank.common.logger.FitbankLogger;
import com.fitbank.common.properties.PropertiesHandler;
*/

/**
 * @author FitBank
 */

public final class SOAPClient {
    /** Instancia singleton de SOAPClient */
    private static SOAPClient instance = null;

    /**
     * Obtiene la instancia singleton de SOAPClient
     * 
     * @return singleton
     */
    public static SOAPClient getInstance() {
        synchronized (SOAPClient.class) {
            if (SOAPClient.instance == null) {
                SOAPClient.instance = new SOAPClient();
            }
        }
        return SOAPClient.instance;
    }

    /**
     * Clase main para pruebas
     * 
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.exit(0);
        }
        try {
            //FitbankLogger.getLogger().debug("EndPoint |" + args[0] + "| Archivo de Peticion |" + args[1] + "|");
            //String rq = FileHelper.readFile(args[1]);
            //FitbankLogger.getLogger().info("Peticion " + rq);
            // FitbankLogger.getLogger().info("Respuesta " +
            // SOAPClient.getInstance().soapSend(args[0], rq));
            // System.out.println(SOAPClient.getInstance().soapSendReal(args[0],
            // rq));
        } catch (Exception e) {
            //FitbankLogger.getLogger().error(e, e);
        }
    }

    /**
     * Contructor sin parametros
     */
    private SOAPClient() {
    }

    /**
     * Envia una peticion SOAP en base a los parametros definido en el PropertiesHandler
     * 
     * @param pEndpintURL
     * @param pSoapRequest
     * @param ph
     * @return data resultado de la peticion
     * @throws Exception
     */
    /**
    public String soapSend(String pEndpintURL, String pSoapRequest, PropertiesHandler ph) throws Exception {
        try {
            FitbankLogger.getLogger().debug("ENDPOINT " + pEndpintURL);

            String file = ph.getStringValue("ws.ofac.path") + "/FL" + Double.valueOf(Math.random() * 100000).intValue()
                    + ".txt";

            FileOutputStream fout = new FileOutputStream(file);
            try {
                fout.write(pSoapRequest.getBytes());
            } finally {
                fout.close();
            }

            String command = ph.getStringValue("ws.ofac.command") + " " + pEndpintURL + " " + file;
			*/
            /*
             * Descomentar para pruebas en Windows String command =File.separator
             * +"Fitbank"+File.separator+"soap"+File.separator+"run.bat" + " " + EndpintURL + " " + file;
             */
    		/*
            FitbankLogger.getLogger().debug("OFAC " + command);

            Process p = Runtime.getRuntime().exec(command);

            String data = FileHelper.readStream(p.getInputStream());
            FitbankLogger.getLogger().debug("Respuesta |" + data + "|");
            return data;
        } catch (Exception e) {
            FitbankLogger.getLogger().error(e, e);
            throw e;
        }
    }*/

    public SOAPMessage prepareMessage(String pSoapRequest) throws Exception {
        return this.prepareMessage(pSoapRequest, "ISO-8859-1");
    }

    /**
     * Prepara el mensaje SOAP a ser enviado
     * 
     * @param pSoapRequest
     * @return
     * @throws Exception
     */
    public SOAPMessage prepareMessage(String pSoapRequest, String pEncoding) throws Exception {
        MessageFactory msgFactory = MessageFactory.newInstance();
        //FitbankLogger.getLogger().debug("MessageFactory |" + msgFactory.getClass().getName() + "|");
        return msgFactory.createMessage(null, new ByteArrayInputStream(
        // ("<?xml version='1.0' encoding='ISO-8859-1'?>" + pSoapRequest).getBytes()));
                ("<?xml version='1.0' encoding='" + pEncoding + "'?>" + pSoapRequest).getBytes()));
    }

    /**
     * Envia una peticion SOAP para pruebas mediante una nueva conexion
     * 
     * @param pEndpintURL
     * @param pSoapRequest
     * @return resp
     * @throws Exception
     */
    public String soapSendReal(String pEndpintURL, String pSoapRequest) throws Exception {
        //FitbankLogger.getLogger().debug("EndPoint |" + pEndpintURL + "| Peticion |" + pSoapRequest + "|");
        // MessageFactory msgFactory = new MessageFactoryImpl();
        SOAPConnection con = SOAPConnectionFactory.newInstance().createConnection();
        SOAPMessage reqMsg = this.prepareMessage(pSoapRequest);
        URL epURL = new URL(pEndpintURL);
        SOAPMessage resMsg = con.call(reqMsg, epURL);
        String resp = this.nodeToString(resMsg.getSOAPBody().getOwnerDocument());
        //FitbankLogger.getLogger().debug(" Respuesta |" + resp + "|");
        return resp;
    }
    
    public Document soapSendRealDocument(String pEndpintURL, String pSoapRequest) throws Exception {
        //FitbankLogger.getLogger().debug("EndPoint |" + pEndpintURL + "| Peticion |" + pSoapRequest + "|");
        // MessageFactory msgFactory = new MessageFactoryImpl();
        SOAPConnection con = SOAPConnectionFactory.newInstance().createConnection();
        SOAPMessage reqMsg = this.prepareMessage(pSoapRequest);
        URL epURL = new URL(pEndpintURL);
        SOAPMessage resMsg = con.call(reqMsg, epURL);
        return resMsg.getSOAPBody().getOwnerDocument();
        
    }
    
    /**
     * Entega un string dado un Nodo.
     * 
     * @param pNode
     *            Nodo a serializar.
     * @return
     * @throws Exception
     */
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
    	
    	
    	/*
    	
        String data = "";
        ToXMLStream dser = (ToXMLStream) (new ToXMLStream()).asDOMSerializer();

        StringWriter sw = new StringWriter();
        dser.setWriter(sw);
        dser.serialize(pNode);
        String element = sw.toString();
        data = element.substring(element.indexOf(">") + 1);
        return data;*/
    }
}
