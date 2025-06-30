package com.example.packminigames.Service.DAO.GameService;

import com.example.packminigames.Mapping.DB.IGameMapper;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.GameIconEntity;
import com.example.packminigames.Models.Entity.TypeGameEntity;
import com.example.packminigames.Repository.GameRepository.IGameRepository;
import com.example.packminigames.Service.DAO.AbstractServiceDaoTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@DisplayName("GameService CRUD Operations Testing")
@MockitoSettings(strictness = Strictness.LENIENT)
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
                    GameDTO.GameDTOBuilder dtoBuilder = GameDTO.builder()
                            .id(entity.getId())
                            .title(entity.getTitle())
                            .description(entity.getDescription());

                    if (entity.getTypeGame() != null) {
                        dtoBuilder.typeGameName(String.valueOf(entity.getTypeGame().getId()));
                    }

                    if (entity.getGameIcon() != null) {
                        dtoBuilder.iconData(new byte[]{1});
                    }

                    return dtoBuilder.build();
                });

        when(gameMapper.toEntity(any(GameDTO.class)))
                .thenAnswer(invocation -> {
                    GameDTO dto = invocation.getArgument(0);
                    if (dto == null) return null;
                    GameEntity.GameEntityBuilder entityBuilder = GameEntity.builder()
                            .id(dto.getId())
                            .title(dto.getTitle())
                            .description(dto.getDescription());

                    if (dto.getTypeGameName() != null) {
                        entityBuilder.typeGame(TypeGameEntity.builder().id(Long.valueOf(dto.getTypeGameName())).build());
                    }

                    if (dto.getIconData() != null) {
                        Long gameIconId = null;
                        if (dto.getId() != null) {
                            if (dto.getId().equals(1L)) gameIconId = 20L;
                            else if (dto.getId().equals(2L)) gameIconId = 21L;
                        }
                        else if (dto.getTitle() != null && "New Game Title".equals(dto.getTitle())) {
                            gameIconId = 22L;
                        } else if (dto.getTitle() != null && "Updated Game Title".equals(dto.getTitle())) {
                            gameIconId = 23L;
                        }
                        entityBuilder.gameIcon(GameIconEntity.builder().id(Objects.requireNonNullElse(gameIconId, -1L)).build());
                    }

                    return entityBuilder.build();
                });

        doAnswer(invocation -> {
            Long idArg = invocation.getArgument(0);
            System.out.println("DEBUG: gameRepository.findById() викликано з ID: " + idArg);

            if (idArg.equals(entityModel1.getId())) {
                System.out.println("DEBUG: Знайдено співпадіння з entityModel1. Повертаємо Optional.of(" + entityModel1.getId() + ").");
                return Optional.of(entityModel1);
            } else if (idArg.equals(entityModel2.getId())) {
                System.out.println("DEBUG: Знайдено співпадіння з entityModel2. Повертаємо Optional.of(" + entityModel2.getId() + ").");
                return Optional.of(entityModel2);
            } else {
                System.out.println("DEBUG: Немає конкретного співпадіння ID. Повертаємо Optional.empty().");
                return Optional.empty();
            }
        }).when(gameRepository).findById(any(Long.class));
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