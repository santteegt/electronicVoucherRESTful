package com.buzz.persistence.voucher;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the TUSUARIO database table.
 * 
 */
@Entity
@NamedQuery(name="Tusuarioid.findAll", query="SELECT t FROM Tusuario t")
public class Tusuarioid implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cusuario;

	//uni-directional many-to-one association to Tusuarioid
	@ManyToOne
	@JoinColumn(name="ccontribuyente_fk")
	private Tcontribuyente tcontribuyente;

	public Tusuarioid() {
	}
	

	public Tusuarioid(String cusuario, Tcontribuyente tcontribuyente) {
		this.cusuario = cusuario;
		this.tcontribuyente = tcontribuyente;
	}


	public String getCusuario() {
		return this.cusuario;
	}

	public void setCusuario(String cusuario) {
		this.cusuario = cusuario;
	}
	
	public Tcontribuyente getTcontribuyente() {
		return tcontribuyente;
	}

	public void setTcontribuyente(Tcontribuyente tcontribuyente) {
		this.tcontribuyente = tcontribuyente;
	}

}