/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Modelo.Pelicula;
import Modelo.Categoria;

import Vista.Inicio;
import Vista.Vista_agregar_peli;
import Vista.Vista_buscar_peli;
import Vista.Vista_elim_peli;
import Vista.Vista_listar_peli;

import Vista.Vista_agregar_cat;
import Vista.Vista_listar_cat;
import java.util.ArrayList;

/**
 *
 * @author Duoc UC
 */
public class Observador implements ActionListener, MouseListener {

    //vista
    private Inicio vistaPrincipal;
    private Vista_agregar_peli agregar_peli = new Vista_agregar_peli();
    private Vista_buscar_peli buscar_peli = new Vista_buscar_peli();
    private Vista_elim_peli elim_peli = new Vista_elim_peli();
    private Vista_listar_peli listar_peli = new Vista_listar_peli();

    private Vista_agregar_cat agregar_cat = new Vista_agregar_cat();
    private Vista_listar_cat listar_cat = new Vista_listar_cat();

    //modelo
    private Pelicula peli = new Pelicula();
    private Categoria cat = new Categoria();

    //acciones que se ejecuta por los controles de cada VISTA
    public enum Accion {
        btnVistaAgregarPeli,
        btnVistaElimPeli,
        btnVistaBuscarPeli,
        btnVistaListarPeli,
        btnVistaAgregarCat,
        btnVistaListarCat,
        btnSalir,
        btnGuardarCat,
        btnLimpiarCat,
        btnVolverCat,
        btnVolverListarPeli,
        listar_cat_volver,
        btnGuardarPeli,
        btnEliminarPeli,
        btnVolverElimPeli,
        btnBuscarPeli,
        btnVolverAgregar,
        btnAgregaDrama,
        
        

    }

    /**
     * Constructor de clase
     */
    public Observador(JFrame padre) {
        this.vistaPrincipal = (Inicio) padre;
    }

