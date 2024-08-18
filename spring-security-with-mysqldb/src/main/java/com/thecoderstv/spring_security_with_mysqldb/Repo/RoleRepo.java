package com.thecoderstv.spring_security_with_mysqldb.Repo;

import com.thecoderstv.spring_security_with_mysqldb.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
}
