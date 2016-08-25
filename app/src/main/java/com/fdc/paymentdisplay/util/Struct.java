package com.fdc.paymentdisplay.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Designates a class as following the "struct" pattern.
 * TODO rules (only used as parameter naming or for simple data models)
 * Test comment for clear case
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Struct {

}
