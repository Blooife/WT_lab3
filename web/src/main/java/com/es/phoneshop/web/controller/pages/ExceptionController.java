package com.es.phoneshop.web.controller.pages;

import com.es.core.entity.book.BookNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ModelAndView handleException(BookNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("errorPages/notFoundPhone");
        modelAndView.addObject("errorMessage", exception.getErrorMessage());
        return modelAndView;
    }
}
