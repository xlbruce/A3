/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.banco.jms.sessionbeans;

import javax.ejb.Local;
import javax.jms.JMSException;

/**
 *
 * @author 31440983
 */
@Local
public interface ProducerSessionbeanLocal {
    
        public void sendMessageToQueue(String message) throws JMSException;
}
