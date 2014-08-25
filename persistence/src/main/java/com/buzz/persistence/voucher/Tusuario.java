package com.buzz.persistence.voucher;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the TUSUARIO database table.
 * 
 */
@Entity
@NamedQuery(name="Tusuario.findAll", query="SELECT t FROM Tusuario t")
public class Tusuario implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TusuarioPK id;

	private byte activo;

	@Lob
	private String contrasena;

	//@Temporal(TemporalType.TIMESTAMP)
	private Timestamp fdesde;

	private String identificacion;

	private String razonSocial;

	//uni-directional many-to-one association to Tusuarioid
	@ManyToOne
	@JoinColumn(name="cusuario_fk")
	private Tusuarioid tusuarioid;

	public Tusuario() {
	}

	public TusuarioPK getId() {
		return this.id;
	}

	public void setId(TusuarioPK id) {
		this.id = id;
	}

	public byte getActivo() {
		return this.activo;
	}

	public void setActivo(byte activo) {
		this.activo = activo;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Tusuarioid getTusuarioid() {
		return this.tusuarioid;
	}

	public void setTusuarioid(Tusuarioid tusuarioid) {
		this.tusuarioid = tusuarioid;
	}

}