package com.amalvadkar.ums.common.updater;

import com.amalvadkar.ums.common.helpers.JpaHelper;
import com.amalvadkar.ums.common.model.dto.EntityUpdateInput;
import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@RequiredArgsConstructor
public class GenericUpdater {

    private final JpaHelper jpaHelper;
    private final EntityManager em;

    public void update(EntityUpdateInput entityUpdateInput) {
        Class<?> entityClass = jpaHelper.findEntityClass(entityUpdateInput.tableName());
        Object entity = em.find(entityClass, entityUpdateInput.id());
        Field field = jpaHelper.findFieldByColumnName(entityClass, entityUpdateInput.columnName());
        updateField(entityUpdateInput.value(), field, entity, entityUpdateInput.loggedInUser());
    }

    private void updateField(String value, Field field, Object entity, LoggedInUser loggedInUser) {
        try {
            field.set(entity, jpaHelper.convert(value, field.getType()));
            updateAuditFields(entity, loggedInUser);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateAuditFields(Object entity, LoggedInUser loggedInUser) throws IllegalAccessException {
        Field lastUpdatedBy = jpaHelper.findFieldByColumnName(entity.getClass(), "last_updated_by");
        lastUpdatedBy.set(entity, loggedInUser.userId());
    }


}
