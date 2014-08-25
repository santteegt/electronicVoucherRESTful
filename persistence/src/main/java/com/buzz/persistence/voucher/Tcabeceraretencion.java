package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TCABECERARETENCION database table.
 * 
 */
@Entity
@NamedQuery(name="Tcabeceraretencion.findAll", query="SELECT t FROM Tcabeceraretencion t")
public class Tcabeceraretencion implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ccretencion;

	@Column(name="contribuyente_especial")
	private String contribuyenteEspecial;

	@Column(name="direccion_establecimiento")
	private String direccionEstablecimiento;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_emision")
	private Date fechaEmision;

	@Column(name="identificacion_sujetoretenido")
	private String identificacionSujetoretenido;

	@Column(name="obligado_contabilidad")
	private String obligadoContabilidad;

	@Column(name="periodo_fiscal")
	private String periodoFiscal;

	@Column(name="razon_social_sujetoretenido")
	private String razonSocialSujetoretenido;

	@Column(name="tipo_id_sujetoretenido")
	private String tipoIdSujetoretenido;

	//bi-directional many-to-one association to Tcomprobante
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ccontribuyente_fk2", referencedColumnName="ccontribuyente_fk"),
		@JoinColumn(name="idpeticion_fk", referencedColumnName="idpeticion")
		})
	private Tcomprobante tcomprobante;

	//bi-directional many-to-one association to Tdetalleretencion
	@OneToMany(mappedBy="tcabeceraretencion")
	private List<Tdetalleretencion> tdetalleretencions;

	public Tcabeceraretencion() {
	}

	public int getCcretencion() {
		return this.ccretencion;
	}

	public void setCcretencion(int ccretencion) {
		this.ccretencion = ccretencion;
	}

	public String getContribuyenteEspecial() {
		return this.contribuyenteEspecial;
	}

	public void setContribuyenteEspecial(String contribuyenteEspecial) {
		this.contribuyenteEspecial = contribuyenteEspecial;
	}

	public String getDireccionEstablecimiento() {
		return this.direccionEstablecimiento;
	}

	public void setDireccionEstablecimiento(String direccionEstablecimiento) {
		this.direccionEstablecimiento = direccionEstablecimiento;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getIdentificacionSujetoretenido() {
		return this.identificacionSujetoretenido;
	}

	public void setIdentificacionSujetoretenido(String identificacionSujetoretenido) {
		this.identificacionSujetoretenido = identificacionSujetoretenido;
	}

	public String getObligadoContabilidad() {
		return this.obligadoContabilidad;
	}

	public void setObligadoContabilidad(String obligadoContabilidad) {
		this.obligadoContabilidad = obligadoContabilidad;
	}

	public String getPeriodoFiscal() {
		return this.periodoFiscal;
	}

	public void setPeriodoFiscal(String periodoFiscal) {
		this.periodoFiscal = periodoFiscal;
	}

	public String getRazonSocialSujetoretenido() {
		return this.razonSocialSujetoretenido;
	}

	public void setRazonSocialSujetoretenido(String razonSocialSujetoretenido) {
		this.razonSocialSujetoretenido = razonSocialSujetoretenido;
	}

	public String getTipoIdSujetoretenido() {
		return this.tipoIdSujetoretenido;
	}

	public void setTipoIdSujetoretenido(String tipoIdSujetoretenido) {
		this.tipoIdSujetoretenido = tipoIdSujetoretenido;
	}

	public Tcomprobante getTcomprobante() {
		return this.tcomprobante;
	}

	public void setTcomprobante(Tcomprobante tcomprobante) {
		this.tcomprobante = tcomprobante;
	}

	public List<Tdetalleretencion> getTdetalleretencions() {
		return this.tdetalleretencions;
	}

	public void setTdetalleretencions(List<Tdetalleretencion> tdetalleretencions) {
		this.tdetalleretencions = tdetalleretencions;
	}

	public Tdetalleretencion addTdetalleretencion(Tdetalleretencion tdetalleretencion) {
		getTdetalleretencions().add(tdetalleretencion);
		tdetalleretencion.setTcabeceraretencion(this);

		return tdetalleretencion;
	}

	public Tdetalleretencion removeTdetalleretencion(Tdetalleretencion tdetalleretencion) {
		getTdetalleretencions().remove(tdetalleretencion);
		tdetalleretencion.setTcabeceraretencion(null);

		return tdetalleretencion;
	}

}