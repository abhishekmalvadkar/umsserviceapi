package com.amalvadkar.ums.common.exceptions;

public class PermissionDeniedException extends UmsException {
    public PermissionDeniedException() {
        super("You don't have permission for this operation");
    }
}
