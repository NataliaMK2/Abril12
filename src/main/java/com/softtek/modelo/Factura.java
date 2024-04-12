package com.softtek.modelo;

import java.util.List;

public class Factura {
    private List<Producto> productos;
    private final IImpuesto impuesto;

    public Factura(List<Producto> productos, IImpuesto impuesto) {
        this.productos = productos;
        this.impuesto = impuesto;
    }

    public double calcularTotalFactura() {
        double total = 0.0;
        for (Producto producto : productos) {
            total += producto.getPrecio() + impuesto.calcularImpuesto(producto);
        }
        return total;
    }
}
