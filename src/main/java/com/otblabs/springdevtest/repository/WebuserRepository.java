package com.otblabs.springdevtest.repository;

import java.util.Optional;

import com.otblabs.springdevtest.model.Webuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface WebuserRepository extends JpaRepository<Webuser, Long>{
	Webuser findByUsername(String username);
    
	Optional<Webuser> findByEmployeeId(String employeeId);
	

}
