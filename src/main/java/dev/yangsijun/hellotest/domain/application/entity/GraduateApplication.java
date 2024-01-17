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
public final class GraduateApplication extends AbstractApplication {

  private GraduateApplication(
      @NonNull UUID id,
      @NonNull GraduatePersonalInformation personalInformation,
      @NonNull GraduateMiddleSchoolGrade gradeCard,
      @NonNull AbstractApplicationStatusParameter parameter,
      @NonNull Applicant applicant) {
    super(id, personalInformation, gradeCard, parameter, applicant);
  }
}
