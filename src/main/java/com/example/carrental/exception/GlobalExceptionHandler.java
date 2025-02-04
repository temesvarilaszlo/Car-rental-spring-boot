package com.example.carrental.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(basePackages = "com.example.carrental.controller")
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                            RedirectAttributes redirectAttributes,
                                            HttpServletRequest request) {
        // Collect validation errors
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        // Add errors and input data to redirect attributes
        if (!exception.getBindingResult().getGlobalErrors().isEmpty()){
            errors.put("otherError", exception.getBindingResult().getGlobalError().getDefaultMessage());
        }
        redirectAttributes.addFlashAttribute("errors", errors);

        preserveOldFormState(redirectAttributes, request);

        // Redirect back to the previous page
        String referer = request.getHeader("Referer");
        return referer != null ? "redirect:" + referer : "redirect:/"; // Fallback to home if referer is null
    }

    @ExceptionHandler(CustomValidationException.class)
    public String handleCustomValidationException(CustomValidationException exception,
                                                  RedirectAttributes redirectAttributes,
                                                  HttpServletRequest request){
        // Collect validation errors
        Map<String, String> errors = exception.getErrors();

        redirectAttributes.addFlashAttribute("errors", errors);

        preserveOldFormState(redirectAttributes, request);

        // Redirect back to the previous page
        String referer = request.getHeader("Referer");
        return referer != null ? "redirect:" + referer : "redirect:/";
    }

    private void preserveOldFormState(RedirectAttributes redirectAttributes, HttpServletRequest request){
        Map<String, String> oldInput = new HashMap<>();
        request.getParameterMap().forEach((key, values) -> {
            if (values.length > 0) {
                oldInput.put(key, values[0]);
            }
            redirectAttributes.addFlashAttribute("oldInput", oldInput);
        });
    }
}
