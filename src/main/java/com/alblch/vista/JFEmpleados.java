package com.alblch.vista;

import javax.swing.*;

public class JFEmpleados extends JFrame{
    public JPanel pnPrincipal;
    private JPanel pnDerecha;
    private JPanel pnIzquierda;
    private JPanel pnSuperior;
    private JPanel pnInferior;
    public JTextField txtDni;
    public JTextField txtNombre;
    public JTextField txtApellidos;
    public JTextField txtEdad;
    public JTextField txtSalario;
    public JButton btnInsertar;
    public JButton btnListar;
    public JButton btnBorrar;
    public JTable jtEmpleados;
    private JScrollPane spTabla;

    public JFEmpleados() {
        setContentPane(pnPrincipal);
        setTitle("ADT4 - Practica1");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
