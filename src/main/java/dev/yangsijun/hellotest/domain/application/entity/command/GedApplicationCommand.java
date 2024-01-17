package dev.yangsijun.hellotest.domain.application.entity.command;

import dev.yangsijun.hellotest.domain.application.type.ArtSportScore;
import dev.yangsijun.hellotest.domain.application.type.CurricularScore;
import dev.yangsijun.hellotest.domain.application.type.DesiredMajors;
import dev.yangsijun.hellotest.domain.application.type.GraduationStatus;
import dev.yangsijun.hellotest.domain.application.type.Major;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;


// @JsonSubTypes을 사용한 다형성 -  https://seongtak-yoon.tistory.com/70
public record GedApplicationCommand(
    GedPersonalInformationCommand personalInformationCmd,
    GedMiddleSchoolGradeCommand gradeCmd,
    List<Major> desiredMajors
) implements ApplicationCommand {

  @Override
  public GraduationStatus getGraduationStatus() {
    return this.personalInformationCmd().graduation();
  }

  public record GedPersonalInformationCommand(
      String applicantImageUri,
      String address,
      String detailAddress,
      GraduationStatus graduation,
      String telephone,
      String guardianName,
      String relationWithApplicant
  ) {

  }

  public record GedMiddleSchoolGradeCommand(
      BigDecimal gedTotalScore,
      BigDecimal gedMaxScore
  ) {}
}
