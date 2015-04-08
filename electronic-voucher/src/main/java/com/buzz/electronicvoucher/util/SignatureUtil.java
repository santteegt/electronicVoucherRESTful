package com.buzz.electronicvoucher.util;

//import com.fitbank.util.Debug;

import es.mityc.firmaJava.libreria.xades.DataToSign;
import es.mityc.firmaJava.libreria.xades.FirmaXML;
import es.mityc.firmaJava.libreria.xades.XAdESSchemas;
import es.mityc.firmaJava.libreria.xades.elementos.xades.ObjectIdentifier;
import es.mityc.javasign.EnumFormatoFirma;
import es.mityc.javasign.pkstore.CertStoreException;
import es.mityc.javasign.pkstore.IPKStoreManager;
import es.mityc.javasign.pkstore.keystore.KSStore;
import es.mityc.javasign.xml.refs.AllXMLToSign;
import es.mityc.javasign.xml.refs.InternObjectToSign;
import es.mityc.javasign.xml.refs.ObjectToSign;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.xml.security.utils.Constants;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.net.URI;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.List;

import org.apache.xml.security.Init;

import com.buzz.tools.PropertiesHandler;

public class SignatureUtil {
    private Configuration properties;
    private String type = StringUtils.EMPTY;
    private String path_store_file = StringUtils.EMPTY;
    private String key_store_password = StringUtils.EMPTY;
    private String private_key = StringUtils.EMPTY;
    private String private_key_alias = StringUtils.EMPTY;

    static {
        Init.init();
        try {
            Constants.setSignatureSpecNSprefix("ds");
        } catch (Exception e) {
            // Debug.error(e);
        }
    }

    @Deprecated
    public SignatureUtil(String typeStore, String pathStoreFile,
            String keyStorePassword, String privateKey, String keyAlias) {
        this.type = typeStore;
        this.key_store_password = keyStorePassword;
        this.private_key = privateKey;
        this.private_key_alias = keyAlias;
        this.path_store_file = pathStoreFile;
    }

    public SignatureUtil(String typeStore, String pathStoreFile,
            String keyStorePassword, String privateKey) {
        this.type = typeStore;
        this.key_store_password = keyStorePassword;
        this.private_key = privateKey;
        this.path_store_file = pathStoreFile;
    }

