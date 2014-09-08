/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buzz.persistence.voucher;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author karina
 */
@Entity
@Table(name = "TDESTINATARIOGUIAREMISION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tdestinatarioguiaremision.findAll", query = "SELECT t FROM Tdestinatarioguiaremision t"),
    @NamedQuery(name = "Tdestinatarioguiaremision.findByCdgremision", query = "SELECT t FROM Tdestinatarioguiaremision t WHERE t.cdgremision = :cdgremision"),
    @NamedQuery(name = "Tdestinatarioguiaremision.findByIdentificacionDestinatario", query = "SELECT t FROM Tdestinatarioguiaremision t WHERE t.identificacionDestinatario = :identificacionDestinatario"),
    @NamedQuery(name = "Tdestinatarioguiaremision.findByRazonSocialDestinatario", query = "SELECT t FROM Tdestinatarioguiaremision t WHERE t.razonSocialDestinatario = :razonSocialDestinatario"),
    @NamedQuery(name = "Tdestinatarioguiaremision.findByDireccionDestinatario", query = "SELECT t FROM Tdestinatarioguiaremision t WHERE t.direccionDestinatario = :direccionDestinatario"),
    @NamedQuery(name = "Tdestinatarioguiaremision.findByMotivoTraslado", query = "SELECT t FROM Tdestinatarioguiaremision t WHERE t.motivoTraslado = :motivoTraslado"),
    @NamedQuery(name = "Tdestinatarioguiaremision.findByDocumentoAduaneroUnico", query = "SELECT t FROM Tdestinatarioguiaremision t WHERE t.documentoAduaneroUnico = :documentoAduaneroUnico"),
    @NamedQuery(name = "Tdestinatarioguiaremision.findByCestablecimientoDestino", query = "SELECT t FROM Tdestinatarioguiaremision t WHERE t.cestablecimientoDestino = :cestablecimientoDestino"),
    @NamedQuery(name = "Tdestinatarioguiaremision.findByRuta", query = "SELECT t FROM Tdestinatarioguiaremision t WHERE t.ruta = :ruta"),
    @NamedQuery(name = "Tdestinatarioguiaremision.findByCodigoDocSustento", query = "SELECT t FROM Tdestinatarioguiaremision t WHERE t.codigoDocSustento = :codigoDocSustento"),
    @NamedQuery(name = "Tdestinatarioguiaremision.findByNumeroDocSustento", query = "SELECT t FROM Tdestinatarioguiaremision t WHERE t.numeroDocSustento = :numeroDocSustento"),
    @NamedQuery(name = "Tdestinatarioguiaremision.findByNumeroAutDocSustento", query = "SELECT t FROM Tdestinatarioguiaremision t WHERE t.numeroAutDocSustento = :numeroAutDocSustento"),
    @NamedQuery(name = "Tdestinatarioguiaremision.findByFemisionDocSustento", query = "SELECT t FROM Tdestinatarioguiaremision t WHERE t.femisionDocSustento = :femisionDocSustento")})
