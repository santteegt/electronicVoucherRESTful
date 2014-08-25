package com.buzz.persistence.voucher;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The primary key class for the TCONTRIBUYENTE database table.
 * 
 */
@Embeddable
public class TcontribuyentePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int ccontribuyente;

	//@Temporal(TemporalType.TIMESTAMP)
	private Timestamp fhasta;

	public TcontribuyentePK() {
	}
	
	public TcontribuyentePK(int ccontribuyente, Timestamp fhasta) {
		super();
		this.ccontribuyente = ccontribuyente;
		this.fhasta = fhasta;
	}

	public int getCcontribuyente() {
		return this.ccontribuyente;
	}
	public void setCcontribuyente(int ccontribuyente) {
		this.ccontribuyente = ccontribuyente;
	}
	public java.util.Date getFhasta() {
		return this.fhasta;
	}
	public void setFhasta(Timestamp fhasta) {
		this.fhasta = fhasta;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TcontribuyentePK)) {
			return false;
		}
		TcontribuyentePK castOther = (TcontribuyentePK)other;
		return 
			(this.ccontribuyente == castOther.ccontribuyente)
			&& this.fhasta.equals(castOther.fhasta);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ccontribuyente;
		hash = hash * prime + this.fhasta.hashCode();
		
		return hash;
	}
}