package com.alblch.controlador;

import com.alblch.modelo.Empleado;
import com.alblch.modelo.EmpleadoDAO;
import com.alblch.vista.JFEmpleados;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorEmpleado implements ActionListener {
    public EmpleadoDAO modelo;
    public JFEmpleados vista;

    public ControladorEmpleado(EmpleadoDAO modelo, JFEmpleados vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.btnInsertar.addActionListener(this);
        this.vista.btnBorrar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);

        inicializarVista();
    }

    private void inicializarVista() {
        limpiarElementos();
        llenarTabla(vista.jtEmpleados);
    }

    private void limpiarElementos() {
        vista.txtDni.setText("");
        vista.txtNombre.setText("");
        vista.txtApellidos.setText("");
        vista.txtEdad.setText("");
        vista.txtSalario.setText("");
        vista.btnInsertar.setEnabled(true);
        vista.btnBorrar.setEnabled(true);
        vista.btnListar.setEnabled(true);
    }

    private void llenarTabla(JTable tablaEmpleados) {
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaEmpleados.setModel(modeloT);

        modeloT.addColumn("DNI");
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Apellidos");
        modeloT.addColumn("Edad");
        modeloT.addColumn("Salario");
        Object [] columna = new Object[5];
        List<Empleado> lista = modelo.listar();
        int numeroRegistros = lista.size();

        for (int i = 0; i < numeroRegistros; i++) {
            Empleado e = lista.get(i);
            columna[0] = e.getDni();
            columna[1] = e.getNombre();
            columna[2] = e.getApellidos();
            columna[3] = e.getEdad();
            columna[4] = e.getSalario();
            modeloT.addRow(columna);
        }

        tablaEmpleados.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaEmpleados.getTableHeader().setReorderingAllowed(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnInsertar){
            String dni = vista.txtDni.getText();
            String nombre = vista.txtNombre.getText();
            String apellidos = vista.txtApellidos.getText();
            int edad = Integer.parseInt(vista.txtEdad.getText());
            float salario = Float.parseFloat(vista.txtSalario.getText());

            Empleado empleado = new Empleado(dni, nombre, apellidos, edad, salario);

            int numeroRegistros = 0;

            numeroRegistros = modelo.insertar(empleado);

            if (numeroRegistros != 0){
                JOptionPane.showMessageDialog(null, "Número de empleados registrados: " + numeroRegistros);
            } else {
                JOptionPane.showMessageDialog(null, "Error, no se han guardado los datos");
            }

            limpiarElementos();
            llenarTabla(vista.jtEmpleados);

        }
        if (e.getSource() == vista.btnBorrar){
            String dni = vista.txtDni.getText();
            Empleado empleado = new Empleado();
            empleado.setDni(dni);

            int numeroRegistros = 0;
            numeroRegistros = modelo.borrar(empleado);

            if (numeroRegistros != 0){
                JOptionPane.showMessageDialog(null, "Número de empleados eliminados: " + numeroRegistros);
            } else {
                JOptionPane.showMessageDialog(null, "Error, no se han borrado los datos");
            }

            llenarTabla(vista.jtEmpleados);
        }
        if (e.getSource() == vista.btnListar){
            limpiarElementos();
            llenarTabla(vista.jtEmpleados);
        }
    }
}
