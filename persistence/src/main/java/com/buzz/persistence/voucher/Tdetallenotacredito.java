package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TDETALLENOTACREDITO database table.
 * 
 */
@Entity
@NamedQuery(name="Tdetallenotacredito.findAll", query="SELECT t FROM Tdetallenotacredito t")
public class Tdetallenotacredito implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cdncredito;

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

	//bi-directional many-to-one association to Tdetalleadicionaldetallenotacredito
	@OneToMany(mappedBy="tdetallenotacredito")
	private List<Tdetalleadicionaldetallenotacredito> tdetalleadicionaldetallenotacreditos;

	//bi-directional many-to-one association to Tcabeceranotacreditodebito
	@ManyToOne
	@JoinColumn(name="ccnotacd_fk")
	private Tcabeceranotacreditodebito tcabeceranotacreditodebito;

	//bi-directional many-to-one association to Timpuestodetallenotacredito
	@OneToMany(mappedBy="tdetallenotacredito")
	private List<Timpuestodetallenotacredito> timpuestodetallenotacreditos;

	public Tdetallenotacredito() {
	}

	public int getCdncredito() {
		return this.cdncredito;
	}

	public void setCdncredito(int cdncredito) {
		this.cdncredito = cdncredito;
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

	public List<Tdetalleadicionaldetallenotacredito> getTdetalleadicionaldetallenotacreditos() {
		return this.tdetalleadicionaldetallenotacreditos;
	}

	public void setTdetalleadicionaldetallenotacreditos(List<Tdetalleadicionaldetallenotacredito> tdetalleadicionaldetallenotacreditos) {
		this.tdetalleadicionaldetallenotacreditos = tdetalleadicionaldetallenotacreditos;
	}

	public Tdetalleadicionaldetallenotacredito addTdetalleadicionaldetallenotacredito(Tdetalleadicionaldetallenotacredito tdetalleadicionaldetallenotacredito) {
		getTdetalleadicionaldetallenotacreditos().add(tdetalleadicionaldetallenotacredito);
		tdetalleadicionaldetallenotacredito.setTdetallenotacredito(this);

		return tdetalleadicionaldetallenotacredito;
	}

	public Tdetalleadicionaldetallenotacredito removeTdetalleadicionaldetallenotacredito(Tdetalleadicionaldetallenotacredito tdetalleadicionaldetallenotacredito) {
		getTdetalleadicionaldetallenotacreditos().remove(tdetalleadicionaldetallenotacredito);
		tdetalleadicionaldetallenotacredito.setTdetallenotacredito(null);

		return tdetalleadicionaldetallenotacredito;
	}

	public Tcabeceranotacreditodebito getTcabeceranotacreditodebito() {
		return this.tcabeceranotacreditodebito;
	}

	public void setTcabeceranotacreditodebito(Tcabeceranotacreditodebito tcabeceranotacreditodebito) {
		this.tcabeceranotacreditodebito = tcabeceranotacreditodebito;
	}

	public List<Timpuestodetallenotacredito> getTimpuestodetallenotacreditos() {
		return this.timpuestodetallenotacreditos;
	}

	public void setTimpuestodetallenotacreditos(List<Timpuestodetallenotacredito> timpuestodetallenotacreditos) {
		this.timpuestodetallenotacreditos = timpuestodetallenotacreditos;
	}

	public Timpuestodetallenotacredito addTimpuestodetallenotacredito(Timpuestodetallenotacredito timpuestodetallenotacredito) {
		getTimpuestodetallenotacreditos().add(timpuestodetallenotacredito);
		timpuestodetallenotacredito.setTdetallenotacredito(this);

		return timpuestodetallenotacredito;
	}

	public Timpuestodetallenotacredito removeTimpuestodetallenotacredito(Timpuestodetallenotacredito timpuestodetallenotacredito) {
		getTimpuestodetallenotacreditos().remove(timpuestodetallenotacredito);
		timpuestodetallenotacredito.setTdetallenotacredito(null);

		return timpuestodetallenotacredito;
	}

}