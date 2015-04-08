/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buzz.electronicvoucher;

import com.buzz.electronicvoucher.util.SOAPClient;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karina
 */
public class ElectronicVoucherThread extends Thread {

    String printedSoapResponse, endpointR, sopRes;
    int intentos;
    volatile boolean band=true;

    public ElectronicVoucherThread(String printedSoapResponse, String endpointR, String sopRes, int intentos) {
        this.printedSoapResponse = printedSoapResponse;
        this.endpointR = endpointR;
        this.sopRes = sopRes;
        this.intentos = intentos;
    }

    public String getPrintedSoapResponse() {
        return printedSoapResponse;
    }
    
    public void terminate(){
        band=false;
    }
    
    @Override
    public void run() {
        while(band){
            int cont = 0;
            do {
                try {
                    printedSoapResponse = SOAPClient.getInstance().soapSendReal(
                            endpointR, sopRes);
                    cont = cont + 1;
                } catch (Exception ex) {
                    Logger.getLogger(ElectronicVoucherThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (printedSoapResponse.contains("AUTORIZADO") == false
                    && cont < intentos);
        }
    }

}
