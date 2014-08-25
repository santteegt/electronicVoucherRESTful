package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TDETALLEADICIONALDETALLEFACTURA database table.
 * 
 */
@Entity
@NamedQuery(name="Tdetalleadicionaldetallefactura.findAll", query="SELECT t FROM Tdetalleadicionaldetallefactura t")
public class Tdetalleadicionaldetallefactura implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cdafactura;

	private String nombre;

	private String valor;

	//bi-directional many-to-one association to Tdetallefactura
	@ManyToOne
	@JoinColumn(name="cdfactura_fk")
	private Tdetallefactura tdetallefactura;

	public Tdetalleadicionaldetallefactura() {
	}

	public int getCdafactura() {
		return this.cdafactura;
	}

	public void setCdafactura(int cdafactura) {
		this.cdafactura = cdafactura;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Tdetallefactura getTdetallefactura() {
		return this.tdetallefactura;
	}

	public void setTdetallefactura(Tdetallefactura tdetallefactura) {
		this.tdetallefactura = tdetallefactura;
	}

}