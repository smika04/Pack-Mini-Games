package com.example.packminigames.Service.DAO.RecordService;

import com.example.packminigames.Mapping.DB.IRecordMapper;
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.RecordEntity;
import com.example.packminigames.Models.Entity.UserEntity;
import com.example.packminigames.PackMiniGamesApplication;
import com.example.packminigames.Repository.RecordRepository.IRecordRepository;
import com.example.packminigames.Service.DAO.AbstractServiceDaoTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = PackMiniGamesApplication.class)
@ActiveProfiles("test")
@DisplayName("RecordService CRUD Operations Testing")
public class RecordServiceDaoTest extends AbstractServiceDaoTest<IRecordRepository,IRecordService, RecordEntity,Long, RecordDTO> {
    @Mock
    private IRecordMapper recordMapper;

    @Mock
    private IRecordRepository recordRepository;

    @InjectMocks
    private RecordService_impl recordServiceImpl;

    @BeforeEach
    protected void setUp()
    {
        this.repository = recordRepository;
        super.setUp();
        this.service = recordServiceImpl;

        when(recordMapper.toDto(any(RecordEntity.class)))
                .thenAnswer(invocation -> {
                    RecordEntity entity = invocation.getArgument(0);
                    if (entity == null) return null;
                    return RecordDTO.builder()
                            .id(entity.getId())
                            .score(entity.getScore())
                            .datePlayed(entity.getDatePlayed())
                            .gameId(entity.getGame() != null ? entity.getGame().getId() : null)
                            .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                            .build();
                });

        when(recordMapper.toEntity(any(RecordDTO.class)))
                .thenAnswer(invocation -> {
                    RecordDTO dto = invocation.getArgument(0);
                    if (dto == null) return null;
                    RecordEntity entity = RecordEntity.builder()
                            .id(dto.getId())
                            .score(dto.getScore())
                            .datePlayed(dto.getDatePlayed())
                            .build();
                    if (dto.getGameId() != null) {
                        entity.setGame(GameEntity.builder().id(dto.getGameId()).build());
                    }
                    if (dto.getUserId() != null) {
                        entity.setUser(UserEntity.builder().id(dto.getUserId()).build());
                    }
                    return entity;
                });

        doAnswer(invocation -> {
            RecordDTO dto = invocation.getArgument(0);
            RecordEntity entity = invocation.getArgument(1);
            if (dto.getScore() != 0) entity.setScore(dto.getScore());
            if (dto.getDatePlayed() != null) entity.setDatePlayed(dto.getDatePlayed());
            if (dto.getGameId() != null) {
                if (entity.getGame() == null || !entity.getGame().getId().equals(dto.getGameId())) {
                    entity.setGame(GameEntity.builder().id(dto.getGameId()).build());
                }
            }
            if (dto.getUserId() != null) {
                if (entity.getUser() == null || !entity.getUser().getId().equals(dto.getUserId())) {
                    entity.setUser(UserEntity.builder().id(dto.getUserId()).build());
                }
            }
            return null;
        }).when(recordMapper).updateEntityFromDto(any(RecordDTO.class), any(RecordEntity.class));
    }

    @Override
    protected RecordEntity createEntityModel1() {
        return RecordEntity.builder()
                .id(1L)
                .score(100)
                .datePlayed(LocalDateTime.of(2023, 1, 1, 10, 0))
                .game(GameEntity.builder().id(10L).build()) // Minimal game entity
                .user(UserEntity.builder().id(20L).build()) // Minimal user entity
                .build();
    }

    @Override
    protected RecordEntity createEntityModel2() {
        return RecordEntity.builder()
                .id(2L)
                .score(200)
                .datePlayed(LocalDateTime.of(2023, 1, 2, 11, 0))
                .game(GameEntity.builder().id(11L).build())
                .user(UserEntity.builder().id(21L).build())
                .build();
    }

    @Override
    protected Long getEntityId(RecordEntity entity) {
        return entity.getId();
    }

    @Override
    protected RecordEntity createNewEntityForCreation() {
        return RecordEntity.builder()
                .score(150)
                .datePlayed(LocalDateTime.of(2023, 1, 3, 12, 0))
                .game(GameEntity.builder().id(12L).build())
                .user(UserEntity.builder().id(22L).build())
                .build();
    }

    @Override
    protected RecordEntity createUpdateEntityDetails(RecordEntity originalEntity) {
        RecordEntity updatedDetails = RecordEntity.builder()
                .score(250)
                .datePlayed(LocalDateTime.of(2023, 1, 4, 13, 0))
                .game(GameEntity.builder().id(13L).build())
                .user(UserEntity.builder().id(23L).build())
                .build();
        if (originalEntity != null) {
            updatedDetails.setId(originalEntity.getId());
        }
        return updatedDetails;
    }

    @Override
    protected Long getNonExistentEntityId() {
        return 999L;
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

    /*@Override
    protected RecordDTO createDtoFromEntity(RecordEntity entity) {
        if (entity == null) return null;
        return recordMapper.toDto(entity);
    }

    @Override
    protected RecordEntity createEntityFromDto(RecordDTO dto) {
        if (dto == null) return null;
        return recordMapper.toEntity(dto);
    }

    @Override
    protected RecordDTO createNewDtoForCreation() {
        return RecordDTO.builder()
                .score(180)
                .datePlayed(LocalDateTime.of(2023, 1, 5, 14, 0))
                .gameId(14L)
                .userId(24L)
                .build();
    }

    @Override
    protected RecordDTO createUpdateDtoDetails(RecordDTO originalDto) {
        return RecordDTO.builder()
                .id(originalDto != null ? originalDto.getId() : null)
                .score(300)
                .datePlayed(LocalDateTime.of(2023, 1, 6, 15, 0))
                .gameId(15L)
                .userId(25L)
                .build();
    }

    @Override
    protected Optional<RecordDTO> callServiceFindById(Long id) {
        return service.GetById(id);
    }

    @Override
    protected RecordDTO callServiceCreate(RecordDTO dto) {
        return service.Add(dto);
    }

    @Override
    protected RecordDTO callServiceUpdate(Long id, RecordDTO dto) {
        dto.setId(id); // Встановлюємо ID для операції оновлення DTO
        return service.Update(dto);
    }

    @Override
    protected void callServiceDelete(Long id) {
        service.Delete(id);
    }

    @Override
    protected boolean callServiceExistsById(Long id) {
        return service.GetById(id).isPresent(); // Перевіряємо існування через GetById
    }
*/
}