    public Document loadDocumentFromFile(File file) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(file);
    }

    private Document commonInputFileProcess(String pathInputFile,
            String pathOutputFile) throws Exception {
        Document docSign = null;
        FirmaXML firma = new FirmaXML();
        DataToSign dataToSign = new DataToSign();
        dataToSign.setXadesFormat(EnumFormatoFirma.XAdES_BES);
        dataToSign.setEsquema(XAdESSchemas.XAdES_132);
        dataToSign.setXMLEncoding("UTF-8");
        // dataToSign.addClaimedRol(new SimpleClaimedRole("Rol de firma"));
        dataToSign.setEnveloped(true);
        dataToSign
                .addObject(new ObjectToSign(new InternObjectToSign(
                        "comprobante"), "contenido comprobante", null,
                        "text/xml", null));
        dataToSign.setParentSignNode("comprobante");
        Document doc = this.loadDocumentFromFile(new File(pathInputFile));

        // Cargamos el almacen de claves
        KeyStore ks = KeyStore.getInstance(type);
        ks.load(new FileInputStream(this.path_store_file),
                key_store_password.toCharArray());
        IPKStoreManager storeManager = new KSStore(ks, new PassStoreKS(
                this.private_key));

        // PrivateKey privateKey = (PrivateKey) ks.getKey(private_key_alias,
        // private_key.toCharArray());

        // Añadimos el KeyInfo del certificado cuya clave privada usamos
        X509Certificate cert = getFirstCertificate(storeManager);
        // X509Certificate cert = (X509Certificate) ks
        // .getCertificate(private_key_alias);
        // Obtenemos la clave privada, pues la necesitaremos para encriptar.
        PrivateKey privateKey = storeManager.getPrivateKey(cert);

        Provider provider = storeManager.getProvider(cert);

        try {
            // Realizamos la firma
            dataToSign.setDocument(doc);
            Object[] objSign = firma.signFile(cert, dataToSign, privateKey,
                    provider);
            docSign = (Document) objSign[0];
        } catch (Exception e) {
            // Debug.error(e);
        }

        return docSign;
    }

    private Document commonInputFileProcess(Document doc) throws Exception {
        Document docSign = null;
        FirmaXML firma = new FirmaXML();
        DataToSign dataToSign = new DataToSign();
        dataToSign.setXadesFormat(EnumFormatoFirma.XAdES_BES);
        dataToSign.setEsquema(XAdESSchemas.XAdES_132);
        dataToSign.setXMLEncoding("UTF-8");
        // dataToSign.addClaimedRol(new SimpleClaimedRole("Rol de firma"));
        dataToSign.setEnveloped(true);
        dataToSign
                .addObject(new ObjectToSign(new InternObjectToSign(
                        "comprobante"), "contenido comprobante", null,
                        "text/xml", null));
        dataToSign.setParentSignNode("comprobante");
        // Document doc = this.loadDocumentFromFile(new File(pathInputFile));

        // Cargamos el almacen de claves
        KeyStore ks = KeyStore.getInstance(type);
        ks.load(new FileInputStream(this.path_store_file),
                key_store_password.toCharArray());
        IPKStoreManager storeManager = new KSStore(ks, new PassStoreKS(
                this.private_key));

        // PrivateKey privateKey = (PrivateKey) ks.getKey(private_key_alias,
        // private_key.toCharArray());

        // Añadimos el KeyInfo del certificado cuya clave privada usamos
        X509Certificate cert = getFirstCertificate(storeManager);
        // X509Certificate cert = (X509Certificate) ks
        // .getCertificate(private_key_alias);
        // Obtenemos la clave privada, pues la necesitaremos para encriptar.
        PrivateKey privateKey = storeManager.getPrivateKey(cert);

        Provider provider = storeManager.getProvider(cert);

        try {
            // Realizamos la firma
            dataToSign.setDocument(doc);
            Object[] objSign = firma.signFile(cert, dataToSign, privateKey,
                    provider);
            docSign = (Document) objSign[0];
        } catch (Exception e) {
            // Debug.error(e);
        }

        return docSign;
    }

    private X509Certificate getFirstCertificate(
            final IPKStoreManager storeManager) {
        List<X509Certificate> certs = null;
        try {
            certs = storeManager.getSignCertificates();
        } catch (CertStoreException ex) {
            System.err.println("Fallo obteniendo listado de certificados");
            System.exit(-1);
        }
        if ((certs == null) || (certs.size() == 0)) {
            System.err.println("Lista de certificados vacía");
            System.exit(-1);
        }

        X509Certificate certificate = certs.get(0);
        return certificate;
    }

    public String processXMLString(String pathInputFile, String pathOutputFile)
            throws Exception {
        Document doc = this.commonInputFileProcess(pathInputFile,
                pathOutputFile);
        File signatureFile = new File(pathOutputFile);
        this.outputDocToFile(doc, signatureFile);
        return this.docToXMLString(doc);
    }

    public String processXMLString(Document doc, String ruc, String clave,
            String nombre) throws Exception {
        this.properties = PropertiesHandler.getInstance("buzzsri");
        String path = StringUtils.EMPTY;
        Document signedDoc = this.commonInputFileProcess(doc);
        path = properties.getString("documents.dir.fir." + ruc);
        File signatureFile = new File(path + nombre + clave + ".xml");
        this.outputDocToFile(doc, signatureFile);
        return this.docToXMLString(signedDoc);
    }

    private String docToXMLString(Document doc) throws Exception {
        String xmlstring = StringUtils.EMPTY;
        DOMSource domSource = new DOMSource(doc);
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

    public void processFileOutput(String pathInputFile, String pathOutputFile)
            throws Exception {
        File signatureFile = new File(pathOutputFile);
        Document doc = this.commonInputFileProcess(pathInputFile,
                pathOutputFile);
        // Guardamos archivo de firma en disco
        this.outputDocToFile(doc, signatureFile);
    }

    private void outputDocToFile(Document doc, File file) throws Exception {
        FileOutputStream f = new FileOutputStream(file);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(f));
        f.close();
    }
}