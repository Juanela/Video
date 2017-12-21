/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Sql.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Duoc UC
 */
public class Pelicula {
    
    protected int codigo;
    protected int precio;
    protected int id_categoria;
    protected String formato4k;
    protected String nombre;

    public Pelicula() {
    }

    public Pelicula(int codigo, int precio, int id_categoria, String formato4k, String nombre) {
        this.codigo = codigo;
        this.precio = precio;
        this.id_categoria = id_categoria;
        this.formato4k = formato4k;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getFormato4k() {
        return formato4k;
    }

    public void setFormato4k(String formato4k) {
        this.formato4k = formato4k;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "codigo=" + codigo + ", precio=" + precio + ", id_categoria=" + id_categoria + ", formato4k=" + formato4k + ", nombre=" + nombre + '}';
    }
    
    //Verificar Pelicula
    public static boolean buscarPeli(Integer codigo) {
        try {
            Connection cnx = Conexion.getConexion();
            String query = "SELECT * FROM pelicula WHERE codigo = ? ";
            PreparedStatement select = cnx.prepareStatement(query);
            select.setInt(1, codigo);
            select.execute();
            ResultSet rs = select.getResultSet();
            
            if (rs.next()) {
                cnx.close();
                return true;
            } else {
                cnx.close();
                return false;
            }

        } catch (SQLException s) {
            System.out.println("Error SQL  " + s.getMessage());
            return true;
        } catch (Exception e) {
            System.out.println("Error SQL" + e.getMessage());
            return true;
        }
    }
    
    public ArrayList<Pelicula> buscarPorCodigo(int cod){
        ArrayList<Pelicula> listaChocolate = new ArrayList<Pelicula>();
        try{
            Connection cnx4 = Conexion.getConexion();
            String query = "SELECT * FROM pelicula WHERE codigo = ?";
            PreparedStatement buscarPorCodigo = cnx4.prepareStatement(query);
            buscarPorCodigo.setInt(1, cod);
            ResultSet rs = buscarPorCodigo.executeQuery();
            while(rs.next()){
                Pelicula cho = new Pelicula();
                cho.setNombre(rs.getString("nombre"));
                cho.setPrecio(rs.getInt("precio"));
                cho.setId_categoria(rs.getInt("id_categoria"));
                cho.setFormato4k(rs.getString("formato4k"));
                listaChocolate.add(cho);
            }
        }catch(SQLException s){
            System.out.println("Error SQL "+s.getMessage());
        }catch(Exception e){
            System.out.println("Error "+e.getMessage());
        }
        return listaChocolate;
    }
    
    //Agregar PELICULA
    public static boolean guardarPeli(int cod, int prc, int cat, String formato, String nom) {

        try {
            Connection cnx = Conexion.getConexion();
            String query = "INSERT INTO pelicula (codigo,precio,id_categoria,formato4k,nombre) VALUES (?,?,?,?,?)";
            PreparedStatement insertar = cnx.prepareStatement(query);
            insertar.setInt(1, cod);
            insertar.setInt(2, prc);
            insertar.setInt(3, cat);
            insertar.setString(4, formato);
            insertar.setString(5, nom);
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
    
    public static boolean eliminarPeli(int codigo) {
        int n = 0;
        PreparedStatement borrar;
        try {
            Connection cnx4 = Conexion.getConexion();
            String query = "DELETE FROM pelicula WHERE codigo=?";
            borrar = cnx4.prepareStatement(query);
            borrar.setInt(1, codigo);
            n = borrar.executeUpdate();
            borrar.close();           
            cnx4.close();
            return true;
        } catch (SQLException s) {
            System.out.println("Error SQL al eliminar" + s.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al eliminar" + e.getMessage());
            return false;
        }    
    }
    
    public DefaultTableModel ListadoPeli(){
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Id","Código","Precio","Id Categoría","Formato 4k","Nombre"};
      try{
         Connection cnx3 = Conexion.getConexion();
         PreparedStatement pstm = cnx3.prepareStatement( "SELECT count(*) as total FROM pelicula");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
      Object[][] data = new String[registros][6];
      try{
         Connection cnx4 = Conexion.getConexion();
         PreparedStatement pstm = cnx4.prepareStatement("SELECT * FROM pelicula");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "id" );
                data[i][1] = res.getString( "codigo" );
                data[i][2] = res.getString( "precio" );
                data[i][3] = res.getString( "id_categoria" );
                data[i][4] = res.getString( "formato4k" );
                data[i][5] = res.getString( "nombre" );
            i++;
         }
         res.close();
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
    
    public DefaultTableModel ListadoPeliRom(){
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Id","Código","Precio","Id Categoría","Formato 4k","Nombre"};
      try{
         Connection cnx5 = Conexion.getConexion();
         PreparedStatement pstm = cnx5.prepareStatement( "SELECT count(*) as total FROM pelicula");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
      Object[][] data = new String[registros][6];
      try{
         Connection cnx6 = Conexion.getConexion();
         PreparedStatement pstm = cnx6.prepareStatement("SELECT * FROM pelicula WHERE id_categoria = 2");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "id" );
                data[i][1] = res.getString( "codigo" );
                data[i][2] = res.getString( "precio" );
                data[i][3] = res.getString( "id_categoria" );
                data[i][4] = res.getString( "formato4k" );
                data[i][5] = res.getString( "nombre" );
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
