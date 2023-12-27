package team.themomnet.hellogsm.core.domain.application.model;

import lombok.NonNull;
import team.themomnet.hellogsm.core.domain.application.model.param.AbstractApplicationParameter;
import team.themomnet.hellogsm.core.domain.application.model.param.AbstractPersonalInformationParameter;

import java.math.BigDecimal;

public final class GedApplication extends AbstractApplication {

  private GedApplication(@NonNull AbstractApplicationParameter parameter) {
    super(parameter);
  }

  public static final class GedPersonalInformation extends AbstractPersonalInformation {

    // 추가로 필요한 정보 없음
    private GedPersonalInformation(@NonNull AbstractPersonalInformationParameter superParameter) {
      super(superParameter);
    }
  }

  public static final class GedMiddleSchoolGrade extends AbstractMiddleSchoolGrade {

    private final BigDecimal gedTotalScore; // GED 시험 총점

    private final BigDecimal gedMaxScore; // GED 시험 만점 - 과목수 * 100

    public GedMiddleSchoolGrade(
        @NonNull BigDecimal percentileRank,
        @NonNull BigDecimal gedTotalScore,
        @NonNull BigDecimal gedMaxScore) {
      super(percentileRank);
      this.gedTotalScore = gedTotalScore;
      this.gedMaxScore = gedMaxScore;
    }
  }
}
