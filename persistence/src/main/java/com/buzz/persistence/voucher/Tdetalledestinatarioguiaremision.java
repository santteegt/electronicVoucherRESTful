package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TDETALLEDESTINATARIOGUIAREMISION database table.
 * 
 */
@Entity
@NamedQuery(name="Tdetalledestinatarioguiaremision.findAll", query="SELECT t FROM Tdetalledestinatarioguiaremision t")
public class Tdetalledestinatarioguiaremision implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cddgremision;

	private float cantidad;

	@Column(name="codigo_adicional")
	private String codigoAdicional;

	@Column(name="codigo_interno")
	private String codigoInterno;

	private String descripcion;

	//bi-directional many-to-one association to Tdetalleadicionaldestinatarioguiaremision
	@OneToMany(mappedBy="tdetalledestinatarioguiaremision")
	private List<Tdetalleadicionaldestinatarioguiaremision> tdetalleadicionaldestinatarioguiaremisions;

	//bi-directional many-to-one association to Tdestinatarioguiaremision
	@ManyToOne
	@JoinColumn(name="cdgremision_fk")
	private Tdestinatarioguiaremision tdestinatarioguiaremision;

	public Tdetalledestinatarioguiaremision() {
	}

	public int getCddgremision() {
		return this.cddgremision;
	}

	public void setCddgremision(int cddgremision) {
		this.cddgremision = cddgremision;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigoAdicional() {
		return this.codigoAdicional;
	}

	public void setCodigoAdicional(String codigoAdicional) {
		this.codigoAdicional = codigoAdicional;
	}

	public String getCodigoInterno() {
		return this.codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Tdetalleadicionaldestinatarioguiaremision> getTdetalleadicionaldestinatarioguiaremisions() {
		return this.tdetalleadicionaldestinatarioguiaremisions;
	}

	public void setTdetalleadicionaldestinatarioguiaremisions(List<Tdetalleadicionaldestinatarioguiaremision> tdetalleadicionaldestinatarioguiaremisions) {
		this.tdetalleadicionaldestinatarioguiaremisions = tdetalleadicionaldestinatarioguiaremisions;
	}

	public Tdetalleadicionaldestinatarioguiaremision addTdetalleadicionaldestinatarioguiaremision(Tdetalleadicionaldestinatarioguiaremision tdetalleadicionaldestinatarioguiaremision) {
		getTdetalleadicionaldestinatarioguiaremisions().add(tdetalleadicionaldestinatarioguiaremision);
		tdetalleadicionaldestinatarioguiaremision.setTdetalledestinatarioguiaremision(this);

		return tdetalleadicionaldestinatarioguiaremision;
	}

	public Tdetalleadicionaldestinatarioguiaremision removeTdetalleadicionaldestinatarioguiaremision(Tdetalleadicionaldestinatarioguiaremision tdetalleadicionaldestinatarioguiaremision) {
		getTdetalleadicionaldestinatarioguiaremisions().remove(tdetalleadicionaldestinatarioguiaremision);
		tdetalleadicionaldestinatarioguiaremision.setTdetalledestinatarioguiaremision(null);

		return tdetalleadicionaldestinatarioguiaremision;
	}

	public Tdestinatarioguiaremision getTdestinatarioguiaremision() {
		return this.tdestinatarioguiaremision;
	}

	public void setTdestinatarioguiaremision(Tdestinatarioguiaremision tdestinatarioguiaremision) {
		this.tdestinatarioguiaremision = tdestinatarioguiaremision;
	}

}