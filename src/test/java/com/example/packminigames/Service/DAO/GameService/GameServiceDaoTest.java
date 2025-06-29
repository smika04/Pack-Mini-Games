package com.example.packminigames.Service.DAO.GameService;

import com.example.packminigames.Mapping.DB.IGameMapper;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.GameIconEntity;
import com.example.packminigames.Models.Entity.TypeGameEntity;
import com.example.packminigames.PackMiniGamesApplication;
import com.example.packminigames.Repository.GameRepository.IGameRepository;
import com.example.packminigames.Service.DAO.AbstractServiceDaoTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = PackMiniGamesApplication.class)
@ActiveProfiles("test")
@DisplayName("GameService CRUD Operations Testing")
public class GameServiceDaoTest extends AbstractServiceDaoTest<IGameRepository,IGameService, GameEntity,Long, GameDTO> {
    @Mock
    private IGameMapper gameMapper;

    @Mock
    private IGameRepository gameRepository;

    @InjectMocks
    private GameService_impl gameServiceImpl;

    @BeforeEach
    protected void setUp() {
        this.repository = gameRepository;
        super.setUp();
        this.service = gameServiceImpl;

        when(gameMapper.toDto(any(GameEntity.class)))
                .thenAnswer(invocation -> {
                    GameEntity entity = invocation.getArgument(0);
                    if (entity == null) return null;
                    return GameDTO.builder()
                            .id(entity.getId())
                            .title(entity.getTitle())
                            .description(entity.getDescription())
                            .typeGameName(String.valueOf(entity.getTypeGame() != null ? entity.getTypeGame().getId() : null))
                            .build();
                });

        when(gameMapper.toEntity(any(GameDTO.class)))
                .thenAnswer(invocation -> {
                    GameDTO dto = invocation.getArgument(0);
                    if (dto == null) return null;
                    GameEntity entity = GameEntity.builder()
                            .id(dto.getId())
                            .title(dto.getTitle())
                            .description(dto.getDescription())
                            .build();
                    if (dto.getTypeGameName() != null) {
                        entity.setTypeGame(TypeGameEntity.builder().id(Long.valueOf(dto.getTypeGameName())).build());
                    }
                    return entity;
                });

        doAnswer(invocation -> {
            GameDTO dto = invocation.getArgument(0);
            GameEntity entity = invocation.getArgument(1);
            if (dto.getTitle() != null) entity.setTitle(dto.getTitle());
            if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
            if (dto.getTypeGameName() != null) {
                entity.setTypeGame(TypeGameEntity.builder().id(Long.valueOf(dto.getTypeGameName())).build());
            }
            return null;
        }).when(gameMapper).updateEntityFromDto(any(GameDTO.class), any(GameEntity.class));
    }

    @Override
    protected GameEntity createEntityModel1() {
        return GameEntity.builder()
                .id(1L)
                .title("Game Title 1")
                .description("Description for Game 1")
                .typeGame(TypeGameEntity.builder().id(10L).build()) // Minimal related entity
                .gameIcon(GameIconEntity.builder().id(20L).build()) // Minimal related entity
                .build();
    }

    @Override
    protected GameEntity createEntityModel2() {
        return GameEntity.builder()
                .id(2L)
                .title("Game Title 2")
                .description("Description for Game 2")
                .typeGame(TypeGameEntity.builder().id(11L).build())
                .gameIcon(GameIconEntity.builder().id(21L).build())
                .build();
    }

    @Override
    protected Long getEntityId(GameEntity entity) {
        return entity.getId();
    }

    @Override
    protected GameEntity createNewEntityForCreation() {
        return GameEntity.builder()
                .title("New Game Title")
                .description("Description for a new game to be created.")
                .typeGame(TypeGameEntity.builder().id(12L).build())
                .gameIcon(GameIconEntity.builder().id(22L).build())
                .build();
    }

    @Override
    protected GameEntity createUpdateEntityDetails(GameEntity originalEntity) {
        GameEntity updatedDetails = GameEntity.builder()
                .title("Updated Game Title")
                .description("Updated description.")
                .typeGame(TypeGameEntity.builder().id(13L).build())
                .gameIcon(GameIconEntity.builder().id(23L).build())
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
    protected Optional<GameEntity> callServiceFindById(Long id) {
        // Отримуємо DTO з сервісу та перетворюємо його на Entity
        return service.GetById(id).map(gameMapper::toEntity);
    }

    @Override
    protected GameEntity callServiceCreate(GameEntity entity) {
        // Перетворюємо Entity на DTO, викликаємо сервіс, потім перетворюємо результат DTO на Entity
        GameDTO dto = gameMapper.toDto(entity);
        GameDTO createdDto = service.Add(dto);
        return gameMapper.toEntity(createdDto);
    }

    @Override
    protected GameEntity callServiceUpdate(Long id, GameEntity entityDetails) {
        // Перетворюємо Entity на DTO, встановлюємо ID для оновлення, викликаємо сервіс, потім перетворюємо результат DTO на Entity
        GameDTO dto = gameMapper.toDto(entityDetails);
        dto.setId(id); // Встановлюємо ID для операції оновлення DTO
        GameDTO updatedDto = service.Update(dto);
        return gameMapper.toEntity(updatedDto);
    }

    @Override
    protected void callServiceDelete(Long id) {
        service.Delete(id);
    }

    @Override
    protected boolean callServiceExistsById(Long id) {
        return service.GetById(id).isPresent(); // Перевіряємо існування через GetById
    }
}
  /*  @Override
    protected GameDTO createDtoFromEntity(GameEntity entity) {
        if (entity == null) return null;
        return gameMapper.toDto(entity);
    }

    @Override
    protected GameEntity createEntityFromDto(GameDTO dto) {
        if (dto == null) return null;
        return gameMapper.toEntity(dto);
    }

    @Override
    protected GameDTO createNewDtoForCreation() {
        return GameDTO.builder()
                .title("New Game DTO")
                .description("Description for a new game DTO.")
                .typeGameId(14L)
                .gameIconId(24L)
                .build();
    }

    @Override
    protected GameDTO createUpdateDtoDetails(GameDTO originalDto) {
        return GameDTO.builder()
                .id(originalDto != null ? originalDto.getId() : null)
                .title("Updated DTO Title")
                .description("Updated DTO description.")
                .typeGameId(15L)
                .gameIconId(25L)
                .build();
    }

    @Override
    protected Optional<GameDTO> callServiceFindById(Long id) {
        return service.GetById(id);
    }

    @Override
    protected GameDTO callServiceCreate(GameDTO dto) {
        return service.Add(dto);
    }

    @Override
    protected GameDTO callServiceUpdate(Long id, GameDTO dto) {
        dto.setId(id); // Встановлюємо ID для операції оновлення DTO
        return service.Update(dto);
    }
*/