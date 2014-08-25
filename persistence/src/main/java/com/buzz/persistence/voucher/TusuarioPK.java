package com.buzz.persistence.voucher;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The primary key class for the TUSUARIO database table.
 * 
 */
@Embeddable
public class TusuarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cusuario_fk", insertable=false, updatable=false)
	private String cusuarioFk;

	//@Temporal(TemporalType.TIMESTAMP)
	private Timestamp fhasta;

	public TusuarioPK() {
	}
	
	public TusuarioPK(String cusuarioFk, Timestamp fhasta) {
		this.cusuarioFk = cusuarioFk;
		this.fhasta = fhasta;
	}

	public String getCusuarioFk() {
		return this.cusuarioFk;
	}
	public void setCusuarioFk(String cusuarioFk) {
		this.cusuarioFk = cusuarioFk;
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
		if (!(other instanceof TusuarioPK)) {
			return false;
		}
		TusuarioPK castOther = (TusuarioPK)other;
		return 
			this.cusuarioFk.equals(castOther.cusuarioFk)
			&& this.fhasta.equals(castOther.fhasta);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cusuarioFk.hashCode();
		hash = hash * prime + this.fhasta.hashCode();
		
		return hash;
	}
}