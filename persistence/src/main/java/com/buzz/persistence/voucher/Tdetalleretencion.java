package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TDETALLERETENCION database table.
 * 
 */
@Entity
@NamedQuery(name="Tdetalleretencion.findAll", query="SELECT t FROM Tdetalleretencion t")
public class Tdetalleretencion implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cdretencion;

	@Column(name="base_imponible")
	private float baseImponible;

	@Column(name="cod_doc_sustento")
	private String codDocSustento;

	@Column(name="codigo_retencion")
	private String codigoRetencion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_emision_doc_sustento")
	private Date fechaEmisionDocSustento;

	@Column(name="num_doc_sustento")
	private String numDocSustento;

	@Column(name="porcentaje_retener")
	private float porcentajeRetener;

	@Column(name="valor_retenido")
	private float valorRetenido;

	//bi-directional many-to-one association to Tcabeceraretencion
	@ManyToOne
	@JoinColumn(name="ccretencion_fk")
	private Tcabeceraretencion tcabeceraretencion;

	public Tdetalleretencion() {
	}

	public int getCdretencion() {
		return this.cdretencion;
	}

	public void setCdretencion(int cdretencion) {
		this.cdretencion = cdretencion;
	}

	public float getBaseImponible() {
		return this.baseImponible;
	}

	public void setBaseImponible(float baseImponible) {
		this.baseImponible = baseImponible;
	}

	public String getCodDocSustento() {
		return this.codDocSustento;
	}

	public void setCodDocSustento(String codDocSustento) {
		this.codDocSustento = codDocSustento;
	}

	public String getCodigoRetencion() {
		return this.codigoRetencion;
	}

	public void setCodigoRetencion(String codigoRetencion) {
		this.codigoRetencion = codigoRetencion;
	}

	public Date getFechaEmisionDocSustento() {
		return this.fechaEmisionDocSustento;
	}

	public void setFechaEmisionDocSustento(Date fechaEmisionDocSustento) {
		this.fechaEmisionDocSustento = fechaEmisionDocSustento;
	}

	public String getNumDocSustento() {
		return this.numDocSustento;
	}

	public void setNumDocSustento(String numDocSustento) {
		this.numDocSustento = numDocSustento;
	}

	public float getPorcentajeRetener() {
		return this.porcentajeRetener;
	}

	public void setPorcentajeRetener(float porcentajeRetener) {
		this.porcentajeRetener = porcentajeRetener;
	}

	public float getValorRetenido() {
		return this.valorRetenido;
	}

	public void setValorRetenido(float valorRetenido) {
		this.valorRetenido = valorRetenido;
	}

	public Tcabeceraretencion getTcabeceraretencion() {
		return this.tcabeceraretencion;
	}

	public void setTcabeceraretencion(Tcabeceraretencion tcabeceraretencion) {
		this.tcabeceraretencion = tcabeceraretencion;
	}

}