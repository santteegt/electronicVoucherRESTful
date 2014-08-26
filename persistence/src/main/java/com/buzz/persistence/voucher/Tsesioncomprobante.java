/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author buzz
 */
@Entity
@Table(name = "TSESIONCOMPROBANTE", catalog = "buzzSRI", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tsesioncomprobante.findAll", query = "SELECT t FROM Tsesioncomprobante t"),
    @NamedQuery(name = "Tsesioncomprobante.findByCcontribuyenteFk", query = "SELECT t FROM Tsesioncomprobante t WHERE t.tsesioncomprobantePK.ccontribuyenteFk = :ccontribuyenteFk"),
    @NamedQuery(name = "Tsesioncomprobante.findByIdpeticion", query = "SELECT t FROM Tsesioncomprobante t WHERE t.tsesioncomprobantePK.idpeticion = :idpeticion"),
    @NamedQuery(name = "Tsesioncomprobante.findByCsesionFk", query = "SELECT t FROM Tsesioncomprobante t WHERE t.csesionFk = :csesionFk")})
public class Tsesioncomprobante implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TsesioncomprobantePK tsesioncomprobantePK;
    @Basic(optional = false)
    @Column(name = "csesion_fk", nullable = false, length = 30)
    private String csesionFk;
    @Basic(optional = false)
    @Lob
    @Column(name = "mensaje_entrada", nullable = false, length = 65535)
    private String mensajeEntrada;
    @Basic(optional = false)
    @Lob
    @Column(name = "mensaje_salida", nullable = false, length = 65535)
    private String mensajeSalida;
    @Basic(optional = false)
    @Lob
    @Column(name = "xml_comprobante", nullable = false, length = 65535)
    private String xmlComprobante;
    @Basic(optional = false)
    @Lob
    @Column(name = "resultado", nullable = false, length = 65535)
    private String resultado;
    @Lob
    @Column(name = "codigos_error", length = 65535)
    private String codigosError;
    @JoinColumns({
        @JoinColumn(name = "ccontribuyente_fk", referencedColumnName = "ccontribuyente_fk", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "idpeticion", referencedColumnName = "idpeticion", nullable = false, insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Tcomprobante tcomprobante;

    public Tsesioncomprobante() {
    }

    public Tsesioncomprobante(TsesioncomprobantePK tsesioncomprobantePK) {
        this.tsesioncomprobantePK = tsesioncomprobantePK;
    }

    public Tsesioncomprobante(TsesioncomprobantePK tsesioncomprobantePK, String csesionFk, String mensajeEntrada, String mensajeSalida, String xmlComprobante, String resultado) {
        this.tsesioncomprobantePK = tsesioncomprobantePK;
        this.csesionFk = csesionFk;
        this.mensajeEntrada = mensajeEntrada;
        this.mensajeSalida = mensajeSalida;
        this.xmlComprobante = xmlComprobante;
        this.resultado = resultado;
    }

    public Tsesioncomprobante(int ccontribuyenteFk, String idpeticion) {
        this.tsesioncomprobantePK = new TsesioncomprobantePK(ccontribuyenteFk, idpeticion);
    }

    public TsesioncomprobantePK getTsesioncomprobantePK() {
        return tsesioncomprobantePK;
    }

    public void setTsesioncomprobantePK(TsesioncomprobantePK tsesioncomprobantePK) {
        this.tsesioncomprobantePK = tsesioncomprobantePK;
    }

    public String getCsesionFk() {
        return csesionFk;
    }

    public void setCsesionFk(String csesionFk) {
        this.csesionFk = csesionFk;
    }

    public String getMensajeEntrada() {
        return mensajeEntrada;
    }

    public void setMensajeEntrada(String mensajeEntrada) {
        this.mensajeEntrada = mensajeEntrada;
    }

    public String getMensajeSalida() {
        return mensajeSalida;
    }

    public void setMensajeSalida(String mensajeSalida) {
        this.mensajeSalida = mensajeSalida;
    }

    public String getXmlComprobante() {
        return xmlComprobante;
    }

    public void setXmlComprobante(String xmlComprobante) {
        this.xmlComprobante = xmlComprobante;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getCodigosError() {
        return codigosError;
    }

    public void setCodigosError(String codigosError) {
        this.codigosError = codigosError;
    }

    public Tcomprobante getTcomprobante() {
        return tcomprobante;
    }

    public void setTcomprobante(Tcomprobante tcomprobante) {
        this.tcomprobante = tcomprobante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tsesioncomprobantePK != null ? tsesioncomprobantePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tsesioncomprobante)) {
            return false;
        }
        Tsesioncomprobante other = (Tsesioncomprobante) object;
        if ((this.tsesioncomprobantePK == null && other.tsesioncomprobantePK != null) || (this.tsesioncomprobantePK != null && !this.tsesioncomprobantePK.equals(other.tsesioncomprobantePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tsesioncomprobante[ tsesioncomprobantePK=" + tsesioncomprobantePK + " ]";
    }
    
}
