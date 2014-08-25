package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TDETALLEADICIONALDESTINATARIOGUIAREMISION database table.
 * 
 */
@Entity
@NamedQuery(name="Tdetalleadicionaldestinatarioguiaremision.findAll", query="SELECT t FROM Tdetalleadicionaldestinatarioguiaremision t")
public class Tdetalleadicionaldestinatarioguiaremision implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cdaddgremision;

	private String nombre;

	private String valor;

	//bi-directional many-to-one association to Tdetalledestinatarioguiaremision
	@ManyToOne
	@JoinColumn(name="cddgremision_fk")
	private Tdetalledestinatarioguiaremision tdetalledestinatarioguiaremision;

	public Tdetalleadicionaldestinatarioguiaremision() {
	}

	public int getCdaddgremision() {
		return this.cdaddgremision;
	}

	public void setCdaddgremision(int cdaddgremision) {
		this.cdaddgremision = cdaddgremision;
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

	public Tdetalledestinatarioguiaremision getTdetalledestinatarioguiaremision() {
		return this.tdetalledestinatarioguiaremision;
	}

	public void setTdetalledestinatarioguiaremision(Tdetalledestinatarioguiaremision tdetalledestinatarioguiaremision) {
		this.tdetalledestinatarioguiaremision = tdetalledestinatarioguiaremision;
	}

}