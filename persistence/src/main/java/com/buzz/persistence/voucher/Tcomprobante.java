package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TCOMPROBANTE database table.
 * 
 */
@Entity
@NamedQuery(name="Tcomprobante.findAll", query="SELECT t FROM Tcomprobante t")
public class Tcomprobante implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TcomprobantePK id;

	@Column(name="ambiente")
	private String ambiente;
	
	@Column(name="tipo_emision")
	private String tipoEmision;

	@Column(name="razon_social")
	private String razonSocial;

	@Column(name="nombre_comercial")
	private String nombreComercial;
	
	@Column(name="ruc")
	private String ruc;
	
	@Column(name="clave_acceso")
	private String claveAcceso;
	
	@Column(name="codigo_documento")
	private String codigoDocumento;

	
	@Column(name="establecimiento")
	private String establecimiento;

	
	@Column(name="punto_emision")
	private String puntoEmision;
	
	@Column(name="secuencial")
	private String secuencial;

	
	@Column(name="direccion_matriz")
	private String direccionMatriz;


	public Tcomprobante() {
	}


	public TcomprobantePK getId() {
		return id;
	}


	public void setId(TcomprobantePK id) {
		this.id = id;
	}


	public String getAmbiente() {
		return ambiente;
	}


	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}


	public String getTipoEmision() {
		return tipoEmision;
	}


	public void setTipoEmision(String tipoEmision) {
		this.tipoEmision = tipoEmision;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


	public String getNombreComercial() {
		return nombreComercial;
	}


	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}


	public String getRuc() {
		return ruc;
	}


	public void setRuc(String ruc) {
		this.ruc = ruc;
	}


	public String getClaveAcceso() {
		return claveAcceso;
	}


	public void setClaveAcceso(String claveAcceso) {
		this.claveAcceso = claveAcceso;
	}


	public String getCodigoDocumento() {
		return codigoDocumento;
	}


	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}


	public String getEstablecimiento() {
		return establecimiento;
	}


	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}


	public String getPuntoEmision() {
		return puntoEmision;
	}


	public void setPuntoEmision(String puntoEmision) {
		this.puntoEmision = puntoEmision;
	}


	public String getSecuencial() {
		return secuencial;
	}


	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}


	public String getDireccionMatriz() {
		return direccionMatriz;
	}


	public void setDireccionMatriz(String direccionMatriz) {
		this.direccionMatriz = direccionMatriz;
	}

	

}