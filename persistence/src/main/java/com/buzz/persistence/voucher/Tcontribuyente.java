package com.buzz.persistence.voucher;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TCONTRIBUYENTE database table.
 * 
 */
@Entity
@NamedQuery(name="Tcontribuyente.findAll", query="SELECT t FROM Tcontribuyente t")
public class Tcontribuyente implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TcontribuyentePK id;

	//@Temporal(TemporalType.TIMESTAMP)
	private Timestamp fdesde;

	private String identificacion;

	@Lob
	private byte[] logo;

	@Column(name="nombre_comercial")
	private String nombreComercial;

	@Column(name="razon_social")
	private String razonSocial;

	//uni-directional many-to-one association to Ttipocontribuyente
	@ManyToOne
	@JoinColumn(name="ctipocontribuyente_fk")
	private Ttipocontribuyente ttipocontribuyente;

	//bi-directional many-to-one association to Tusuarioid
	@OneToMany(mappedBy="tcontribuyente")
	private List<Tusuarioid> tusuarioids;

	public Tcontribuyente() {
	}

	public TcontribuyentePK getId() {
		return this.id;
	}

	public void setId(TcontribuyentePK id) {
		this.id = id;
	}

	public Date getFdesde() {
		return this.fdesde;
	}

	public void setFdesde(Timestamp fdesde) {
		this.fdesde = fdesde;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getNombreComercial() {
		return this.nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Ttipocontribuyente getTtipocontribuyente() {
		return this.ttipocontribuyente;
	}

	public void setTtipocontribuyente(Ttipocontribuyente ttipocontribuyente) {
		this.ttipocontribuyente = ttipocontribuyente;
	}

	public List<Tusuarioid> getTusuarioids() {
		return this.tusuarioids;
	}

	public void setTusuarioids(List<Tusuarioid> tusuarioids) {
		this.tusuarioids = tusuarioids;
	}

	public Tusuarioid addTusuarioid(Tusuarioid tusuarioid) {
		getTusuarioids().add(tusuarioid);
		tusuarioid.setTcontribuyente(this);

		return tusuarioid;
	}

	public Tusuarioid removeTusuarioid(Tusuarioid tusuarioid) {
		getTusuarioids().remove(tusuarioid);
		tusuarioid.setTcontribuyente(null);

		return tusuarioid;
	}

}