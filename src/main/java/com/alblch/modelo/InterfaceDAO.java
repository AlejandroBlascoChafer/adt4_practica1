package com.alblch.modelo;

import java.util.List;

public interface InterfaceDAO<T> {
    int insertar(T obj);
    int borrar(T obj);
    List<T> listar();
}
