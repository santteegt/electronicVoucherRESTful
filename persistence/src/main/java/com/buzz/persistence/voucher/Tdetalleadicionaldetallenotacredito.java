package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TDETALLEADICIONALDETALLENOTACREDITO database table.
 * 
 */
@Entity
@NamedQuery(name="Tdetalleadicionaldetallenotacredito.findAll", query="SELECT t FROM Tdetalleadicionaldetallenotacredito t")
public class Tdetalleadicionaldetallenotacredito implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cdancredito;

	private String nombre;

	private String valor;

	//bi-directional many-to-one association to Tdetallenotacredito
	@ManyToOne
	@JoinColumn(name="cdncredito_fk")
	private Tdetallenotacredito tdetallenotacredito;

	public Tdetalleadicionaldetallenotacredito() {
	}

	public int getCdancredito() {
		return this.cdancredito;
	}

	public void setCdancredito(int cdancredito) {
		this.cdancredito = cdancredito;
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

	public Tdetallenotacredito getTdetallenotacredito() {
		return this.tdetallenotacredito;
	}

	public void setTdetallenotacredito(Tdetallenotacredito tdetallenotacredito) {
		this.tdetallenotacredito = tdetallenotacredito;
	}

}