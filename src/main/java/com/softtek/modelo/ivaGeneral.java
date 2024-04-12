package com.softtek.modelo;

    public class ivaGeneral implements IImpuesto {
        @Override
        public double calcularImpuesto(Producto producto) {
            return producto.getPrecio() * 0.21;
        }
    }

