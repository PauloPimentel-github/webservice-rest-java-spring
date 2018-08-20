package com.devpimentel.restapi.restapi.repository;

import com.devpimentel.restapi.restapi.model.Launch;
import com.devpimentel.restapi.restapi.repository.launch.LaunchRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaunchRepository extends JpaRepository<Launch, Long>, LaunchRepositoryQuery {
}
