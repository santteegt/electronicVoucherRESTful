package com.buzz.persistence.voucher;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TDESTINATARIOGUIAREMISION database table.
 * 
 */
@Entity
@NamedQuery(name="Tdestinatarioguiaremision.findAll", query="SELECT t FROM Tdestinatarioguiaremision t")
public class Tdestinatarioguiaremision implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cdgremision;

	@Column(name="cestablecimiento_destino")
	private String cestablecimientoDestino;

	@Column(name="codigo_doc_sustento")
	private String codigoDocSustento;

	@Column(name="direccion_destinatario")
	private String direccionDestinatario;

	@Column(name="documento_aduanero_unico")
	private String documentoAduaneroUnico;

	@Temporal(TemporalType.DATE)
	@Column(name="femision_doc_sustento")
	private Date femisionDocSustento;

	@Column(name="identificacion_destinatario")
	private String identificacionDestinatario;

	@Column(name="motivo_traslado")
	private String motivoTraslado;

	@Column(name="numero_aut_doc_sustento")
	private String numeroAutDocSustento;

	@Column(name="numero_doc_sustento")
	private String numeroDocSustento;

	@Column(name="razon_social_destinatario")
	private String razonSocialDestinatario;

	private String ruta;

	//bi-directional many-to-one association to Tcabeceraguiaremision
	@ManyToOne
	@JoinColumn(name="ccgremision_fk")
	private Tcabeceraguiaremision tcabeceraguiaremision;

	//bi-directional many-to-one association to Tdetalledestinatarioguiaremision
	@OneToMany(mappedBy="tdestinatarioguiaremision")
	private List<Tdetalledestinatarioguiaremision> tdetalledestinatarioguiaremisions;

	public Tdestinatarioguiaremision() {
	}

	public int getCdgremision() {
		return this.cdgremision;
	}

	public void setCdgremision(int cdgremision) {
		this.cdgremision = cdgremision;
	}

	public String getCestablecimientoDestino() {
		return this.cestablecimientoDestino;
	}

	public void setCestablecimientoDestino(String cestablecimientoDestino) {
		this.cestablecimientoDestino = cestablecimientoDestino;
	}

	public String getCodigoDocSustento() {
		return this.codigoDocSustento;
	}

	public void setCodigoDocSustento(String codigoDocSustento) {
		this.codigoDocSustento = codigoDocSustento;
	}

	public String getDireccionDestinatario() {
		return this.direccionDestinatario;
	}

	public void setDireccionDestinatario(String direccionDestinatario) {
		this.direccionDestinatario = direccionDestinatario;
	}

	public String getDocumentoAduaneroUnico() {
		return this.documentoAduaneroUnico;
	}

	public void setDocumentoAduaneroUnico(String documentoAduaneroUnico) {
		this.documentoAduaneroUnico = documentoAduaneroUnico;
	}

	public Date getFemisionDocSustento() {
		return this.femisionDocSustento;
	}

	public void setFemisionDocSustento(Date femisionDocSustento) {
		this.femisionDocSustento = femisionDocSustento;
	}

	public String getIdentificacionDestinatario() {
		return this.identificacionDestinatario;
	}

	public void setIdentificacionDestinatario(String identificacionDestinatario) {
		this.identificacionDestinatario = identificacionDestinatario;
	}

	public String getMotivoTraslado() {
		return this.motivoTraslado;
	}

	public void setMotivoTraslado(String motivoTraslado) {
		this.motivoTraslado = motivoTraslado;
	}

	public String getNumeroAutDocSustento() {
		return this.numeroAutDocSustento;
	}

	public void setNumeroAutDocSustento(String numeroAutDocSustento) {
		this.numeroAutDocSustento = numeroAutDocSustento;
	}

	public String getNumeroDocSustento() {
		return this.numeroDocSustento;
	}

	public void setNumeroDocSustento(String numeroDocSustento) {
		this.numeroDocSustento = numeroDocSustento;
	}

	public String getRazonSocialDestinatario() {
		return this.razonSocialDestinatario;
	}

	public void setRazonSocialDestinatario(String razonSocialDestinatario) {
		this.razonSocialDestinatario = razonSocialDestinatario;
	}

	public String getRuta() {
		return this.ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Tcabeceraguiaremision getTcabeceraguiaremision() {
		return this.tcabeceraguiaremision;
	}

	public void setTcabeceraguiaremision(Tcabeceraguiaremision tcabeceraguiaremision) {
		this.tcabeceraguiaremision = tcabeceraguiaremision;
	}

	public List<Tdetalledestinatarioguiaremision> getTdetalledestinatarioguiaremisions() {
		return this.tdetalledestinatarioguiaremisions;
	}

	public void setTdetalledestinatarioguiaremisions(List<Tdetalledestinatarioguiaremision> tdetalledestinatarioguiaremisions) {
		this.tdetalledestinatarioguiaremisions = tdetalledestinatarioguiaremisions;
	}

	public Tdetalledestinatarioguiaremision addTdetalledestinatarioguiaremision(Tdetalledestinatarioguiaremision tdetalledestinatarioguiaremision) {
		getTdetalledestinatarioguiaremisions().add(tdetalledestinatarioguiaremision);
		tdetalledestinatarioguiaremision.setTdestinatarioguiaremision(this);

		return tdetalledestinatarioguiaremision;
	}

	public Tdetalledestinatarioguiaremision removeTdetalledestinatarioguiaremision(Tdetalledestinatarioguiaremision tdetalledestinatarioguiaremision) {
		getTdetalledestinatarioguiaremisions().remove(tdetalledestinatarioguiaremision);
		tdetalledestinatarioguiaremision.setTdestinatarioguiaremision(null);

		return tdetalledestinatarioguiaremision;
	}

}