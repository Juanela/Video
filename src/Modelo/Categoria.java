/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Sql.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Duoc UC
 */
public class Categoria {
    protected int id;
    protected String descripcion;

    public Categoria() {
    }

    public Categoria(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }
    
    //Agregar CATEGORIA
    public static boolean guardarCat(String desc) {

        try {
            Connection cnx = Conexion.getConexion();
            String query = "INSERT INTO categoria (descripcion) VALUES (?)";
            PreparedStatement insertar = cnx.prepareStatement(query);
            insertar.setString(1, desc);
            insertar.execute();
            insertar.close();
            cnx.close();
            return true;
        } catch (SQLException s) {
            System.out.println("Error SQL al agregar" + s.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al agregar" + e.getMessage());
            return false;
        }
    }
    
    //listar Categorias para agregar peli
    public static ArrayList<Categoria> listarCat() {
        ArrayList<Categoria> listaCat = new ArrayList<>();
        try {
            Connection cnx2 = Conexion.getConexion();
            String query = "SELECT * FROM categoria";
            PreparedStatement listarcat = cnx2.prepareStatement(query);
            ResultSet rs = listarcat.executeQuery();
            while (rs.next()) {
                Categoria dto = new Categoria();
                dto.setId(rs.getInt("id"));
                dto.setDescripcion(rs.getString("descripcion"));
                listaCat.add(dto);
            }
        } catch (SQLException s) {
            System.out.println("Error SQL" + s.getMessage());
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } return listaCat;
    }
    
    public DefaultTableModel ListadoCat(){
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Id","Descripcion"};
      try{
         Connection cnx3 = Conexion.getConexion();
         PreparedStatement pstm = cnx3.prepareStatement( "SELECT count(*) as total FROM categoria");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
      Object[][] data = new String[registros][2];
      try{
         Connection cnx4 = Conexion.getConexion();
         PreparedStatement pstm = cnx4.prepareStatement("SELECT * FROM categoria");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "id" );
                data[i][1] = res.getString( "descripcion" );
            i++;
         }
         res.close();
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
}
