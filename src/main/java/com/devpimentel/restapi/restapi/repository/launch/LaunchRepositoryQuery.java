package com.devpimentel.restapi.restapi.repository.launch;

import com.devpimentel.restapi.restapi.model.Launch;
import com.devpimentel.restapi.restapi.repository.filter.LaunchFilter;
import com.devpimentel.restapi.restapi.repository.projection.ReleaseSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LaunchRepositoryQuery {

    public Page<Launch> filter(LaunchFilter launchFilter, Pageable pageable);
    public Page<ReleaseSummary> sumUp(LaunchFilter launchFilter, Pageable pageable);
}
