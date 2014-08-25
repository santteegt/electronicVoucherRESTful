package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIMPUESTODETALLENOTACREDITO database table.
 * 
 */
@Entity
@NamedQuery(name="Timpuestodetallenotacredito.findAll", query="SELECT t FROM Timpuestodetallenotacredito t")
public class Timpuestodetallenotacredito implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cidncredito;

	@Column(name="base_imponible")
	private float baseImponible;

	private String codigo;

	@Column(name="codigo_porcentaje")
	private String codigoPorcentaje;

	private float tarifa;

	private float valor;

	//bi-directional many-to-one association to Tdetallenotacredito
	@ManyToOne
	@JoinColumn(name="cdncredito_fk")
	private Tdetallenotacredito tdetallenotacredito;

	public Timpuestodetallenotacredito() {
	}

	public int getCidncredito() {
		return this.cidncredito;
	}

	public void setCidncredito(int cidncredito) {
		this.cidncredito = cidncredito;
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

	public Tdetallenotacredito getTdetallenotacredito() {
		return this.tdetallenotacredito;
	}

	public void setTdetallenotacredito(Tdetallenotacredito tdetallenotacredito) {
		this.tdetallenotacredito = tdetallenotacredito;
	}

}