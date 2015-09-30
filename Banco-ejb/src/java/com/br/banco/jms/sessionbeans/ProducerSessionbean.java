/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.banco.jms.sessionbeans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author 31440983
 */
@Stateless
public class ProducerSessionbean implements ProducerSessionbeanLocal {
    @Resource(mappedName = "jms/banco")
    private Queue banco;
    @Resource(mappedName = "jms/bancoFactory")
    private ConnectionFactory bancoFactory;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    private Message createJMSMessageForjmsBanco(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToBanco(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = bancoFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(banco);
            messageProducer.send(createJMSMessageForjmsBanco(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public void sendMessageToQueue(String message) throws JMSException
    {
        sendJMSMessageToBanco(message);
    }
}
