package com.example.packminigames.Controllers;

import com.example.packminigames.Service.DAO.IBasicServiceDAO;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public abstract class AbstractDAOServiceMockTest<DTO, SERVICE extends IBasicServiceDAO<DTO>>
{
    protected abstract SERVICE getService();

    protected abstract DTO createDTO(Long id, String identifier);

    // --- Методи для налаштування поведінки мок-сервісу (when) ---

    protected void setupGetAll(List<DTO> dtos) {
        when(getService().GetAll()).thenReturn(dtos);
    }

    protected void setupGetById(Long id, Optional<DTO> dto) {
        when(getService().GetById(id)).thenReturn(dto);
    }

    protected void setupAdd(DTO createdDto) {
        when(getService().Add(any())).thenReturn(createdDto);
    }

    protected void setupUpdate(DTO updatedDto) {
        when(getService().Update(any())).thenReturn(updatedDto);
    }

    protected void setupUpdateThrows(RuntimeException exception) {
        when(getService().Update(any())).thenThrow(exception);
    }

    protected void setupDelete(Long id) {
        doNothing().when(getService()).Delete(id);
    }

    protected void setupDeleteThrows(Long id, RuntimeException exception) {
        doThrow(exception).when(getService()).Delete(id);
    }

    // --- Методи для перевірки викликів мок-сервісу (verify) ---

    protected void verifyGetAllCalled() {
        verify(getService(), times(1)).GetAll();
    }

    protected void verifyGetByIdCalled(Long id) {
        verify(getService(), times(1)).GetById(id);
    }

    protected void verifyAddCalled() {
        verify(getService(), times(1)).Add(any());
    }

    protected void verifyUpdateCalled() {
        verify(getService(), times(1)).Update(any());
    }

    protected void verifyDeleteCalled(Long id) {
        verify(getService(), times(1)).Delete(id);
    }
}
