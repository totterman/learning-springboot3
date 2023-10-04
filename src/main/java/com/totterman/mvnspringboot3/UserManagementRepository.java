package com.totterman.mvnspringboot3;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository
extends JpaRepository<UserAccount, Long> {
    
}
