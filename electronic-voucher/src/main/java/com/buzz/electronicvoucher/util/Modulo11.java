package com.buzz.electronicvoucher.util;

public class Modulo11 {
	 
    public String invertirCadena(String cadena) {
        String cadenaInvertida = "";
        for (int x = cadena.length() - 1; x >= 0; x--) {
            cadenaInvertida = cadenaInvertida + cadena.charAt(x);
        }
        return cadenaInvertida;
    }
 
    public int obtenerSumaPorDigitos(String cadena) {
        int pivote = 2;
        int longitudCadena = cadena.length();
        int cantidadTotal = 0;
        int b = 1;
        for (int i = 0; i < longitudCadena; i++) {
            if (pivote == 7) {
                pivote = 2;
            }
            int temporal = Integer.parseInt("" + cadena.substring(i, b));
            b++;
            temporal *= pivote;
            pivote++;
            cantidadTotal += temporal;
        }
        cantidadTotal = 11 - cantidadTotal % 11;
        return cantidadTotal;
    }
    
    public String obtainEncoding(String code) {
    	Integer suma = Integer.valueOf(0);
        Integer sumt = Integer.valueOf(0);
        Integer con = Integer.valueOf(2);
        Integer j = code.length();
    	for (int i = code.length(); i > 0; i--) {
    	      suma = Integer.parseInt(code.substring(i - 1, i)) * con;
    	      sumt = sumt + suma;
    	      j = j - 1;
    	      con = con == 7 ? 2:con+1;
    	    }
    	    sumt = 11 - sumt % 11;
    	    if (sumt == 11) {
    	      sumt = 0;
    	    }
    	    if (sumt == 10) {
    	      sumt = 1;
    	    }
    	    code = code + sumt;
    	    
    	    return code;
    }
 
    public static void main(String args[]) throws Exception {
        Modulo11 a = new Modulo11();
        System.out.println(a.obtenerSumaPorDigitos(a.invertirCadena("070820140101903790350011001888000000501000002011")));
        System.out.println(a.obtenerSumaPorDigitos(a.obtainEncoding("070820140101903790350011001888000000501000002011")));
    }
}
