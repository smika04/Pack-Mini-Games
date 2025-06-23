package com.example.packminigames.Service.DAO.RecordService;

import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Models.Entity.RecordEntity;
import com.example.packminigames.PackMiniGamesApplication;
import com.example.packminigames.Repository.RecordRepository.IRecordRepository;
import com.example.packminigames.Service.DAO.AbstractServiceDaoTest;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest(classes = PackMiniGamesApplication.class)
@ActiveProfiles("test")
@DisplayName("RecordService CRUD Operations Testing")
public class RecordServiceDaoTest extends AbstractServiceDaoTest<IRecordRepository,IRecordService, RecordEntity,Long, RecordDTO> {
    @Override
    protected RecordEntity createEntityModel1() {
        return null;
    }

    @Override
    protected RecordEntity createEntityModel2() {
        return null;
    }

    @Override
    protected Long getEntityId(RecordEntity entity) {
        return 0L;
    }

    @Override
    protected RecordEntity createNewEntityForCreation() {
        return null;
    }

    @Override
    protected RecordEntity createUpdateEntityDetails(RecordEntity originalEntity) {
        return null;
    }

    @Override
    protected Long getNonExistentEntityId() {
        return 0L;
    }

    @Override
    protected Optional<RecordEntity> callServiceFindById(Long aLong) {
        return Optional.empty();
    }

    @Override
    protected RecordEntity callServiceCreate(RecordEntity entity) {
        return null;
    }

    @Override
    protected RecordEntity callServiceUpdate(Long aLong, RecordEntity entityDetails) {
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
