package org.aleks4ay.springbootsecurity.repositories;

import org.aleks4ay.springbootsecurity.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{
    User findByName(String name);
}
