package dev.yangsijun.hellotest.domain.application.entity.param;

import dev.yangsijun.hellotest.domain.application.type.DesiredMajors;
import dev.yangsijun.hellotest.domain.application.type.EvaluationResult;
import dev.yangsijun.hellotest.domain.application.type.Major;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record AbstractApplicationStatusParameter(
    @NonNull Boolean finalSubmitted,
    @NonNull Boolean printsArrived,
    @Nullable EvaluationResult subjectEvaluationResult,
    @Nullable EvaluationResult competencyEvaluationResult,
    @Nullable Long registrationNumber,
    @NonNull DesiredMajors desiredMajors,
    @Nullable Major finalMajor) {

  static AbstractApplicationStatusParameter init(DesiredMajors desiredMajors) {
    return AbstractApplicationStatusParameter.builder()
        .finalSubmitted(false)
        .printsArrived(false)
        .subjectEvaluationResult(null)
        .competencyEvaluationResult(null)
        .registrationNumber(null)
        .desiredMajors(desiredMajors)
        .finalMajor(null)
        .build();
  }
}
