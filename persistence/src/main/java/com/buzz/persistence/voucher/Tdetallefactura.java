package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TDETALLEFACTURA database table.
 * 
 */
@Entity
@NamedQuery(name="Tdetallefactura.findAll", query="SELECT t FROM Tdetallefactura t")
public class Tdetallefactura implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cdfactura;

	private float cantidad;

	@Column(name="codigo_auxiliar")
	private String codigoAuxiliar;

	@Column(name="codigo_principal")
	private String codigoPrincipal;

	private String descripcion;

	private float descuento;

	@Column(name="precio_total_sin_impuesto")
	private float precioTotalSinImpuesto;

	@Column(name="precio_unitario")
	private float precioUnitario;

	//bi-directional many-to-one association to Tdetalleadicionaldetallefactura
	@OneToMany(mappedBy="tdetallefactura")
	private List<Tdetalleadicionaldetallefactura> tdetalleadicionaldetallefacturas;

	//bi-directional many-to-one association to Tcabecerafactura
	@ManyToOne
	@JoinColumn(name="ccfactura_fk")
	private Tcabecerafactura tcabecerafactura;

	//bi-directional many-to-one association to Timpuestodetallefactura
	@OneToMany(mappedBy="tdetallefactura")
	private List<Timpuestodetallefactura> timpuestodetallefacturas;

	public Tdetallefactura() {
	}

	public int getCdfactura() {
		return this.cdfactura;
	}

	public void setCdfactura(int cdfactura) {
		this.cdfactura = cdfactura;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigoAuxiliar() {
		return this.codigoAuxiliar;
	}

	public void setCodigoAuxiliar(String codigoAuxiliar) {
		this.codigoAuxiliar = codigoAuxiliar;
	}

	public String getCodigoPrincipal() {
		return this.codigoPrincipal;
	}

	public void setCodigoPrincipal(String codigoPrincipal) {
		this.codigoPrincipal = codigoPrincipal;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getDescuento() {
		return this.descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public float getPrecioTotalSinImpuesto() {
		return this.precioTotalSinImpuesto;
	}

	public void setPrecioTotalSinImpuesto(float precioTotalSinImpuesto) {
		this.precioTotalSinImpuesto = precioTotalSinImpuesto;
	}

	public float getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public List<Tdetalleadicionaldetallefactura> getTdetalleadicionaldetallefacturas() {
		return this.tdetalleadicionaldetallefacturas;
	}

	public void setTdetalleadicionaldetallefacturas(List<Tdetalleadicionaldetallefactura> tdetalleadicionaldetallefacturas) {
		this.tdetalleadicionaldetallefacturas = tdetalleadicionaldetallefacturas;
	}

	public Tdetalleadicionaldetallefactura addTdetalleadicionaldetallefactura(Tdetalleadicionaldetallefactura tdetalleadicionaldetallefactura) {
		getTdetalleadicionaldetallefacturas().add(tdetalleadicionaldetallefactura);
		tdetalleadicionaldetallefactura.setTdetallefactura(this);

		return tdetalleadicionaldetallefactura;
	}

	public Tdetalleadicionaldetallefactura removeTdetalleadicionaldetallefactura(Tdetalleadicionaldetallefactura tdetalleadicionaldetallefactura) {
		getTdetalleadicionaldetallefacturas().remove(tdetalleadicionaldetallefactura);
		tdetalleadicionaldetallefactura.setTdetallefactura(null);

		return tdetalleadicionaldetallefactura;
	}

	public Tcabecerafactura getTcabecerafactura() {
		return this.tcabecerafactura;
	}

	public void setTcabecerafactura(Tcabecerafactura tcabecerafactura) {
		this.tcabecerafactura = tcabecerafactura;
	}

	public List<Timpuestodetallefactura> getTimpuestodetallefacturas() {
		return this.timpuestodetallefacturas;
	}

	public void setTimpuestodetallefacturas(List<Timpuestodetallefactura> timpuestodetallefacturas) {
		this.timpuestodetallefacturas = timpuestodetallefacturas;
	}

	public Timpuestodetallefactura addTimpuestodetallefactura(Timpuestodetallefactura timpuestodetallefactura) {
		getTimpuestodetallefacturas().add(timpuestodetallefactura);
		timpuestodetallefactura.setTdetallefactura(this);

		return timpuestodetallefactura;
	}

	public Timpuestodetallefactura removeTimpuestodetallefactura(Timpuestodetallefactura timpuestodetallefactura) {
		getTimpuestodetallefacturas().remove(timpuestodetallefactura);
		timpuestodetallefactura.setTdetallefactura(null);

		return timpuestodetallefactura;
	}

}