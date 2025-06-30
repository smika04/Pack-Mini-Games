package com.example.packminigames.Service.DAO;

import jakarta.persistence.EntityNotFoundException; // Важливо: імпортуйте правильний виняток
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DisplayName("Abstract Service CRUD Operations Testing")
public abstract class AbstractServiceDaoTest<R extends JpaRepository<E, ID>,S extends IBasicServiceDAO<D>, E, ID, D>
{
    protected R repository;
    protected S service;
    
    protected E entityModel1 ,entityModel2;
    protected ID entityId1 ,entityId2;

    @BeforeEach
    protected void setUp()
    {
        entityModel1 = createEntityModel1();
        entityModel2 = createEntityModel2();
        entityId1 = getEntityId(entityModel1);
        entityId2 = getEntityId(entityModel2);
    }

    // --- Абстрактні методи, які повинні бути реалізовані в конкретних тестових класах ---

    protected abstract E createEntityModel1();
    protected abstract E createEntityModel2();
    protected abstract ID getEntityId(E entity);
    protected abstract E createNewEntityForCreation();
    protected abstract E createUpdateEntityDetails(E originalEntity);
    protected abstract ID getNonExistentEntityId();

    // --- Абстрактні методи для виклику конкретних методів сервісу (CRUD) ---

    protected abstract Optional<E> callServiceFindById(ID id);
    protected abstract E callServiceCreate(E entity);
    protected abstract E callServiceUpdate(ID id, E entityDetails);
    protected abstract void callServiceDelete(ID id);

    protected abstract boolean callServiceExistsById(ID id);


    // --- Абстрактні методи для мокування поведінки репозиторію (Mockito) ---

    protected void mockRepositoryFindById(ID id, Optional<E> result) {
        when(repository.findById(id)).thenReturn(result);
    }

    protected void mockRepositorySave(E inputEntity, E result) {
        when(repository.save(inputEntity)).thenReturn(result);
    }

    protected void mockRepositoryDeleteById(ID id) {
        doNothing().when(repository).deleteById(id);
    }

    protected void mockRepositoryExistsById(ID id, boolean exists) {
        when(repository.existsById(id)).thenReturn(exists);
    }


    @Test
    @DisplayName("Should retrieve an entity by ID successfully")
    void testGetEntityById_Success() {
        mockRepositoryFindById(entityId1, Optional.of(entityModel1));

        Optional<E> foundEntity = callServiceFindById(entityId1);

        assertTrue(foundEntity.isPresent(), "Entity should be present");
        assertEquals(entityModel1, foundEntity.get(), "Found entity should match the expected entity");
        verify(repository, times(1)).findById(entityId1);
    }

    @Test
    @DisplayName("Should return empty Optional if entity ID not found")
    void testGetEntityById_NotFound() {
        ID nonExistentId = getNonExistentEntityId();
        mockRepositoryFindById(nonExistentId, Optional.empty());

        Optional<E> foundEntity = callServiceFindById(nonExistentId);

        assertFalse(foundEntity.isPresent(), "Entity should not be present");
        verify(repository, times(1)).findById(nonExistentId);
    }

    @Test
    @DisplayName("Should create a new entity successfully")
    void testCreateEntity_Success()
    {
        E newEntity = createNewEntityForCreation();
        E expectedSavedEntity = entityModel1;

        mockRepositorySave(newEntity, expectedSavedEntity);

        E createdEntity = callServiceCreate(newEntity);

        assertNotNull(createdEntity, "Created entity should not be null");
        assertEquals(expectedSavedEntity, createdEntity, "Created entity should match the expected saved entity");
        verify(repository, times(1)).save(newEntity);
    }

    @Test
    @DisplayName("Should update an existing entity successfully")
    void testUpdateEntity_Success() {
        E updateDetails = createUpdateEntityDetails(entityModel1);
        E expectedUpdatedEntity = entityModel1;

        mockRepositoryFindById(entityId1, Optional.of(entityModel1));
        mockRepositorySave(entityModel1, expectedUpdatedEntity);

        E updatedEntity = callServiceUpdate(entityId1, updateDetails);

        assertNotNull(updatedEntity, "Updated entity should not be null");
        assertEquals(expectedUpdatedEntity, updatedEntity, "Updated entity should match the expected updated entity");
        verify(repository, times(1)).findById(entityId1);
        verify(repository, times(1)).save(entityModel1);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when updating a non-existent entity")
    void testUpdateEntity_NotFound() {
        ID nonExistentId = getNonExistentEntityId();
        E updateDetails = createUpdateEntityDetails(null);

        mockRepositoryFindById(nonExistentId, Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> callServiceUpdate(nonExistentId, updateDetails));
        verify(repository, times(1)).findById(nonExistentId);
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete an existing entity successfully")
    void testDeleteEntity_Success() {
        ID idToDelete = entityId1;

        mockRepositoryExistsById(idToDelete, true);
        mockRepositoryDeleteById(idToDelete);

        callServiceDelete(idToDelete);

        verify(repository, times(1)).deleteById(idToDelete);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when deleting a non-existent entity")
    void testDeleteEntity_NotFound() {
        ID nonExistentId = getNonExistentEntityId();

        // Для тестування методу delete сервісу, ми мокуємо його залежність - репозиторій.
        mockRepositoryExistsById(nonExistentId, false);

        assertThrows(RuntimeException.class, () -> callServiceDelete(nonExistentId));

        verify(repository, times(1)).existsById(nonExistentId);
        verify(repository, never()).deleteById(any());
    }
}