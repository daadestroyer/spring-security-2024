package com.thecoderstv.spring_security_with_mysqldb.repo;

import com.thecoderstv.spring_security_with_mysqldb.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {

}
