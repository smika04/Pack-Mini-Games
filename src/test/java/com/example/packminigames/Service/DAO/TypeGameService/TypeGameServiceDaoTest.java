package com.example.packminigames.Service.DAO.TypeGameService;

import com.example.packminigames.Mapping.DB.ITypeGameMapper;
import com.example.packminigames.Models.DTO.TypeGameDTO;
import com.example.packminigames.Models.Entity.TypeGameEntity;
import com.example.packminigames.Repository.TypeGameRepository.ITypeGameRepository;
import com.example.packminigames.Service.DAO.AbstractServiceDaoTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@DisplayName("TypeGameService CRUD Operations Testing")
@MockitoSettings(strictness = Strictness.LENIENT)
public class TypeGameServiceDaoTest extends AbstractServiceDaoTest<ITypeGameRepository,ITypeGameService, TypeGameEntity,Long, TypeGameDTO>
{
    @Mock
    private ITypeGameMapper typeGameMapper;

    @Mock
    private ITypeGameRepository typeGameRepository;

    @InjectMocks
    private TypeGameService_impl typeGameServiceImpl;

    @BeforeEach
    @Override
    protected void setUp()
    {
        super.setUp();

        this.repository = typeGameRepository;
        this.service = typeGameServiceImpl;

        when(typeGameMapper.toDto(any(TypeGameEntity.class)))
                .thenAnswer(invocation -> {
                    TypeGameEntity entity = invocation.getArgument(0);
                    if (entity == null) return null;
                    return TypeGameDTO.builder()
                            .id(entity.getId())
                            .name(entity.getName()).build();
                });

        when(typeGameMapper.toEntity(any(TypeGameDTO.class)))
                .thenAnswer(invocation -> {
                    TypeGameDTO dto = invocation.getArgument(0);
                    if (dto == null) return null;
                    return TypeGameEntity.builder()
                            .id(dto.getId())
                            .name(dto.getName()).build();
                });

        doAnswer(invocation -> {
            TypeGameDTO dto = invocation.getArgument(0);
            TypeGameEntity entity = invocation.getArgument(1);
            if (dto.getName() != null) entity.setName(dto.getName());
            return null;
        }).when(typeGameMapper).updateEntityFromDto(any(TypeGameDTO.class), any(TypeGameEntity.class));
    }

    @Override
    protected TypeGameEntity createEntityModel1() {return TypeGameEntity.builder().id(1L).name("FPS").build();}

    @Override
    protected TypeGameEntity createEntityModel2() {return TypeGameEntity.builder().id(2L).name("RTS").build();}

    @Override
    protected Long getEntityId(TypeGameEntity entity) {return entity.getId();}

    @Override
    protected TypeGameEntity createNewEntityForCreation() {return TypeGameEntity.builder().id(2L).name("new_type").build();}

    @Override
    protected TypeGameEntity createUpdateEntityDetails(TypeGameEntity originalEntity)
    {
        if (originalEntity != null)
        {
            return TypeGameEntity.builder().
                    id(originalEntity.getId())
                    .name("updated_" + originalEntity.getName()).build();
        }
        return TypeGameEntity.builder().id(99L).name("nonexistent_update").build();
    }

    @Override
    protected Long getNonExistentEntityId() {return 99L;}

    @Override
    protected Optional<TypeGameEntity> callServiceFindById(Long aLong) {
        return service.GetById(aLong).map(typeGameMapper::toEntity);
    }

    @Override
    protected TypeGameEntity callServiceCreate(TypeGameEntity entity)
    {
        TypeGameDTO inputDto = typeGameMapper.toDto(entity);
        TypeGameDTO createdDto = service.Add(inputDto);
        return typeGameMapper.toEntity(createdDto);
    }

    @Override
    protected TypeGameEntity callServiceUpdate(Long aLong, TypeGameEntity entityDetails)
    {
        TypeGameDTO updateDto = typeGameMapper.toDto(entityDetails);
        updateDto.setId(aLong);

        TypeGameDTO updatedDto = service.Update(updateDto);
        return typeGameMapper.toEntity(updatedDto);
    }

    @Override
    protected void callServiceDelete(Long aLong) {service.Delete(aLong);}

    @Override
    protected boolean callServiceExistsById(Long aLong) {return true;}
}






