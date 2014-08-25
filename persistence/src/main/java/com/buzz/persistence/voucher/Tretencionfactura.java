package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TRETENCIONFACTURA database table.
 * 
 */
@Entity
@NamedQuery(name="Tretencionfactura.findAll", query="SELECT t FROM Tretencionfactura t")
public class Tretencionfactura implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int crfactura;

	private String codigo;

	@Column(name="codigo_porcentaje")
	private String codigoPorcentaje;

	private float tarifa;

	private float valor;

	//bi-directional many-to-one association to Tcabecerafactura
	@ManyToOne
	@JoinColumn(name="ccfactura_fk")
	private Tcabecerafactura tcabecerafactura;

	public Tretencionfactura() {
	}

	public int getCrfactura() {
		return this.crfactura;
	}

	public void setCrfactura(int crfactura) {
		this.crfactura = crfactura;
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

	public Tcabecerafactura getTcabecerafactura() {
		return this.tcabecerafactura;
	}

	public void setTcabecerafactura(Tcabecerafactura tcabecerafactura) {
		this.tcabecerafactura = tcabecerafactura;
	}

}