package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TINFORMACIONADICIONALCOMPROBANTE database table.
 * 
 */
@Entity
@NamedQuery(name="Tinformacionadicionalcomprobante.findAll", query="SELECT t FROM Tinformacionadicionalcomprobante t")
public class Tinformacionadicionalcomprobante implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ciacomprobante;

	private String nombre;

	private String valor;

	//bi-directional many-to-one association to Tcomprobante
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ccontribuyente_fk2", referencedColumnName="ccontribuyente_fk"),
		@JoinColumn(name="idpeticion_fk", referencedColumnName="idpeticion")
		})
	private Tcomprobante tcomprobante;

	public Tinformacionadicionalcomprobante() {
	}

	public int getCiacomprobante() {
		return this.ciacomprobante;
	}

	public void setCiacomprobante(int ciacomprobante) {
		this.ciacomprobante = ciacomprobante;
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

	public Tcomprobante getTcomprobante() {
		return this.tcomprobante;
	}

	public void setTcomprobante(Tcomprobante tcomprobante) {
		this.tcomprobante = tcomprobante;
	}

}