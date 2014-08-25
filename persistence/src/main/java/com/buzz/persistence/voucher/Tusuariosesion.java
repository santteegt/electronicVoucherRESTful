package com.buzz.persistence.voucher;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the TUSUARIOSESION database table.
 * 
 */
@Entity
@NamedQuery(name="Tusuariosesion.findAll", query="SELECT t FROM Tusuariosesion t")
public class Tusuariosesion implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String csesion;

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_acceso")
	private Timestamp fechaAcceso;

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_vigencia")
	private Timestamp fechaVigencia;

	//uni-directional many-to-one association to Tusuarioid
	@ManyToOne
	@JoinColumn(name="cusuario_fk")
	private Tusuarioid tusuarioid;

	public Tusuariosesion() {
	}

	public String getCsesion() {
		return this.csesion;
	}

	public void setCsesion(String csesion) {
		this.csesion = csesion;
	}

	public Date getFechaAcceso() {
		return this.fechaAcceso;
	}

	public void setFechaAcceso(Timestamp fechaAcceso) {
		this.fechaAcceso = fechaAcceso;
	}

	public Date getFechaVigencia() {
		return this.fechaVigencia;
	}

	public void setFechaVigencia(Timestamp fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public Tusuarioid getTusuarioid() {
		return this.tusuarioid;
	}

	public void setTusuarioid(Tusuarioid tusuarioid) {
		this.tusuarioid = tusuarioid;
	}

}