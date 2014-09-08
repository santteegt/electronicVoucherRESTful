package com.buzz.tools;

import java.rmi.server.UID;

/**
 * Clase que permite crear tokens.
 */
public final class TokenCreator {
    
    private TokenCreator(){}
    
    public static String createToken(){
        return new UID().toString();
    }
}
