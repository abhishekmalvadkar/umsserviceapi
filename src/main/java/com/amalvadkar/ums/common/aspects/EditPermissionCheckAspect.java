package com.amalvadkar.ums.common.aspects;

import com.amalvadkar.ums.common.checker.EditPermissionChecker;
import com.amalvadkar.ums.common.model.dto.HeaderConfigAware;
import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static com.amalvadkar.ums.common.aspects.AspectArgumentUtils.findArgument;

@Aspect
@Component
@RequiredArgsConstructor
public class EditPermissionCheckAspect {

    private final EditPermissionChecker editPermissionChecker;

    @Around("@annotation(editPermissionCheck)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, EditPermissionCheck editPermissionCheck) throws Throwable {
        HeaderConfigAware editRequest = findArgument(joinPoint.getArgs(),HeaderConfigAware.class);
        LoggedInUser loggedInUser = findArgument(joinPoint.getArgs(),LoggedInUser.class);
        editPermissionChecker.check(editRequest.headerConfigId(), editPermissionCheck.value().id(),loggedInUser);
        return joinPoint.proceed();
    }
}
