package com.soc.soar.repository;

import com.soc.soar.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepo extends JpaRepository<UserData, Long> {

    Optional<UserData> findByUserName(String username);

}
