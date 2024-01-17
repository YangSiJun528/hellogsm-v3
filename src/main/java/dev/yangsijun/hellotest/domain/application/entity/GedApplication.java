package dev.yangsijun.hellotest.domain.application.entity;

import dev.yangsijun.hellotest.domain.applicant.entity.Applicant;
import dev.yangsijun.hellotest.domain.application.entity.param.AbstractApplicationStatusParameter;
import dev.yangsijun.hellotest.domain.application.entity.param.AbstractPersonalInformationParameter;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class GedApplication extends AbstractApplication {

  private GedApplication(
      @NonNull UUID id,
      @NonNull GedPersonalInformation personalInformation,
      @NonNull GedMiddleSchoolGrade gradeCard,
      @NonNull AbstractApplicationStatusParameter parameter,
      @NonNull Applicant applicant) {
    super(id, personalInformation, gradeCard, parameter, applicant);
  }
}
