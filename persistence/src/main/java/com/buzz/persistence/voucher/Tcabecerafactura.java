package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TCABECERAFACTURA database table.
 * 
 */
@Entity
@NamedQuery(name="Tcabecerafactura.findAll", query="SELECT t FROM Tcabecerafactura t")
public class Tcabecerafactura implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ccfactura;

	@Column(name="contribuyente_especial")
	private String contribuyenteEspecial;

	@Column(name="direccion_establecimiento")
	private String direccionEstablecimiento;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_emision")
	private Date fechaEmision;

	@Column(name="guia_remision")
	private String guiaRemision;

	@Column(name="identificacion_comprador")
	private String identificacionComprador;

	@Column(name="importe_total")
	private float importeTotal;

	private String moneda;

	@Column(name="obligado_contabilidad")
	private String obligadoContabilidad;

	private float propina;

	@Column(name="razon_social_comprador")
	private String razonSocialComprador;

	@Column(name="tipo_id_comprador")
	private String tipoIdComprador;

	@Column(name="total_descuentos")
	private float totalDescuentos;

	@Column(name="total_sin_impuestos")
	private float totalSinImpuestos;

	//bi-directional many-to-one association to Tcomprobante
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ccontribuyente_fk2", referencedColumnName="ccontribuyente_fk"),
		@JoinColumn(name="idpeticion_fk", referencedColumnName="idpeticion")
		})
	private Tcomprobante tcomprobante;

	//bi-directional many-to-one association to Tcabecerafacturaimpuesto
	@OneToMany(mappedBy="tcabecerafactura")
	private List<Tcabecerafacturaimpuesto> tcabecerafacturaimpuestos;

	//bi-directional many-to-one association to Tdetallefactura
	@OneToMany(mappedBy="tcabecerafactura")
	private List<Tdetallefactura> tdetallefacturas;

	//bi-directional many-to-one association to Tretencionfactura
	@OneToMany(mappedBy="tcabecerafactura")
	private List<Tretencionfactura> tretencionfacturas;

	public Tcabecerafactura() {
	}

	public int getCcfactura() {
		return this.ccfactura;
	}

	public void setCcfactura(int ccfactura) {
		this.ccfactura = ccfactura;
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

	public String getGuiaRemision() {
		return this.guiaRemision;
	}

	public void setGuiaRemision(String guiaRemision) {
		this.guiaRemision = guiaRemision;
	}

	public String getIdentificacionComprador() {
		return this.identificacionComprador;
	}

	public void setIdentificacionComprador(String identificacionComprador) {
		this.identificacionComprador = identificacionComprador;
	}

	public float getImporteTotal() {
		return this.importeTotal;
	}

	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getMoneda() {
		return this.moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getObligadoContabilidad() {
		return this.obligadoContabilidad;
	}

	public void setObligadoContabilidad(String obligadoContabilidad) {
		this.obligadoContabilidad = obligadoContabilidad;
	}

	public float getPropina() {
		return this.propina;
	}

	public void setPropina(float propina) {
		this.propina = propina;
	}

	public String getRazonSocialComprador() {
		return this.razonSocialComprador;
	}

	public void setRazonSocialComprador(String razonSocialComprador) {
		this.razonSocialComprador = razonSocialComprador;
	}

	public String getTipoIdComprador() {
		return this.tipoIdComprador;
	}

	public void setTipoIdComprador(String tipoIdComprador) {
		this.tipoIdComprador = tipoIdComprador;
	}

	public float getTotalDescuentos() {
		return this.totalDescuentos;
	}

	public void setTotalDescuentos(float totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	public float getTotalSinImpuestos() {
		return this.totalSinImpuestos;
	}

	public void setTotalSinImpuestos(float totalSinImpuestos) {
		this.totalSinImpuestos = totalSinImpuestos;
	}

	public Tcomprobante getTcomprobante() {
		return this.tcomprobante;
	}

	public void setTcomprobante(Tcomprobante tcomprobante) {
		this.tcomprobante = tcomprobante;
	}

	public List<Tcabecerafacturaimpuesto> getTcabecerafacturaimpuestos() {
		return this.tcabecerafacturaimpuestos;
	}

	public void setTcabecerafacturaimpuestos(List<Tcabecerafacturaimpuesto> tcabecerafacturaimpuestos) {
		this.tcabecerafacturaimpuestos = tcabecerafacturaimpuestos;
	}

	public Tcabecerafacturaimpuesto addTcabecerafacturaimpuesto(Tcabecerafacturaimpuesto tcabecerafacturaimpuesto) {
		getTcabecerafacturaimpuestos().add(tcabecerafacturaimpuesto);
		tcabecerafacturaimpuesto.setTcabecerafactura(this);

		return tcabecerafacturaimpuesto;
	}

	public Tcabecerafacturaimpuesto removeTcabecerafacturaimpuesto(Tcabecerafacturaimpuesto tcabecerafacturaimpuesto) {
		getTcabecerafacturaimpuestos().remove(tcabecerafacturaimpuesto);
		tcabecerafacturaimpuesto.setTcabecerafactura(null);

		return tcabecerafacturaimpuesto;
	}

	public List<Tdetallefactura> getTdetallefacturas() {
		return this.tdetallefacturas;
	}

	public void setTdetallefacturas(List<Tdetallefactura> tdetallefacturas) {
		this.tdetallefacturas = tdetallefacturas;
	}

	public Tdetallefactura addTdetallefactura(Tdetallefactura tdetallefactura) {
		getTdetallefacturas().add(tdetallefactura);
		tdetallefactura.setTcabecerafactura(this);

		return tdetallefactura;
	}

	public Tdetallefactura removeTdetallefactura(Tdetallefactura tdetallefactura) {
		getTdetallefacturas().remove(tdetallefactura);
		tdetallefactura.setTcabecerafactura(null);

		return tdetallefactura;
	}

	public List<Tretencionfactura> getTretencionfacturas() {
		return this.tretencionfacturas;
	}

	public void setTretencionfacturas(List<Tretencionfactura> tretencionfacturas) {
		this.tretencionfacturas = tretencionfacturas;
	}

	public Tretencionfactura addTretencionfactura(Tretencionfactura tretencionfactura) {
		getTretencionfacturas().add(tretencionfactura);
		tretencionfactura.setTcabecerafactura(this);

		return tretencionfactura;
	}

	public Tretencionfactura removeTretencionfactura(Tretencionfactura tretencionfactura) {
		getTretencionfacturas().remove(tretencionfactura);
		tretencionfactura.setTcabecerafactura(null);

		return tretencionfactura;
	}

}