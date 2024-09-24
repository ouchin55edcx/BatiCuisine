package org.ouchin.repositories;

import org.ouchin.models.Estimate;

import java.util.UUID;

public interface EstimateRepository {
    void save(Estimate estimate);
    Estimate findByProjectId(UUID projectId);
    void update(Estimate estimate);


}
