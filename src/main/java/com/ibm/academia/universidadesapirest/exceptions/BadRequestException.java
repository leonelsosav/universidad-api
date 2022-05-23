package com.ibm.academia.universidadesapirest.exceptions;

import java.io.Serial;

public class BadRequestException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -7966933439578828041L;

    public BadRequestException(String message)
    {
        super(message);
    }
}
