package team.themomnet.hellogsm.core.domain.application.model.param;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import team.themomnet.hellogsm.core.domain.application.model.AbstractMiddleSchoolGrade;
import team.themomnet.hellogsm.core.domain.application.model.AbstractPersonalInformation;
import team.themomnet.hellogsm.core.domain.type.DesiredMajors;
import team.themomnet.hellogsm.core.domain.type.Evaluation;
import team.themomnet.hellogsm.core.domain.type.Major;

@Getter
public class AbstractApplicationParameter {

  protected final Boolean finalSubmitted;

  protected final Boolean printsArrived;

  @Nullable
  protected final Evaluation subjectEvaluation;

  @Nullable
  protected final Evaluation competencyEvaluation;

  @Nullable
  protected final Long registrationNumber;

  protected final DesiredMajors desiredMajors;
  protected final Major finalMajor;

  @Builder
  public AbstractApplicationParameter(
      @NonNull Boolean finalSubmitted,
      @NonNull Boolean printsArrived,
      @Nullable Evaluation subjectEvaluation,
      @Nullable Evaluation competencyEvaluation,
      @Nullable Long registrationNumber,
      @NonNull DesiredMajors desiredMajors,
      @NonNull Major finalMajor) {
    this.finalSubmitted = finalSubmitted;
    this.printsArrived = printsArrived;
    this.subjectEvaluation = subjectEvaluation;
    this.competencyEvaluation = competencyEvaluation;
    this.registrationNumber = registrationNumber;
    this.desiredMajors = desiredMajors;
    this.finalMajor = finalMajor;
  }
}
