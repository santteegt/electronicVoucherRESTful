package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIMPUESTODETALLEFACTURA database table.
 * 
 */
@Entity
@NamedQuery(name="Timpuestodetallefactura.findAll", query="SELECT t FROM Timpuestodetallefactura t")
public class Timpuestodetallefactura implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cidfactura;

	@Column(name="base_imponible")
	private float baseImponible;

	private String codigo;

	@Column(name="codigo_porcentaje")
	private String codigoPorcentaje;

	private float tarifa;

	private float valor;

	//bi-directional many-to-one association to Tdetallefactura
	@ManyToOne
	@JoinColumn(name="cdfactura_fk")
	private Tdetallefactura tdetallefactura;

	public Timpuestodetallefactura() {
	}

	public int getCidfactura() {
		return this.cidfactura;
	}

	public void setCidfactura(int cidfactura) {
		this.cidfactura = cidfactura;
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

	public Tdetallefactura getTdetallefactura() {
		return this.tdetallefactura;
	}

	public void setTdetallefactura(Tdetallefactura tdetallefactura) {
		this.tdetallefactura = tdetallefactura;
	}

}