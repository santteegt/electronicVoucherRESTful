package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TDETALLENOTADEBITO database table.
 * 
 */
@Entity
@NamedQuery(name="Tdetallenotadebito.findAll", query="SELECT t FROM Tdetallenotadebito t")
public class Tdetallenotadebito implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cdndebito;

	private String razon;

	private float valor;

	//bi-directional many-to-one association to Tcabeceranotacreditodebito
	@ManyToOne
	@JoinColumn(name="ccnotacd_fk")
	private Tcabeceranotacreditodebito tcabeceranotacreditodebito;

	public Tdetallenotadebito() {
	}

	public int getCdndebito() {
		return this.cdndebito;
	}

	public void setCdndebito(int cdndebito) {
		this.cdndebito = cdndebito;
	}

	public String getRazon() {
		return this.razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	public float getValor() {
		return this.valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Tcabeceranotacreditodebito getTcabeceranotacreditodebito() {
		return this.tcabeceranotacreditodebito;
	}

	public void setTcabeceranotacreditodebito(Tcabeceranotacreditodebito tcabeceranotacreditodebito) {
		this.tcabeceranotacreditodebito = tcabeceranotacreditodebito;
	}

}