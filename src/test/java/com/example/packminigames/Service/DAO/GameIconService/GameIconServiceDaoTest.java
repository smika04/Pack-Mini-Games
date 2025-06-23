package com.example.packminigames.Service.DAO.GameIconService;

import com.example.packminigames.Models.DTO.GameIconDTO;
import com.example.packminigames.Models.Entity.GameIconEntity;
import com.example.packminigames.PackMiniGamesApplication;
import com.example.packminigames.Repository.GameIconRepository.IGameIconRepository;
import com.example.packminigames.Service.DAO.AbstractServiceDaoTest;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest(classes = PackMiniGamesApplication.class)
@ActiveProfiles("test")
@DisplayName("GameIconService CRUD Operations Testing")
public class GameIconServiceDaoTest extends AbstractServiceDaoTest<IGameIconRepository, IGameIconService, GameIconEntity, Long, GameIconDTO>
{
    @Override
    protected GameIconEntity createEntityModel1() {
        return null;
    }

    @Override
    protected GameIconEntity createEntityModel2() {
        return null;
    }

    @Override
    protected Long getEntityId(GameIconEntity entity) {
        return 0L;
    }

    @Override
    protected GameIconEntity createNewEntityForCreation() {
        return null;
    }

    @Override
    protected GameIconEntity createUpdateEntityDetails(GameIconEntity originalEntity) {
        return null;
    }

    @Override
    protected Long getNonExistentEntityId() {
        return 0L;
    }

    @Override
    protected Optional<GameIconEntity> callServiceFindById(Long aLong) {
        return Optional.empty();
    }

    @Override
    protected GameIconEntity callServiceCreate(GameIconEntity entity) {
        return null;
    }

    @Override
    protected GameIconEntity callServiceUpdate(Long aLong, GameIconEntity entityDetails) {
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
