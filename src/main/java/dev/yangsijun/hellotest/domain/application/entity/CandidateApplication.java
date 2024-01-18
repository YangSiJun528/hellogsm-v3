package dev.yangsijun.hellotest.domain.application.entity;


import dev.yangsijun.hellotest.domain.applicant.entity.Applicant;
import dev.yangsijun.hellotest.domain.application.entity.param.AbstractApplicationStatusParameter;
import jakarta.persistence.Entity;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class CandidateApplication extends AbstractApplication {
  public CandidateApplication(
      UUID id,
      @NonNull CandidatePersonalInformation personalInformation,
      @NonNull CandidateMiddleSchoolGrade gradeCard,
      @NonNull AbstractApplicationStatusParameter statusParam,
      @NonNull Applicant applicant) {
    super(id, personalInformation, gradeCard, statusParam, applicant);
  }
}
