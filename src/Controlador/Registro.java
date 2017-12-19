/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Vista.Inicio;
import Vista.Vista_agregar_peli;
import Vista.Vista_agregar_cat;
/**
 *
 * @author Duoc UC
 */
public class Registro {
    public static void main(String[] args) {
            
       new Observador(new Inicio() ).iniciar();
       
    }
}
