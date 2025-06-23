package com.example.packminigames.Service.DAO.TypeGameService;

import com.example.packminigames.Models.DTO.TypeGameDTO;
import com.example.packminigames.Models.Entity.TypeGameEntity;
import com.example.packminigames.PackMiniGamesApplication;
import com.example.packminigames.Repository.TypeGameRepository.ITypeGameRepository;
import com.example.packminigames.Service.DAO.AbstractServiceDaoTest;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest(classes = PackMiniGamesApplication.class)
@ActiveProfiles("test")
@DisplayName("TypeGameService CRUD Operations Testing")
public class TypeGameServiceDaoTest extends AbstractServiceDaoTest<ITypeGameRepository,ITypeGameService, TypeGameEntity,Long, TypeGameDTO>
{
    @Override
    protected TypeGameEntity createEntityModel1() {
        return null;
    }

    @Override
    protected TypeGameEntity createEntityModel2() {
        return null;
    }

    @Override
    protected Long getEntityId(TypeGameEntity entity) {
        return 0L;
    }

    @Override
    protected TypeGameEntity createNewEntityForCreation() {
        return null;
    }

    @Override
    protected TypeGameEntity createUpdateEntityDetails(TypeGameEntity originalEntity) {
        return null;
    }

    @Override
    protected Long getNonExistentEntityId() {
        return 0L;
    }

    @Override
    protected Optional<TypeGameEntity> callServiceFindById(Long aLong) {
        return Optional.empty();
    }

    @Override
    protected TypeGameEntity callServiceCreate(TypeGameEntity entity) {
        return null;
    }

    @Override
    protected TypeGameEntity callServiceUpdate(Long aLong, TypeGameEntity entityDetails) {
        return null;
    }

    @Override
    protected void callServiceDelete(Long aLong) {

    }

    @Override
    protected boolean callServiceExistsById(Long aLong) {
        return false;
    }
}
