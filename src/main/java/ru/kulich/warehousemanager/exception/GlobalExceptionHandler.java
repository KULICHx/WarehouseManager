package ru.kulich.warehousemanager.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Глобальный обработчик исключений для обработки исключений, возникающих во всех контроллерах.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обработчик исключений типа Exception.
     * @param e Исключение, которое необходимо обработать.
     * @return Сообщение об ошибке.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleException(Exception e) {
        return "An error occurred: " + e.getMessage();
    }
}
