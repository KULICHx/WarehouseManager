package ru.kulich.warehousemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс приложения Warehouse Manager.
 */
@SpringBootApplication
public class WarehouseManagerApplication {

    /**
     * Точка входа в приложение Warehouse Manager.
     * @param args Аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        SpringApplication.run(WarehouseManagerApplication.class, args);
    }
}
