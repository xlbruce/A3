/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.banco.controller;

import javax.ejb.Local;

/**
 *
 * @author 31440983
 */
@Local
public interface LoginManagerLocal {
    
    public boolean auth(String username,String senha);
}
