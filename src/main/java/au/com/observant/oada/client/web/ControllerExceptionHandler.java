/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * General exception handling methods for the controllers.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Exception handler for {@link AccessDeniedException}.
     * Maps {@link AccessDeniedException} to HTTP response 401.
     *
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(AccessDeniedException.class)
    public void exceptionHandler(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    }
}
