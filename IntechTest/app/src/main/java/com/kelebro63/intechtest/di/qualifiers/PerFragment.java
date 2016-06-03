package com.kelebro63.intechtest.di.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by kelebro63 on 02.06.2016
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
