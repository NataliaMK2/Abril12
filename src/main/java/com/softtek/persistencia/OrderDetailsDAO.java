package com.softtek.persistencia;
import com.softtek.modelo.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAO {

    private Connection connection;

    public OrderDetailsDAO(Connection connection) {
        this.connection = connection;
    }

    public List<OrderDetail> getAllOrderDetails() throws SQLException {
        List<OrderDetail> orderDetails = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM order_details");

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int productId = resultSet.getInt("product_id");
                float unitPrice = resultSet.getFloat("unit_price");
                int quantity = resultSet.getInt("quantity");
                float discount = resultSet.getFloat("discount");

                orderDetails.add(new OrderDetail(orderId, productId, unitPrice, quantity, discount));
            }
        }

        return orderDetails;
    }
}

