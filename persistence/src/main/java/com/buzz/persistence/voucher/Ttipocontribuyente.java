package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TTIPOCONTRIBUYENTE database table.
 * 
 */
@Entity
@NamedQuery(name="Ttipocontribuyente.findAll", query="SELECT t FROM Ttipocontribuyente t")
public class Ttipocontribuyente implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ctipocontribuyente;

	private String descripcion;

	public Ttipocontribuyente() {
	}

	public int getCtipocontribuyente() {
		return this.ctipocontribuyente;
	}

	public void setCtipocontribuyente(int ctipocontribuyente) {
		this.ctipocontribuyente = ctipocontribuyente;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}