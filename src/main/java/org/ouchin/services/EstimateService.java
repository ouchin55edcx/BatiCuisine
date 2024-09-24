package org.ouchin.services;

import org.ouchin.models.Estimate;
import org.ouchin.repositories.EstimateRepository;

import java.time.LocalDate;
import java.util.UUID;

public class EstimateService {
    private final EstimateRepository estimateRepository;

    public EstimateService(EstimateRepository estimateRepository) {
        this.estimateRepository = estimateRepository;
    }

    public void saveEstimateWithTotal(UUID projectId, float totalAmount, LocalDate issueDate, LocalDate validityDate, boolean isAccepted) {
        Estimate estimate = new Estimate(
                UUID.randomUUID(),
                totalAmount,
                issueDate,
                validityDate,
                isAccepted,
                projectId
        );

        estimateRepository.save(estimate);
    }

    public Estimate getEstimateByProjectId(UUID projectId) {
        return estimateRepository.findByProjectId(projectId);
    }

    public void updateEstimateAcceptance(UUID projectId, boolean isAccepted) {
        Estimate estimate = estimateRepository.findByProjectId(projectId);
        if (estimate != null) {
            estimate.setAccepted(isAccepted);
            estimateRepository.update(estimate);
        } else {
            System.out.println("No estimate found for the project.");
        }
    }
}
