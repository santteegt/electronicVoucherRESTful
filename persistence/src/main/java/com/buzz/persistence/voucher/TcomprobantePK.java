/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author buzz
 */
@Embeddable
public class TcomprobantePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ccontribuyente_fk", nullable = false)
    private int ccontribuyenteFk;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String idpeticion;

    public TcomprobantePK() {
    }

    public TcomprobantePK(int ccontribuyenteFk, String idpeticion) {
        this.ccontribuyenteFk = ccontribuyenteFk;
        this.idpeticion = idpeticion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ccontribuyenteFk;
        hash += (idpeticion != null ? idpeticion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcomprobantePK)) {
            return false;
        }
        TcomprobantePK other = (TcomprobantePK) object;
        if (this.ccontribuyenteFk != other.ccontribuyenteFk) {
            return false;
        }
        if ((this.idpeticion == null && other.idpeticion != null) || (this.idpeticion != null && !this.idpeticion.equals(other.idpeticion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.TcomprobantePK[ ccontribuyenteFk=" + ccontribuyenteFk + ", idpeticion=" + idpeticion + " ]";
    }
    
}
