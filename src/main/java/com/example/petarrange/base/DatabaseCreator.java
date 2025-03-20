package com.example.petarrange.base;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCreator implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseCreator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
//
//        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS petarrange");
//        jdbcTemplate.execute("USE mypetstore");
//
//        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS user(" +
//                "user_id INT PRIMARY KEY AUTO_INCREMENT," +
//                "username VARCHAR(25) NOT NULL," +
//                "password VARCHAR(50) NOT NULL," +
//                "email VARCHAR(50)," +
//                "address VARCHAR(100)" +
//                ")");
//
//        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS product(" +
//                "product_id INT PRIMARY KEY AUTO_INCREMENT," +
//                "product_name VARCHAR(50) NOT NULL," +
//                "price DECIMAL(10, 2) NOT NULL," +
//                "stock INT NOT NULL" +
//                ")");
//
//
//        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `order`(" +
//                "order_id INT PRIMARY KEY AUTO_INCREMENT," +
//                "user_id INT NOT NULL," +
//                "order_time DATETIME DEFAULT CURRENT_TIMESTAMP," +
//                "total_price DECIMAL(10, 2)," +
//                "status INT DEFAULT 0," +
//                "FOREIGN KEY (user_id) REFERENCES user(user_id)" +
//                ")");
//
//        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS order_item(" +
//                "item_id INT PRIMARY KEY AUTO_INCREMENT," +
//                "order_id INT NOT NULL," +
//                "product_id INT NOT NULL," +
//                "quantity INT NOT NULL," +
//                "price DECIMAL(10, 2) NOT NULL," +
//                "FOREIGN KEY (order_id) REFERENCES `order`(order_id)," +
//                "FOREIGN KEY (product_id) REFERENCES product(product_id)" +
//                ")");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS admin(" +
                "admin_id INT PRIMARY KEY AUTO_INCREMENT," +
                "admin_name VARCHAR(25) NOT NULL," +
                "password VARCHAR(50) NOT NULL" +
                ")");

        jdbcTemplate.execute(
                "create table if not exists item_order " +
                        "(" +
                        "    id    int  auto_increment primary key ," +
                        "    item_id   varchar(255)          null," +
                        "    user_name   varchar(255)          null," +
                        "    code     varchar(255) null," +
                        "    add_time  datetime     null," +
                        "    total    varchar(255) null," +
                        "    is_delete int          null," +
                        "    status   int          null," +
                        "    check (`status` in (0, 1, 2, 3, 4))" +
                        ")");

        jdbcTemplate.execute("ALTER TABLE signon MODIFY password VARCHAR(512);");


    }
}
