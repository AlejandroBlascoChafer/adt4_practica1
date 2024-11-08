package com.alblch.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmpleadoDAO implements InterfaceDAO<Empleado>{
    private final Connection accesoBD;

    public EmpleadoDAO() {
        accesoBD = GestionBD.getConexion();
    }


    @Override
    public int insertar(Empleado emp) {

        int numLineasAfectadas=0;

        try{
            String sql = "INSERT INTO empleados VALUES (?,?,?,?,?);";
            PreparedStatement ps = accesoBD.prepareStatement(sql);
            ps.setString(1, emp.getDni());
            ps.setString(2, emp.getNombre());
            ps.setString(3, emp.getApellidos());
            ps.setInt(4, emp.getEdad());
            ps.setFloat(5, emp.getSalario());

            numLineasAfectadas = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error EmpleadosDAO - insert: " + e.getMessage());
        }
        return numLineasAfectadas;

    }

    @Override
    public int borrar(Empleado emp) {
        int numLineasAfectadas=0;
        try{
            String sql = "DELETE FROM empleados WHERE dni=?;";
            PreparedStatement ps = accesoBD.prepareStatement(sql);
            ps.setString(1, emp.getDni());

            numLineasAfectadas = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error EmpleadosDAO - delete: " + e.getMessage());
        }


        return numLineasAfectadas;
    }

    @Override
    public List<Empleado> listar() {
        List<Empleado> lista = new java.util.ArrayList<>(List.of());
        Empleado empleado;

        try {
            String sql = "SELECT * FROM empleados";
            PreparedStatement ps = accesoBD.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                empleado = new Empleado();
                empleado.setDni(rs.getString(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellidos(rs.getString(3));
                empleado.setEdad(rs.getInt(4));
                empleado.setSalario(rs.getFloat(5));
                lista.add(empleado);
            }
        } catch (SQLException e) {
            System.out.println("Error EmpleadosDAO - select: " + e.getMessage());
        }
        return lista;
    }
}
