package com.softtek.presentacion;

import com.softtek.modelo.Factura;
import com.softtek.modelo.Producto;
import com.softtek.modelo.ivaGeneral;
import com.softtek.modelo.ivaSuperReducido;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Camiseta", 19.99));
        productos.add(new Producto("Pantalon", 25.95));

        Factura facturaIvaGeneral = new Factura(productos, new ivaGeneral());
        System.out.println("Total factura IVA General: " + facturaIvaGeneral.calcularTotalFactura());

        Factura facturaIvaSuperReducido = new Factura(productos, new ivaSuperReducido());
        System.out.println("Total factura IVA SuperReducido: " + facturaIvaSuperReducido.calcularTotalFactura());
    }
}