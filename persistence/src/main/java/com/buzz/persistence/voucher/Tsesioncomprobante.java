package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TSESIONCOMPROBANTE database table.
 * 
 */
@Entity
@NamedQuery(name="Tsesioncomprobante.findAll", query="SELECT t FROM Tsesioncomprobante t")
public class Tsesioncomprobante implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TsesioncomprobantePK id;

	@Lob
	@Column(name="codigos_error")
	private String codigosError;

	@Column(name="csesion_fk")
	private String csesionFk;

	@Lob
	@Column(name="mensaje_entrada")
	private String mensajeEntrada;

	@Lob
	@Column(name="mensaje_salida")
	private String mensajeSalida;

	@Lob
	private String resultado;

	@Lob
	@Column(name="xml_comprobante")
	private String xmlComprobante;

	//bi-directional many-to-one association to Tcomprobante
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ccontribuyente_fk", referencedColumnName="ccontribuyente_fk"),
		@JoinColumn(name="idpeticion", referencedColumnName="idpeticion")
		})
	private Tcomprobante tcomprobante;

	public Tsesioncomprobante() {
	}

	public TsesioncomprobantePK getId() {
		return this.id;
	}

	public void setId(TsesioncomprobantePK id) {
		this.id = id;
	}

	public String getCodigosError() {
		return this.codigosError;
	}

	public void setCodigosError(String codigosError) {
		this.codigosError = codigosError;
	}

	public String getCsesionFk() {
		return this.csesionFk;
	}

	public void setCsesionFk(String csesionFk) {
		this.csesionFk = csesionFk;
	}

	public String getMensajeEntrada() {
		return this.mensajeEntrada;
	}

	public void setMensajeEntrada(String mensajeEntrada) {
		this.mensajeEntrada = mensajeEntrada;
	}

	public String getMensajeSalida() {
		return this.mensajeSalida;
	}

	public void setMensajeSalida(String mensajeSalida) {
		this.mensajeSalida = mensajeSalida;
	}

	public String getResultado() {
		return this.resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getXmlComprobante() {
		return this.xmlComprobante;
	}

	public void setXmlComprobante(String xmlComprobante) {
		this.xmlComprobante = xmlComprobante;
	}

	public Tcomprobante getTcomprobante() {
		return this.tcomprobante;
	}

	public void setTcomprobante(Tcomprobante tcomprobante) {
		this.tcomprobante = tcomprobante;
	}

}