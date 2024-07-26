package com.soc.soar.repository;

import com.soc.soar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepo extends JpaRepository<User,Integer> {

    User findOneByEmailAndPassword(String email, String password);
}
