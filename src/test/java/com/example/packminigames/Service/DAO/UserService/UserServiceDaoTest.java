package com.example.packminigames.Service.DAO.UserService;

import com.example.packminigames.Mapping.DB.IUserMapper;
import com.example.packminigames.Models.DTO.UserDTO;
import com.example.packminigames.Models.Entity.UserEntity;
import com.example.packminigames.Repository.UserRepository.IUserRepository;
import com.example.packminigames.Service.DAO.AbstractServiceDaoTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@DisplayName("UserService CRUD Operations Testing")
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceDaoTest extends AbstractServiceDaoTest<IUserRepository,IUserService, UserEntity,Long,UserDTO>
{
    @Mock
    private IUserMapper userMapper;

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService_impl userServiceImpl;

    @BeforeEach
    @Override
    protected void setUp()
    {
        super.setUp();

        this.repository = userRepository;
        this.service = userServiceImpl;


        when(userMapper.toDto(any(UserEntity.class)))
                .thenAnswer(invocation ->
                {
                    UserEntity entity = invocation.getArgument(0);
                    if (entity == null) return null;
                    return UserDTO.builder()
                            .id(entity.getId())
                            .username(entity.getUsername())
                            .birthday(entity.getBirthday()).build();
                });

        when(userMapper.toEntity(any(UserDTO.class)))
                .thenAnswer(invocation ->
                {
                    UserDTO dto = invocation.getArgument(0);
                    if (dto == null) return null;
                    return UserEntity.builder()
                            .id(dto.getId())
                            .username(dto.getUsername())
                            .birthday(dto.getBirthday()).build();
                });

        doAnswer(invocation ->
        {
            UserDTO dto = invocation.getArgument(0);
            UserEntity entity = invocation.getArgument(1);
            entity.setUsername(dto.getUsername());
            entity.setBirthday(dto.getBirthday());

            return null;
        }).when(userMapper).updateEntityFromDto(any(UserDTO.class), any(UserEntity.class));
    }


    @Override
    protected UserEntity createEntityModel1()
    {
        return UserEntity.builder()
                .id(1L)
                .username("john_doe")
                .birthday(LocalDate.of(1990, 1, 1)).build();
    }

    @Override
    protected UserEntity createEntityModel2()
    {
        return UserEntity.builder()
                .id(2L)
                .username("jane_smith")
                .birthday(LocalDate.of(1992, 2, 2)).build();
    }

    @Override
    protected Long getEntityId(UserEntity entity) {return entity.getId();}

    @Override
    protected UserEntity createNewEntityForCreation()
    {
        return UserEntity.builder()
                .username("new_user")
                .birthday(LocalDate.of(2000, 3, 3)).build();
    }

    @Override
    protected UserEntity createUpdateEntityDetails(UserEntity originalEntity) {
        if (originalEntity != null)
        {
            return UserEntity.builder()
                    .id(originalEntity.getId())
                    .username("updated_" + originalEntity.getUsername())
                    .birthday(LocalDate.of(originalEntity.getBirthday().getYear() + 1,
                            originalEntity.getBirthday().getMonth(),
                            originalEntity.getBirthday().getDayOfMonth())).build();
        }
        return UserEntity.builder()
                .id(99L)
                .username("nonexistent_update")
                .birthday(LocalDate.of(2000, 1, 1)).build();
    }

    @Override
    protected Long getNonExistentEntityId() {return 99L;}

    @Override
    protected Optional<UserEntity> callServiceFindById(Long id) {
        return service.GetById(id).map(userMapper::toEntity);
    }

    @Override
    protected UserEntity callServiceCreate(UserEntity entity)
    {
        UserDTO inputDto = userMapper.toDto(entity);
        UserDTO createdDto = service.Add(inputDto);
        return userMapper.toEntity(createdDto);
    }

    @Override
    protected UserEntity callServiceUpdate(Long id, UserEntity entityDetails)
    {
        UserDTO updateDto = userMapper.toDto(entityDetails);
        updateDto.setId(id);

        UserDTO updatedDto = service.Update(updateDto);
        return userMapper.toEntity(updatedDto);
    }

    @Override
    protected void callServiceDelete(Long id) {service.Delete(id);}

    @Override
    protected boolean callServiceExistsById(Long aLong) {return true;}
}
