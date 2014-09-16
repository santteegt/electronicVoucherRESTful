package com.buzz.restfulservice.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import com.buzz.electronicvoucher.ElectronicVoucherSender;
import com.buzz.electronicvoucher.schema.v1_0_0.ComprobanteRetencion;
import com.buzz.electronicvoucher.schema.v1_1_0.Factura;
import com.buzz.electronicvoucher.schema.v1_1_0.InfoTributaria;
import com.buzz.electronicvoucher.schema.v1_1_0.Factura.Retenciones.Retencion;
import com.buzz.electronicvoucher.util.Modulo11;
import com.buzz.persistence.util.JPAServerSession;
import com.buzz.persistence.util.JPASession;
import com.buzz.restfulservice.model.User;
import com.buzz.restfulservice.model.Users;
import com.buzz.tools.PropertiesHandler;
import com.sun.org.apache.xerces.internal.dom.DocumentImpl;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "voucherreceptor")
@Path("/voucherreceptor")
public class VoucherReceptorService
{
	private Logger log = Logger.getLogger(VoucherReceptorService.class);
	
	private Configuration properties;
	
	@XmlElement(name="billing")
	private String url1 = "/voucherreceptor/bill";
	
	@XmlElement(name="report")
	private String url2 = "/voucherreceptor/report";
	
	
	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}
	
	public String getUrl2() {
		return url1;
	}

	public void setUrl2(String url1) {
		this.url1 = url1;
	}

	@GET
    @Path("/")
    @Produces("application/vnd.com.demo.user-management+xml;charset=UTF-8;version=1")
	//@Produces("application/json")
    public VoucherReceptorService getServiceInfo() {
        return new VoucherReceptorService();
    }
	
    @GET
    @Path("/users/{id}")
    @Produces("application/json")
    public Response getUserById(@PathParam("id") Integer id)
    {
    	log.info("************ENTRA AL METODO POST");
        User user = new User();
        user.setId(id);
        user.setFirstName("Lokesh");
        user.setLastName("Gupta");
        return Response.status(200).entity(user).build();
    }
    
    @POST
    @Path("/bill")
    @Consumes("application/json")
    @Produces("application/json")
    public Response processBilling(Factura pFactura)
    {
    	JSONObject response = new JSONObject();
    	try{
	    	log.info("************ENTRA AL METODO POST BILL");
	    	//JPAServerSession.beginTransaction();
	    	JPASession.beginTransaction();
	    	ElectronicVoucherSender sender = new ElectronicVoucherSender();
	    	response = sender.processVoucher(pFactura);
    	}catch(Exception e) {
    		log.info(e);
    		log.error(e);
    		try{
	    		response.append("estado", "Internal Error");
	    		response.append("fullMessage", e.getMessage());
	    		response.append("stacktrace", ExceptionUtils.getStackTrace(e));
    		}catch(JSONException ejson) {
    			log.info(e.getMessage());
    		}
    		
    	}
    	finally{
    		try{
    			//JPAServerSession.commitTransaction(true);
    			JPASession.commitTransaction(true);
    		}catch(Exception e) {
    			try{
    				JPASession.rollbackTransaction();
        		}catch(Exception er) {
        			try{
	        			response.append("estado", "Internal Error");
	    	    		response.append("fullMessage", e.getMessage());
	    	    		response.append("stacktrace", ExceptionUtils.getStackTrace(er));
        			}catch(JSONException jsone){
    	    			log.info(jsone.getMessage());
    	    		}
        		}
    			log.info(e.getMessage());
    		}
    	}
        return Response.status(200).entity(response.toString()).build();
        
    	/*Object obj = null;
        try{
        	obj = ((JSONObject)response.get("voucherReport")).get("generatedReport");
        }catch(Exception e){
        	
        }
        ResponseBuilder responsew = Response.ok((Object) obj);
        responsew.header("Content-Disposition", "attachment; filename=\"COMPROBANTE\"");
        return responsew.build();*/
    }
    
    @POST
    @Path("/retention")
    @Consumes("application/json")
    @Produces("application/json")
    public Response processRetention(ComprobanteRetencion pRetention)
    {
    	JSONObject response = new JSONObject();
    	try{
    		log.info("************ENTRA AL METODO POST RETENTION");
	    	//JPAServerSession.beginTransaction();
	    	JPASession.beginTransaction();
	    	ElectronicVoucherSender sender = new ElectronicVoucherSender();
	    	response = sender.processVoucher(pRetention);
    	}catch(Exception e) {
    		log.info(e);
    		log.error(e);
    		try{
	    		response.append("estado", "Internal Error");
	    		response.append("fullMessage", e.getMessage());
	    		response.append("stacktrace", ExceptionUtils.getStackTrace(e));
    		}catch(JSONException ejson) {
    			log.info(e.getMessage());
    		}
    		
    	}
    	finally{
    		try{
    			//JPAServerSession.commitTransaction(true);
    			JPASession.commitTransaction(true);
    		}catch(Exception e) {
    			try{
    				JPASession.rollbackTransaction();
        		}catch(Exception er) {
        			try{
	        			response.append("estado", "Internal Error");
	    	    		response.append("fullMessage", e.getMessage());
	    	    		response.append("stacktrace", ExceptionUtils.getStackTrace(er));
        			}catch(JSONException jsone){
    	    			log.info(jsone.getMessage());
    	    		}
        		}
    			log.info(e.getMessage());
    		}
    	}
        return Response.status(200).entity(response.toString()).build();

    }
    
    @POST
    @Path("/billtest")
    @Consumes("application/json")
    @Produces("application/json")
    public Response processBillingtest(Factura pFactura)
    {
    	
        return Response.status(200).entity(pFactura).build();
    }
    
    @POST
    @Path("/retentiontest")
    @Consumes("application/json")
    @Produces("application/xml")
    public Response processRetentiontest(ComprobanteRetencion pRetention)
    {
    	
        return Response.status(200).entity(pRetention).build();
    }
    
    @GET
    @Path("/report/{accessKey}")
    @Produces("application/pdf")
    public Response getFileInPDFFormat(@PathParam("accessKey") String accessKey) 
    {
    	ResponseBuilder response = null;
    	try{
	    	log.info("************OBTIENE REPORTE PARA KEY: "+accessKey);
	    	properties = PropertiesHandler.getInstance("buzzsri"); 
	        //Put some validations here such as invalid file name or missing file name
	        if(accessKey == null || accessKey.isEmpty())
	        {
	            response = Response.status(Status.BAD_REQUEST);
	        }
	        //Prepare a file object with file to return
	        File file = new File(properties.getString("report.dir.out")
	        		+accessKey+".pdf");
	         
	        response = file.exists() ? Response.ok((Object) file):
	        	Response.status(Status.NOT_FOUND);
	        response.header("Content-Disposition", "attachment; filename=\"COMPROBANTE-"+accessKey +"\"");
    	}catch(Exception e){
    		log.error(e.getMessage());
    		response = Response.status(Status.INTERNAL_SERVER_ERROR);
    	}
        return response.build();
    }
    
    @POST
    @Path("/users")
    @Consumes("application/json")
    @Produces("application/json")
    public Response processUsers(Users users)
    {
    	log.info("************ENTRA AL METODO POST");
        return Response.status(200).entity(users).build();
    }
    
    
}