package com.pluralsight.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.conferencedemo.models.Session;

/*
 * Here we have extended JpaRepository<Session, Long>
 * This class is part of spring-boot-starter-data-jpa 
 * We are going to use Session as our data type and 
 * Long refers to primary key.
 */
public interface SessionRepository extends JpaRepository<Session, Long> {

}
