/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;
import java.sql.Timestamp;



/**
 *
 * @author Oscar
 */
public class Usuarios implements Serializable {
    private int IdUsuario;
    private String Email;
    private String Clave;
    private Timestamp UltimoAcceso;
    private char Tipo;
    private char Bloqueado;

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public Timestamp getUltimoAcceso() {
        return UltimoAcceso;
    }

    public void setUltimoAcceso(Timestamp UltimoAcceso) {
        this.UltimoAcceso = UltimoAcceso;
    }

    public char getTipo() {
        return Tipo;
    }

    public void setTipo(char Tipo) {
        this.Tipo = Tipo;
    }

    public char getBloqueado() {
        return Bloqueado;
    }

    public void setBloqueado(char Bloqueado) {
        this.Bloqueado = Bloqueado;
    }
    
}
