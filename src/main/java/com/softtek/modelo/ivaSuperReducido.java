package com.softtek.modelo;

public class ivaSuperReducido implements IImpuesto {
    @Override
    public double calcularImpuesto(Producto producto) {
        return producto.getPrecio() * 0.1;
    }
}