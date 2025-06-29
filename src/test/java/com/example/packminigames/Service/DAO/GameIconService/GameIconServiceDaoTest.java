package com.example.packminigames.Service.DAO.GameIconService;

import com.example.packminigames.Mapping.DB.IGameIconMapper;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.DTO.GameIconDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.GameIconEntity;
import com.example.packminigames.Models.Entity.TypeGameEntity;
import com.example.packminigames.Repository.GameIconRepository.IGameIconRepository;
import com.example.packminigames.Repository.GameRepository.IGameRepository;
import com.example.packminigames.Service.DAO.AbstractServiceDaoTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@DisplayName("GameIconService CRUD Operations Testing")
@MockitoSettings(strictness = Strictness.LENIENT)
public class GameIconServiceDaoTest extends AbstractServiceDaoTest<IGameIconRepository, IGameIconService, GameIconEntity, Long, GameIconDTO>
{
    @Mock
    private IGameIconMapper gameIconMapper;

    @Mock
    private IGameIconRepository gameIconRepository;

    @Mock
    private IGameRepository gameRepository;

    private GameEntity gameModel1Instance;
    private GameEntity gameModel2Instance;


    @InjectMocks
    private GameIconService_impl gameIconServiceImpl;

    @BeforeEach
    protected void setUp()
    {
        this.repository = gameIconRepository;

        super.setUp();

        this.service = gameIconServiceImpl;

        gameModel1Instance = getGameEntityModel1();
        gameModel2Instance = getGameEntityModel2();

        when(gameRepository.findById(gameModel1Instance.getId())).thenReturn(Optional.of(gameModel1Instance));
        when(gameRepository.findById(gameModel2Instance.getId())).thenReturn(Optional.of(gameModel2Instance));
        when(gameRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        when(gameIconMapper.toDto(any(GameIconEntity.class)))
                .thenAnswer(invocation ->
                {
                    GameIconEntity entity = invocation.getArgument(0);
                    if (entity == null) return null;
                    return GameIconDTO.builder()
                            .id(entity.getId())
                            .name(entity.getName())
                            .iconData(entity.getIconData())
                            .contentType(entity.getContentType())
                            .gameId(entity.getGame() != null ? entity.getGame().getId() : null).build();
                });

        when(gameIconMapper.toEntity(any(GameIconDTO.class)))
                .thenAnswer(invocation ->
                {
                    GameIconDTO dto = invocation.getArgument(0);
                    if (dto == null) return null;
                    GameIconEntity entity = GameIconEntity.builder()
                            .id(dto.getId())
                            .name(dto.getName())
                            .iconData(dto.getIconData())
                            .contentType(dto.getContentType())
                            .build();
                    if (dto.getGameId() != null) {
                        if (dto.getGameId().equals(gameModel1Instance.getId())) {
                            entity.setGame(gameModel1Instance);
                        } else if (dto.getGameId().equals(gameModel2Instance.getId())) {
                            entity.setGame(gameModel2Instance);
                        } else {
                            entity.setGame(GameEntity.builder().id(dto.getGameId()).build());
                        }
                    }
                    return entity;
                });

        doAnswer(invocation -> {
            Long idArg = invocation.getArgument(0);
            if (idArg.equals(gameModel1Instance.getId())) {
                return Optional.of(gameModel1Instance);
            } else if (idArg.equals(gameModel2Instance.getId())) {
                return Optional.of(gameModel2Instance);
            } else {
                return Optional.empty();
            }
        }).when(gameRepository).findById(any(Long.class));
    }

    @Override
    protected GameIconEntity createEntityModel1()
    {
        return GameIconEntity.builder()
                .id(1L)
                .name("test_icon")
                .iconData(new byte[]{0x01, 0x02, 0x03})
                .contentType("image/png")
                .game(getGameEntityModel1())
                .build();
    }

    @Override
    protected GameIconEntity createEntityModel2()
    {
        return GameIconEntity.builder()
                .id(2L)
                .name("test_icon")
                .iconData(new byte[]{0x01, 0x02, 0x03})
                .contentType("image/png")
                .game(getGameEntityModel2())
                .build();
    }

    private GameEntity getGameEntityModel1()
    {
        return   GameEntity.builder()
                .id(1L)
                .title("The Witcher 3")
                .description("An open-world action RPG.")
                .typeGame(TypeGameEntity.builder().id(1L).name("FPS").build())
                .records(Collections.emptyList())
                .build();
    }

    private GameEntity getGameEntityModel2()
    {
        return   GameEntity.builder()
                .id(2L)
                .title("Cossack 3")
                .description("An open-world RTS.")
                .typeGame(TypeGameEntity.builder().id(2L).name("RTS").build())
                .records(Collections.emptyList())
                .build();
    }

    @Override
    protected Long getEntityId(GameIconEntity entity) {
        return 0L;
    }

    @Override
    protected GameIconEntity createNewEntityForCreation() {
        return GameIconEntity.builder()
                .name("new_icon")
                .iconData(new byte[]{0x04, 0x05})
                .contentType("image/jpeg")
                .game(getGameEntityModel1())
                .build();
    }

    @Override
    protected GameIconEntity createUpdateEntityDetails(GameIconEntity originalEntity) {
        if (originalEntity != null) {
            return GameIconEntity.builder()
                    .id(originalEntity.getId())
                    .name("updated_" + originalEntity.getName())
                    .iconData(originalEntity.getIconData())
                    .contentType(originalEntity.getContentType())
                    .game(originalEntity.getGame())
                    .build();
        }
        return GameIconEntity.builder()
                .id(99L)
                .name("nonexistent_update_icon")
                .iconData(new byte[]{0x06})
                .contentType("image/gif")
                .build();
    }

    @Override
    protected Long getNonExistentEntityId() {
        return 99L; // Забезпечте, щоб це ID дійсно не існувало
    }

    @Override
    protected Optional<GameIconEntity> callServiceFindById(Long id) {
        return service.GetById(id).map(gameIconMapper::toEntity);
    }

    @Override
    protected GameIconEntity callServiceCreate(GameIconEntity entity) {
        GameIconDTO inputDto = gameIconMapper.toDto(entity);
        GameIconDTO createdDto = service.Add(inputDto);
        return gameIconMapper.toEntity(createdDto);
    }

    @Override
    protected GameIconEntity callServiceUpdate(Long id, GameIconEntity entityDetails) {
        GameIconDTO updateDto = gameIconMapper.toDto(entityDetails);
        updateDto.setId(id);
        GameIconDTO updatedDto = service.Update(updateDto);
        return gameIconMapper.toEntity(updatedDto);
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