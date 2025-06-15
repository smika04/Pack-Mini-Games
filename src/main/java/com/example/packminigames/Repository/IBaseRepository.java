package com.example.packminigames.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.Optional;

@NoRepositoryBean
public interface IBaseRepository<E, ID> extends JpaRepository<E, ID>
{

}
