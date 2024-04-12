package com.softtek.presentacion;

import com.softtek.modelo.OrderDetail;
import com.softtek.persistencia.Conexion;
import com.softtek.persistencia.OrderDetailsDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.Scanner;

public class PruebaOrderDetails {

    public static void main(String[] args) {
        try {
            Conexion conexion = new Conexion();
            conexion.abriConexion();
            Connection connection = conexion.getMiConexion();

            OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO(connection);

            List<OrderDetail> allOrderDetails = orderDetailsDAO.getAllOrderDetails();

            Scanner scanner = new Scanner(System.in);
            int opcion;
            do {
                System.out.println("\nMenú:");
                System.out.println("1. Mostrar detalles de órdenes con precio unitario mayor que 30");
                System.out.println("2. Ordenar los productos de forma descendente por unit_price");
                System.out.println("3. Mostrar detalle de orden con precio mínimo");
                System.out.println("4. Obtener las estadísticas de detalle de ordenes sobre el atributo quantity");
                System.out.println("5. Mostrar productos y suma de cantidades");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        List<OrderDetail> orderDetailsPriceGreaterThan30 = allOrderDetails.stream()
                                .filter(orderDetail -> orderDetail.getUnitPrice() > 30)
                                .toList();
                        System.out.println("\nDetalles de orden con precio unitario mayor que 30:");
                        orderDetailsPriceGreaterThan30.forEach(System.out::println);
                        break;
                    case 2:
                        List<OrderDetail> sortedOrderDetailsDescending = allOrderDetails.stream()
                                .sorted((o1, o2) -> Float.compare(o2.getUnitPrice(), o1.getUnitPrice()))
                                .toList();
                        System.out.println("\nDetalles de orden ordenados descendente por precio unitario:");
                        sortedOrderDetailsDescending.forEach(System.out::println);
                        break;
                    case 3:
                        OrderDetail minPriceOrderDetail = allOrderDetails.stream()
                                .min(Comparator.comparing(OrderDetail::getUnitPrice))
                                .orElse(null);
                        System.out.println("\nDetalle de orden con precio mínimo:");
                        System.out.println(minPriceOrderDetail);
                        break;
                    case 4:
                        IntSummaryStatistics quantityStats = allOrderDetails.stream()
                                .mapToInt(OrderDetail::getQuantity)
                                .summaryStatistics();
                        System.out.println("\nEstadísticas de cantidad:");
                        System.out.println("Cantidad total: " + quantityStats.getCount());
                        System.out.println("Cantidad mínima: " + quantityStats.getMin());
                        System.out.println("Cantidad máxima: " + quantityStats.getMax());
                        System.out.println("Suma de cantidades: " + quantityStats.getSum());
                        System.out.println("Promedio de cantidades: " + quantityStats.getAverage());
                        break;
                    case 5:
                        Map<Integer, Integer> productQuantitySum = allOrderDetails.stream()
                                .collect(Collectors.groupingBy(OrderDetail::getProductId,
                                        Collectors.summingInt(OrderDetail::getQuantity)));
                        System.out.println("\nProductos y suma de cantidades:");
                        productQuantitySum.forEach((productId, sum) ->
                                System.out.println("Producto ID: " + productId + ", Suma de cantidades: " + sum));
                        break;
                    case 0:
                        System.out.println("Salir del programa");
                        break;
                    default:
                        System.out.println("Opción no válida. Seleccione otra opción");
                }
            } while (opcion != 0);

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
