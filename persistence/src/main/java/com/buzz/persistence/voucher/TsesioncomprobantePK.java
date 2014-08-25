package com.buzz.persistence.voucher;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the TSESIONCOMPROBANTE database table.
 * 
 */
@Embeddable
public class TsesioncomprobantePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String idpeticion;

	@Column(name="ccontribuyente_fk", insertable=false, updatable=false)
	private int ccontribuyenteFk;

	public TsesioncomprobantePK() {
	}
	
	public TsesioncomprobantePK(String idpeticion, int ccontribuyenteFk) {
		this.idpeticion = idpeticion;
		this.ccontribuyenteFk = ccontribuyenteFk;
	}

	public String getIdpeticion() {
		return this.idpeticion;
	}
	public void setIdpeticion(String idpeticion) {
		this.idpeticion = idpeticion;
	}
	public int getCcontribuyenteFk() {
		return this.ccontribuyenteFk;
	}
	public void setCcontribuyenteFk(int ccontribuyenteFk) {
		this.ccontribuyenteFk = ccontribuyenteFk;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TsesioncomprobantePK)) {
			return false;
		}
		TsesioncomprobantePK castOther = (TsesioncomprobantePK)other;
		return 
			this.idpeticion.equals(castOther.idpeticion)
			&& (this.ccontribuyenteFk == castOther.ccontribuyenteFk);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idpeticion.hashCode();
		hash = hash * prime + this.ccontribuyenteFk;
		
		return hash;
	}
}