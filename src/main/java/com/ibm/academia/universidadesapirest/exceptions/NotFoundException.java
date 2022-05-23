package com.ibm.academia.universidadesapirest.exceptions;

import java.io.Serial;

public class NotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 8707921255078290811L;

    public NotFoundException(String message){
        super(message);
    }
}
