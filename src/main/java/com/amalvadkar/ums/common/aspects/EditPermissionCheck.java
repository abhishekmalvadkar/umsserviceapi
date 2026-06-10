package com.amalvadkar.ums.common.aspects;

import com.amalvadkar.ums.common.enums.MenuEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EditPermissionCheck {
    MenuEnum value();
}
