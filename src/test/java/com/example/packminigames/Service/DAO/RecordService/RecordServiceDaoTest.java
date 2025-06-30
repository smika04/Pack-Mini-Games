package com.example.packminigames.Service.DAO.RecordService;

import com.example.packminigames.Mapping.DB.IRecordMapper;
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.RecordEntity;
import com.example.packminigames.Models.Entity.UserEntity;
import com.example.packminigames.Repository.GameRepository.IGameRepository;
import com.example.packminigames.Repository.RecordRepository.IRecordRepository;
import com.example.packminigames.Repository.UserRepository.IUserRepository;
import com.example.packminigames.Service.DAO.AbstractServiceDaoTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("RecordService CRUD Operations Testing")
public class RecordServiceDaoTest extends AbstractServiceDaoTest<IRecordRepository,IRecordService, RecordEntity,Long, RecordDTO> {
    @Mock
    private IRecordMapper recordMapper;

    @Mock
    private IRecordRepository recordRepository;

    @Mock
    private IGameRepository gameRepository;

    @Mock
    private IUserRepository userRepository;


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
                    System.out.println("DEBUG (RecordServiceDaoTest): recordMapper.toEntity викликано з DTO: " + (dto != null ? dto.getId() : "null")); // Додано лог
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

        when(gameRepository.findById(any(Long.class))).thenAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            System.out.println("DEBUG (RecordServiceDaoTest): gameRepository.findById викликано з ID: " + id); // Додано лог
            if (id == 10L) return Optional.of(GameEntity.builder().id(10L).build()); // Для entityModel1
            if (id == 11L) return Optional.of(GameEntity.builder().id(11L).build()); // Для entityModel2
            if (id == 12L) return Optional.of(GameEntity.builder().id(12L).build()); // Для createNewEntityForCreation
            if (id == 13L) return Optional.of(GameEntity.builder().id(13L).build()); // Для createUpdateEntityDetails
            return Optional.empty();
        });

        when(userRepository.findById(any(Long.class))).thenAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            System.out.println("DEBUG (RecordServiceDaoTest): userRepository.findById викликано з ID: " + id);
            if (id == 20L) return Optional.of(UserEntity.builder().id(20L).build());
            if (id == 21L) return Optional.of(UserEntity.builder().id(21L).build());
            if (id == 22L) return Optional.of(UserEntity.builder().id(22L).build());
            if (id == 23L) return Optional.of(UserEntity.builder().id(23L).build());
            return Optional.empty();
        });
    }

    @Override
    protected RecordEntity createEntityModel1()
    {
        return RecordEntity.builder()
                .id(1L)
                .score(100)
                .datePlayed(LocalDateTime.of(2023, 1, 1, 10, 0))
                .game(GameEntity.builder().id(10L).build())
                .user(UserEntity.builder().id(20L).build())
                .build();
    }

    @Override
    protected RecordEntity createEntityModel2()
    {
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
    protected RecordEntity createNewEntityForCreation()
    {
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
    protected Optional<RecordEntity> callServiceFindById(Long id) {
        return service.GetById(id).map(recordMapper::toEntity);
    }

    @Override
    protected RecordEntity callServiceCreate(RecordEntity entity) {
        RecordDTO dto = recordMapper.toDto(entity);
        RecordDTO createdDto = service.Add(dto);
        return recordMapper.toEntity(createdDto);
    }

    @Override
    protected RecordEntity callServiceUpdate(Long id, RecordEntity entityDetails)
    {
        RecordDTO dto = recordMapper.toDto(entityDetails);
        dto.setId(id);
        RecordDTO updatedDto = service.Update(dto);
        return recordMapper.toEntity(updatedDto);
    }

    @Override
    protected void callServiceDelete(Long id) {
        service.Delete(id);
    }

    @Override
    protected boolean callServiceExistsById(Long id) {
        return service.GetById(id).isPresent();
    }
}
