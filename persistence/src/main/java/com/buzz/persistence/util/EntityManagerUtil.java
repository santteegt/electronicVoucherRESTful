/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buzz.persistence.util;

import javax.persistence.Persistence;

/**
 *
 * @author buzz
 */
public final class EntityManagerUtil {

    private static final javax.persistence.EntityManagerFactory emfInstance
            = Persistence.createEntityManagerFactory("persistence");

    private EntityManagerUtil() {
    }

    public static javax.persistence.EntityManagerFactory get() {
        return emfInstance;
    }
}
