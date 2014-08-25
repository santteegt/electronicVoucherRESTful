package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TCABECERANOTACREDITODEBITO database table.
 * 
 */
@Entity
@NamedQuery(name="Tcabeceranotacreditodebito.findAll", query="SELECT t FROM Tcabeceranotacreditodebito t")
public class Tcabeceranotacreditodebito implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ccnotacd;

	@Column(name="codigo_doc_modificado")
	private String codigoDocModificado;

	@Column(name="contribuyente_especial")
	private String contribuyenteEspecial;

	@Column(name="direccion_establecimiento")
	private String direccionEstablecimiento;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_emision")
	private Date fechaEmision;

	@Temporal(TemporalType.DATE)
	@Column(name="femision_doc_sustento")
	private Date femisionDocSustento;

	@Column(name="identificacion_comprador")
	private String identificacionComprador;

	private String moneda;

	private String motivo;

	@Column(name="numero_doc_modificado")
	private String numeroDocModificado;

	@Column(name="obligado_contabilidad")
	private String obligadoContabilidad;

	@Column(name="razon_social_comprador")
	private String razonSocialComprador;

	private String rise;

	@Column(name="tipo_id_comprador")
	private String tipoIdComprador;

	@Column(name="total_sin_impuestos")
	private float totalSinImpuestos;

	@Column(name="valor_modificacion")
	private float valorModificacion;

	@Column(name="valor_total")
	private float valorTotal;

	//bi-directional many-to-one association to Tcomprobante
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ccontribuyente_fk2", referencedColumnName="ccontribuyente_fk"),
		@JoinColumn(name="idpeticion_fk", referencedColumnName="idpeticion")
		})
	private Tcomprobante tcomprobante;

	//bi-directional many-to-one association to Tcabeceranotacreditodebitoimpuesto
	@OneToMany(mappedBy="tcabeceranotacreditodebito")
	private List<Tcabeceranotacreditodebitoimpuesto> tcabeceranotacreditodebitoimpuestos;

	//bi-directional many-to-one association to Tdetallenotacredito
	@OneToMany(mappedBy="tcabeceranotacreditodebito")
	private List<Tdetallenotacredito> tdetallenotacreditos;

	//bi-directional many-to-one association to Tdetallenotadebito
	@OneToMany(mappedBy="tcabeceranotacreditodebito")
	private List<Tdetallenotadebito> tdetallenotadebitos;

	public Tcabeceranotacreditodebito() {
	}

	public int getCcnotacd() {
		return this.ccnotacd;
	}

	public void setCcnotacd(int ccnotacd) {
		this.ccnotacd = ccnotacd;
	}

	public String getCodigoDocModificado() {
		return this.codigoDocModificado;
	}

	public void setCodigoDocModificado(String codigoDocModificado) {
		this.codigoDocModificado = codigoDocModificado;
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

	public Date getFemisionDocSustento() {
		return this.femisionDocSustento;
	}

	public void setFemisionDocSustento(Date femisionDocSustento) {
		this.femisionDocSustento = femisionDocSustento;
	}

	public String getIdentificacionComprador() {
		return this.identificacionComprador;
	}

	public void setIdentificacionComprador(String identificacionComprador) {
		this.identificacionComprador = identificacionComprador;
	}

	public String getMoneda() {
		return this.moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getNumeroDocModificado() {
		return this.numeroDocModificado;
	}

	public void setNumeroDocModificado(String numeroDocModificado) {
		this.numeroDocModificado = numeroDocModificado;
	}

	public String getObligadoContabilidad() {
		return this.obligadoContabilidad;
	}

	public void setObligadoContabilidad(String obligadoContabilidad) {
		this.obligadoContabilidad = obligadoContabilidad;
	}

	public String getRazonSocialComprador() {
		return this.razonSocialComprador;
	}

	public void setRazonSocialComprador(String razonSocialComprador) {
		this.razonSocialComprador = razonSocialComprador;
	}

	public String getRise() {
		return this.rise;
	}

	public void setRise(String rise) {
		this.rise = rise;
	}

	public String getTipoIdComprador() {
		return this.tipoIdComprador;
	}

	public void setTipoIdComprador(String tipoIdComprador) {
		this.tipoIdComprador = tipoIdComprador;
	}

	public float getTotalSinImpuestos() {
		return this.totalSinImpuestos;
	}

	public void setTotalSinImpuestos(float totalSinImpuestos) {
		this.totalSinImpuestos = totalSinImpuestos;
	}

	public float getValorModificacion() {
		return this.valorModificacion;
	}

	public void setValorModificacion(float valorModificacion) {
		this.valorModificacion = valorModificacion;
	}

	public float getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Tcomprobante getTcomprobante() {
		return this.tcomprobante;
	}

	public void setTcomprobante(Tcomprobante tcomprobante) {
		this.tcomprobante = tcomprobante;
	}

	public List<Tcabeceranotacreditodebitoimpuesto> getTcabeceranotacreditodebitoimpuestos() {
		return this.tcabeceranotacreditodebitoimpuestos;
	}

	public void setTcabeceranotacreditodebitoimpuestos(List<Tcabeceranotacreditodebitoimpuesto> tcabeceranotacreditodebitoimpuestos) {
		this.tcabeceranotacreditodebitoimpuestos = tcabeceranotacreditodebitoimpuestos;
	}

	public Tcabeceranotacreditodebitoimpuesto addTcabeceranotacreditodebitoimpuesto(Tcabeceranotacreditodebitoimpuesto tcabeceranotacreditodebitoimpuesto) {
		getTcabeceranotacreditodebitoimpuestos().add(tcabeceranotacreditodebitoimpuesto);
		tcabeceranotacreditodebitoimpuesto.setTcabeceranotacreditodebito(this);

		return tcabeceranotacreditodebitoimpuesto;
	}

	public Tcabeceranotacreditodebitoimpuesto removeTcabeceranotacreditodebitoimpuesto(Tcabeceranotacreditodebitoimpuesto tcabeceranotacreditodebitoimpuesto) {
		getTcabeceranotacreditodebitoimpuestos().remove(tcabeceranotacreditodebitoimpuesto);
		tcabeceranotacreditodebitoimpuesto.setTcabeceranotacreditodebito(null);

		return tcabeceranotacreditodebitoimpuesto;
	}

	public List<Tdetallenotacredito> getTdetallenotacreditos() {
		return this.tdetallenotacreditos;
	}

	public void setTdetallenotacreditos(List<Tdetallenotacredito> tdetallenotacreditos) {
		this.tdetallenotacreditos = tdetallenotacreditos;
	}

	public Tdetallenotacredito addTdetallenotacredito(Tdetallenotacredito tdetallenotacredito) {
		getTdetallenotacreditos().add(tdetallenotacredito);
		tdetallenotacredito.setTcabeceranotacreditodebito(this);

		return tdetallenotacredito;
	}

	public Tdetallenotacredito removeTdetallenotacredito(Tdetallenotacredito tdetallenotacredito) {
		getTdetallenotacreditos().remove(tdetallenotacredito);
		tdetallenotacredito.setTcabeceranotacreditodebito(null);

		return tdetallenotacredito;
	}

	public List<Tdetallenotadebito> getTdetallenotadebitos() {
		return this.tdetallenotadebitos;
	}

	public void setTdetallenotadebitos(List<Tdetallenotadebito> tdetallenotadebitos) {
		this.tdetallenotadebitos = tdetallenotadebitos;
	}

	public Tdetallenotadebito addTdetallenotadebito(Tdetallenotadebito tdetallenotadebito) {
		getTdetallenotadebitos().add(tdetallenotadebito);
		tdetallenotadebito.setTcabeceranotacreditodebito(this);

		return tdetallenotadebito;
	}

	public Tdetallenotadebito removeTdetallenotadebito(Tdetallenotadebito tdetallenotadebito) {
		getTdetallenotadebitos().remove(tdetallenotadebito);
		tdetallenotadebito.setTcabeceranotacreditodebito(null);

		return tdetallenotadebito;
	}

}