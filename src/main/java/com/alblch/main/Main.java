package com.alblch.main;

import com.alblch.controlador.ControladorEmpleado;
import com.alblch.modelo.EmpleadoDAO;
import com.alblch.vista.JFEmpleados;

public class Main {
    public static void main(String[] args) {
        JFEmpleados vista = new JFEmpleados();
        EmpleadoDAO modelo = new EmpleadoDAO();
        ControladorEmpleado ce = new ControladorEmpleado(modelo, vista);

        vista.setLocationRelativeTo(null);
        vista.setVisible(true);


    }
}