    /**
     * Inicia todos las acciones y listener de la vista
     */
    public void iniciar() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vistaPrincipal);
            SwingUtilities.updateComponentTreeUI(this.agregar_peli);
            SwingUtilities.updateComponentTreeUI(this.buscar_peli);
            SwingUtilities.updateComponentTreeUI(this.elim_peli);
            SwingUtilities.updateComponentTreeUI(this.listar_peli);
            SwingUtilities.updateComponentTreeUI(this.agregar_cat);
            SwingUtilities.updateComponentTreeUI(this.listar_cat);

            this.vistaPrincipal.setLocationRelativeTo(null);
            this.vistaPrincipal.setTitle("Video-Buster");
            this.vistaPrincipal.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }

        //Escuchamos los botones de todas partes
        this.vistaPrincipal.menu_agre_peli.setActionCommand("btnVistaAgregarPeli");
        this.vistaPrincipal.menu_agre_peli.addActionListener(this);
        this.vistaPrincipal.menu_elim_peli.setActionCommand("btnVistaElimPeli");
        this.vistaPrincipal.menu_elim_peli.addActionListener(this);
        this.vistaPrincipal.menu_buscar_peli.setActionCommand("btnVistaBuscarPeli");
        this.vistaPrincipal.menu_buscar_peli.addActionListener(this);
        this.vistaPrincipal.menu_listar_peli.setActionCommand("btnVistaListarPeli");
        this.vistaPrincipal.menu_listar_peli.addActionListener(this);

        this.vistaPrincipal.menu_agre_cat.setActionCommand("btnVistaAgregarCat");
        this.vistaPrincipal.menu_agre_cat.addActionListener(this);
        this.vistaPrincipal.menu_listar_cat.setActionCommand("btnVistaListarCat");
        this.vistaPrincipal.menu_listar_cat.addActionListener(this);

        this.vistaPrincipal.menu_salir.setActionCommand("btnSalir");
        this.vistaPrincipal.menu_salir.addActionListener(this);

        this.agregar_cat.agrega_cat.setActionCommand("btnGuardarCat");
        this.agregar_cat.agrega_cat.addActionListener(this);
        this.agregar_cat.btn_limpiar_cat.setActionCommand("btnLimpiarCat");
        this.agregar_cat.btn_limpiar_cat.addActionListener(this);
        this.agregar_cat.btnVolverCat.setActionCommand("btnVolverCat");
        this.agregar_cat.btnVolverCat.addActionListener(this);
        this.listar_cat.listar_cat_volver.setActionCommand("listar_cat_volver");
        this.listar_cat.listar_cat_volver.addActionListener(this);
        this.listar_peli.btn_volver_peli.setActionCommand("btnVolverListarPeli");
        this.listar_peli.btn_volver_peli.addActionListener(this);
                
        this.agregar_peli.guardar_peli.setActionCommand("btnGuardarPeli");
        this.agregar_peli.guardar_peli.addActionListener(this);
        this.agregar_peli.btnVolver.setActionCommand("btnVolverAgregar");
        this.agregar_peli.btnVolver.addActionListener(this); 
        this.agregar_peli.btn_agregarDrama.setActionCommand("btnAgregaDrama");
        this.agregar_peli.btn_agregarDrama.addActionListener(this);
        this.elim_peli.btn_elim.setActionCommand("btnEliminarPeli");
        this.elim_peli.btn_elim.addActionListener(this);
        this.elim_peli.btn_volver.setActionCommand("btnVolverElimPeli");
        this.elim_peli.btn_volver.addActionListener(this);
        this.buscar_peli.btnBuscar.setActionCommand("btnBuscarPeli");
        this.buscar_peli.btnBuscar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Accion.valueOf(e.getActionCommand())) {
            case btnVistaAgregarPeli:
                this.agregar_peli.setLocationRelativeTo(null);
                this.agregar_peli.setTitle("Ingresar Película");
                this.agregar_peli.setVisible(true);
                ArrayList<Categoria> listaCat = Modelo.Categoria.listarCat();

                for (int i = 0; i < listaCat.size(); i++) {
                    this.agregar_peli.categoria.addItem(listaCat.get(i).getId() + ".- " + listaCat.get(i).getDescripcion());
                }
                this.vistaPrincipal.setVisible(false);
                break;
            case btnVistaElimPeli:
                this.elim_peli.setLocationRelativeTo(null);
                this.elim_peli.setTitle("Eliminar Película");
                this.elim_peli.setVisible(true);
                this.vistaPrincipal.setVisible(false);
                break;
            case btnVistaBuscarPeli:
                this.buscar_peli.setLocationRelativeTo(null);
                this.buscar_peli.setTitle("Buscar Película");
                this.buscar_peli.setVisible(true);
                this.vistaPrincipal.setVisible(false);
                break;
            case btnVistaListarPeli:
                this.listar_peli.setLocationRelativeTo(null);
                this.listar_peli.setTitle("Listar Película");
                this.listar_peli.setVisible(true);
                this.vistaPrincipal.setVisible(false);
                this.listar_peli.tabla_peli.setModel(this.peli.ListadoPeli());
                break;
            case btnVistaAgregarCat:
                this.agregar_cat.setLocationRelativeTo(null);
                this.agregar_cat.setTitle("Agregar Categoría");
                this.agregar_cat.setVisible(true);
                this.vistaPrincipal.setVisible(false);
                break;
            case btnVistaListarCat:
                this.listar_cat.setLocationRelativeTo(null);
                this.listar_cat.setTitle("Agregar Categoría");
                this.listar_cat.setVisible(true);
                this.vistaPrincipal.setVisible(false);
                this.listar_cat.tabla_cat.setModel(this.cat.ListadoCat());
                break;
            case btnSalir:
                this.vistaPrincipal.dispose();
                break;
            case btnGuardarCat:

                if (this.cat.guardarCat(this.agregar_cat.ta_agrega_cat.getText())) {
                    JOptionPane.showMessageDialog(null, "Categoría agregada correctamente");
                    //Limpiamos textField
                    this.agregar_cat.ta_agrega_cat.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo agregar producto");
                }
                break;
            case btnLimpiarCat:
                this.agregar_cat.ta_agrega_cat.setText("");
                break;
            case btnVolverCat:
                this.agregar_cat.setVisible(false);
                this.vistaPrincipal.setVisible(true);
                break;
            case listar_cat_volver:
                this.listar_cat.setVisible(false);
                this.vistaPrincipal.setVisible(true);
                break;
            case btnVolverListarPeli:
                this.listar_peli.setVisible(false);
                this.vistaPrincipal.setVisible(true);
                break;
            case btnVolverElimPeli:
                this.elim_peli.setVisible(false);
                this.vistaPrincipal.setVisible(true);
                break;
            case btnVolverAgregar:
                this.agregar_peli.setVisible(false);
                this.vistaPrincipal.setVisible(true);
                break;
            case btnGuardarPeli:
                //Enviamos datos del formulario Ingresar producto a metodo nuevoProducto
                if (Integer.parseInt(this.agregar_peli.tf_cod.getText()) >= 10000 && Integer.parseInt(this.agregar_peli.tf_cod.getText()) <= 99999) {
                    if (this.agregar_peli.tf_nom.getText().length() >= 3) {
                        if (Integer.parseInt(this.agregar_peli.tf_price.getText()) > 1000) {
                            if (this.peli.buscarPeli(Integer.parseInt(this.agregar_peli.tf_cod.getText()))) {
                                JOptionPane.showMessageDialog(null, "El código de pelicula ya existe!");
                            } else {
                                if (this.peli.guardarPeli(Integer.parseInt(this.agregar_peli.tf_cod.getText()),
                                        Integer.parseInt(this.agregar_peli.tf_price.getText()),
                                        this.agregar_peli.categoria.getSelectedIndex(),
                                        this.agregar_peli.formato.getSelectedItem().toString(),
                                        this.agregar_peli.tf_nom.getText()
                                )) {
                                    JOptionPane.showMessageDialog(null, "Pelicula agregada correctamente");
                                    //Limpiamos textField
                                    this.agregar_peli.tf_cod.setText("");
                                    this.agregar_peli.tf_nom.setText("");
                                    this.agregar_peli.tf_price.setText("");
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se pudo agregar pelicula");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "El precio debe ser mayor a 1000!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El nombre debe tener al menos 3 caracteres!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El código debe ser entre 10000 y 99999!");
                }
                break;
            
            case btnAgregaDrama:
                String cat = "Drama";
                
                int cod;
                cod = Integer.parseInt(this.agregar_peli.tf_cod.getText());
                
                String nom;
                nom = (this.agregar_peli.tf_nom.getText());
                
                int price;
                price = Integer.parseInt(this.agregar_peli.tf_price.getText());
                
                String formato;
                formato = ( this.agregar_peli.formato.getSelectedItem().toString());
                
            break;
                    
            case btnEliminarPeli:
                if (this.peli.buscarPeli(Integer.parseInt(this.elim_peli.tf_elim.getText()))) {
                    if (this.peli.eliminarPeli(Integer.parseInt(this.elim_peli.tf_elim.getText()))) {
                        JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
                        this.elim_peli.tf_elim.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar producto");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El código ingresado no existe!");
                }
                break;
            case btnBuscarPeli:
                if (this.peli.buscarPeli(Integer.parseInt(this.buscar_peli.tf_buscar.getText()))) {
                    int precio = 0;
                    int cat = 0;
                    String formato, nom;
                    int code = Integer.parseInt(this.buscar_peli.tf_buscar.getText());                    
                    
                    precio = this.peli.buscarPorCodigo(code).get(0).getPrecio();
                    cat = this.peli.buscarPorCodigo(code).get(0).getId_categoria();
                    formato = this.peli.buscarPorCodigo(code).get(0).getFormato4k();
                    nom = this.peli.buscarPorCodigo(code).get(0).getFormato4k();
                    String p = Integer.toString(precio);

                    this.buscar_peli.tf_nom.setText(nom);
                    this.buscar_peli.tf_prc.setText(p);
                    this.buscar_peli.jComboBox1.setSelectedItem(cat);
                    this.buscar_peli.jComboBox2.setSelectedItem(formato);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró un registro!");
                }

                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1)//boton izquierdo
        {
            //Muestro datos de producto a modificar
            /*int fila = this.listProd.tbProducto.rowAtPoint(e.getPoint());
             if (fila > -1){
                this.listProd.txtnombre.setText(String.valueOf(this.listProd.tbProducto.getValueAt(fila, 2) ));
                this.listProd.txtfamprod.setText(String.valueOf(this.listProd.tbProducto.getValueAt(fila, 3) ));
                this.listProd.txtdescripcion.setText(String.valueOf(this.listProd.tbProducto.getValueAt(fila, 6) ));
                this.listProd.txtcantidad.setText(String.valueOf(this.listProd.tbProducto.getValueAt(fila, 8) ));
                this.listProd.txtprecio.setText(String.valueOf(this.listProd.tbProducto.getValueAt(fila, 7) ));
                this.listProd.txtcod.setText(String.valueOf(this.listProd.tbProducto.getValueAt(fila, 1) ));
             }*/
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
