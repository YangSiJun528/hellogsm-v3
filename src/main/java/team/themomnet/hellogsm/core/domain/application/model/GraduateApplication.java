package team.themomnet.hellogsm.core.domain.application.model;

import lombok.NonNull;
import team.themomnet.hellogsm.core.domain.application.model.param.AbstractApplicationParameter;
import team.themomnet.hellogsm.core.domain.application.model.param.AbstractPersonalInformationParameter;

import java.math.BigDecimal;

public final class GraduateApplication extends AbstractApplication {

  private GraduateApplication(AbstractApplicationParameter parameter) {
    super(parameter);
  }

  public static final class GraduatePersonalInformation extends AbstractPersonalInformation {

    private final String schoolName;

    private final String schoolLocation;

    private final String teacherName;

    private final String teacherPhoneNumber;

    GraduatePersonalInformation(
        @NonNull AbstractPersonalInformationParameter superParameter,
        @NonNull String schoolName,
        @NonNull String schoolLocation,
        @NonNull String teacherName,
        @NonNull String teacherPhoneNumber) {
      super(superParameter);
      this.schoolName = schoolName;
      this.schoolLocation = schoolLocation;
      this.teacherName = teacherName;
      this.teacherPhoneNumber = teacherPhoneNumber;
    }
  }

  public static final class GraduateMiddleSchoolGrade extends AbstractMiddleSchoolGrade {

    private final BigDecimal attendanceScore; // 출석 점수

    private final BigDecimal volunteerScore; // 봉사 점수

    public GraduateMiddleSchoolGrade(
        @NonNull BigDecimal percentileRank,
        @NonNull BigDecimal attendanceScore,
        @NonNull BigDecimal volunteerScore) {
      super(percentileRank);
      this.attendanceScore = attendanceScore;
      this.volunteerScore = volunteerScore;
    }
  }
}