public class Tdestinatarioguiaremision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cdgremision", nullable = false)
    private Integer cdgremision;
    @Basic(optional = false)
    @Column(name = "identificacion_destinatario", nullable = false, length = 13)
    private String identificacionDestinatario;
    @Basic(optional = false)
    @Column(name = "razon_social_destinatario", nullable = false, length = 300)
    private String razonSocialDestinatario;
    @Basic(optional = false)
    @Column(name = "direccion_destinatario", nullable = false, length = 300)
    private String direccionDestinatario;
    @Basic(optional = false)
    @Column(name = "motivo_traslado", nullable = false, length = 300)
    private String motivoTraslado;
    @Column(name = "documento_aduanero_unico", length = 20)
    private String documentoAduaneroUnico;
    @Column(name = "cestablecimiento_destino", length = 3)
    private String cestablecimientoDestino;
    @Column(name = "ruta", length = 300)
    private String ruta;
    @Column(name = "codigo_doc_sustento", length = 2)
    private String codigoDocSustento;
    @Column(name = "numero_doc_sustento", length = 17)
    private String numeroDocSustento;
    @Column(name = "numero_aut_doc_sustento", length = 37)
    private String numeroAutDocSustento;
    @Column(name = "femision_doc_sustento")
    @Temporal(TemporalType.DATE)
    private Date femisionDocSustento;
    @JoinColumn(name = "ccgremision_fk", referencedColumnName = "ccgremision", nullable = false)
    @ManyToOne(optional = false)
    private Tcabeceraguiaremision ccgremisionFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdgremisionFk")
    private List<Tdetalledestinatarioguiaremision> tdetalledestinatarioguiaremisionList;

    public Tdestinatarioguiaremision() {
    }

    public Tdestinatarioguiaremision(Integer cdgremision) {
        this.cdgremision = cdgremision;
    }

    public Tdestinatarioguiaremision(Integer cdgremision, String identificacionDestinatario, String razonSocialDestinatario, String direccionDestinatario, String motivoTraslado) {
        this.cdgremision = cdgremision;
        this.identificacionDestinatario = identificacionDestinatario;
        this.razonSocialDestinatario = razonSocialDestinatario;
        this.direccionDestinatario = direccionDestinatario;
        this.motivoTraslado = motivoTraslado;
    }

    public Integer getCdgremision() {
        return cdgremision;
    }

    public void setCdgremision(Integer cdgremision) {
        this.cdgremision = cdgremision;
    }

    public String getIdentificacionDestinatario() {
        return identificacionDestinatario;
    }

    public void setIdentificacionDestinatario(String identificacionDestinatario) {
        this.identificacionDestinatario = identificacionDestinatario;
    }

    public String getRazonSocialDestinatario() {
        return razonSocialDestinatario;
    }

    public void setRazonSocialDestinatario(String razonSocialDestinatario) {
        this.razonSocialDestinatario = razonSocialDestinatario;
    }

    public String getDireccionDestinatario() {
        return direccionDestinatario;
    }

    public void setDireccionDestinatario(String direccionDestinatario) {
        this.direccionDestinatario = direccionDestinatario;
    }

    public String getMotivoTraslado() {
        return motivoTraslado;
    }

    public void setMotivoTraslado(String motivoTraslado) {
        this.motivoTraslado = motivoTraslado;
    }

    public String getDocumentoAduaneroUnico() {
        return documentoAduaneroUnico;
    }

    public void setDocumentoAduaneroUnico(String documentoAduaneroUnico) {
        this.documentoAduaneroUnico = documentoAduaneroUnico;
    }

    public String getCestablecimientoDestino() {
        return cestablecimientoDestino;
    }

    public void setCestablecimientoDestino(String cestablecimientoDestino) {
        this.cestablecimientoDestino = cestablecimientoDestino;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getCodigoDocSustento() {
        return codigoDocSustento;
    }

    public void setCodigoDocSustento(String codigoDocSustento) {
        this.codigoDocSustento = codigoDocSustento;
    }

    public String getNumeroDocSustento() {
        return numeroDocSustento;
    }

    public void setNumeroDocSustento(String numeroDocSustento) {
        this.numeroDocSustento = numeroDocSustento;
    }

    public String getNumeroAutDocSustento() {
        return numeroAutDocSustento;
    }

    public void setNumeroAutDocSustento(String numeroAutDocSustento) {
        this.numeroAutDocSustento = numeroAutDocSustento;
    }

    public Date getFemisionDocSustento() {
        return femisionDocSustento;
    }

    public void setFemisionDocSustento(Date femisionDocSustento) {
        this.femisionDocSustento = femisionDocSustento;
    }

    public Tcabeceraguiaremision getCcgremisionFk() {
        return ccgremisionFk;
    }

    public void setCcgremisionFk(Tcabeceraguiaremision ccgremisionFk) {
        this.ccgremisionFk = ccgremisionFk;
    }

    @XmlTransient
    public List<Tdetalledestinatarioguiaremision> getTdetalledestinatarioguiaremisionList() {
        return tdetalledestinatarioguiaremisionList;
    }

    public void setTdetalledestinatarioguiaremisionList(List<Tdetalledestinatarioguiaremision> tdetalledestinatarioguiaremisionList) {
        this.tdetalledestinatarioguiaremisionList = tdetalledestinatarioguiaremisionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdgremision != null ? cdgremision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tdestinatarioguiaremision)) {
            return false;
        }
        Tdestinatarioguiaremision other = (Tdestinatarioguiaremision) object;
        if ((this.cdgremision == null && other.cdgremision != null) || (this.cdgremision != null && !this.cdgremision.equals(other.cdgremision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buzz.persistence.voucher.Tdestinatarioguiaremision[ cdgremision=" + cdgremision + " ]";
    }
    
}
