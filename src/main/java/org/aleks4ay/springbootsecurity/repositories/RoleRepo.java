package org.aleks4ay.springbootsecurity.repositories;

import org.aleks4ay.springbootsecurity.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long>{
}
