package com.example.packminigames.Service.DAO.GameService;

import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.PackMiniGamesApplication;
import com.example.packminigames.Repository.GameRepository.IGameRepository;
import com.example.packminigames.Service.DAO.AbstractServiceDaoTest;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest(classes = PackMiniGamesApplication.class)
@ActiveProfiles("test")
@DisplayName("GameService CRUD Operations Testing")
public class GameServiceDaoTest extends AbstractServiceDaoTest<IGameRepository,IGameService, GameEntity,Long, GameDTO> {
    @Override
    protected GameEntity createEntityModel1() {
        return null;
    }

    @Override
    protected GameEntity createEntityModel2() {
        return null;
    }

    @Override
    protected Long getEntityId(GameEntity entity) {
        return 0L;
    }

    @Override
    protected GameEntity createNewEntityForCreation() {
        return null;
    }

    @Override
    protected GameEntity createUpdateEntityDetails(GameEntity originalEntity) {
        return null;
    }

    @Override
    protected Long getNonExistentEntityId() {
        return 0L;
    }

    @Override
    protected Optional<GameEntity> callServiceFindById(Long aLong) {
        return Optional.empty();
    }

    @Override
    protected GameEntity callServiceCreate(GameEntity entity) {
        return null;
    }

    @Override
    protected GameEntity callServiceUpdate(Long aLong, GameEntity entityDetails) {
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
