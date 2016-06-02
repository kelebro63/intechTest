package com.kelebro63.intechtest.utils;

import com.kelebro63.intechtest.models.ErrorModel;

import java.io.IOException;


/*
 * Created by kelebro63 on 02.06.2016
 */
public class RetrofitUtils {
    public static int getErrorCode(Throwable throwable) {
        RetrofitException error = (RetrofitException) throwable;
        if (error.getResponse() == null)
            return -1;
        return (error.getResponse().code());
    }

    public static ErrorModel getError(Throwable throwable) throws IOException {
        RetrofitException error = (RetrofitException) throwable;
        if (error.getResponse() == null)
            return null;
        return ((ErrorModel) error.getErrorBodyAs(ErrorModel.class));
    }
}
