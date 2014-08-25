package com.buzz.persistence.voucher;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the TCOMPROBANTE database table.
 * 
 */
@Embeddable
public class TcomprobantePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ccontribuyente_fk", insertable=false, updatable=false)
	private int ccontribuyenteFk;

	@Column(name="idpeticion", insertable=false, updatable=false)
	private String idpeticion;

	public TcomprobantePK() {
	}
	
	
	
	public int getCcontribuyenteFk() {
		return ccontribuyenteFk;
	}



	public void setCcontribuyenteFk(int ccontribuyenteFk) {
		this.ccontribuyenteFk = ccontribuyenteFk;
	}



	public String getIdpeticion() {
		return idpeticion;
	}



	public void setIdpeticion(String idpeticion) {
		this.idpeticion = idpeticion;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TcomprobantePK)) {
			return false;
		}
		TcomprobantePK castOther = (TcomprobantePK)other;
		return 
			(this.ccontribuyenteFk == castOther.ccontribuyenteFk)
			&& this.idpeticion.equals(castOther.idpeticion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ccontribuyenteFk;
		hash = hash * prime + this.idpeticion.hashCode();
		
		return hash;
	}

}