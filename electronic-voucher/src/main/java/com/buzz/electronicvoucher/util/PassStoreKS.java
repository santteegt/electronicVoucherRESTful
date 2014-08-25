package com.buzz.electronicvoucher.util;

import java.security.cert.X509Certificate;

import es.mityc.javasign.pkstore.IPassStoreKS;

public class PassStoreKS implements IPassStoreKS {

	private transient String password;

	public PassStoreKS(final String pass) {
		this.password = new String(pass);
	}

	public char[] getPassword(final X509Certificate certificate,
			final String alias) {
		return password.toCharArray();
	}
}
