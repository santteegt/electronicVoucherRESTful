package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TCABECERANOTACREDITODEBITOIMPUESTO database table.
 * 
 */
@Entity
@NamedQuery(name="Tcabeceranotacreditodebitoimpuesto.findAll", query="SELECT t FROM Tcabeceranotacreditodebitoimpuesto t")
public class Tcabeceranotacreditodebitoimpuesto implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ccncdimpuesto;

	@Column(name="base_imponible")
	private float baseImponible;

	private String codigo;

	@Column(name="codigo_porcentaje")
	private String codigoPorcentaje;

	private float tarifa;

	private float valor;

	//bi-directional many-to-one association to Tcabeceranotacreditodebito
	@ManyToOne
	@JoinColumn(name="ccnotacd_fk")
	private Tcabeceranotacreditodebito tcabeceranotacreditodebito;

	public Tcabeceranotacreditodebitoimpuesto() {
	}

	public int getCcncdimpuesto() {
		return this.ccncdimpuesto;
	}

	public void setCcncdimpuesto(int ccncdimpuesto) {
		this.ccncdimpuesto = ccncdimpuesto;
	}

	public float getBaseImponible() {
		return this.baseImponible;
	}

	public void setBaseImponible(float baseImponible) {
		this.baseImponible = baseImponible;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoPorcentaje() {
		return this.codigoPorcentaje;
	}

	public void setCodigoPorcentaje(String codigoPorcentaje) {
		this.codigoPorcentaje = codigoPorcentaje;
	}

	public float getTarifa() {
		return this.tarifa;
	}

	public void setTarifa(float tarifa) {
		this.tarifa = tarifa;
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