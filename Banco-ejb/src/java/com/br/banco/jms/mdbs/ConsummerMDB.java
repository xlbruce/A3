/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.banco.jms.mdbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author 31440983
 */
@MessageDriven(mappedName = "jms/banco", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ConsummerMDB implements MessageListener {
    
    public ConsummerMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
    TextMessage tm = (TextMessage) message;
    
        try {
            System.out.println(tm.getText());
            salvar(new File("E:\Mackenzie\LP3\Banco\Banco\log.txt"));
        } catch (JMSException ex) {
            Logger.getLogger(ConsummerMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void salvar(File arquivo, String conteudo) throws  IOException{
    
    FileOutputStream streamDeSaida = new FileOutputStream(arquivo);
        streamDeSaida.write(conteudo.getBytes());
        streamDeSaida.close();
    }
    
    public static byte[] carregar(File arquivo) throws Exception{
    
        FileInputStream dispositivoEntrada = new FileInputStream(arquivo);
        byte[] conteudo = new byte[dispositivoEntrada.available()];
        dispositivoEntrada.read(conteudo);
        return conteudo;
        }
    
}
