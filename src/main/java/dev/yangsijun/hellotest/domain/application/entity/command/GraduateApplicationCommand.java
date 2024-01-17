package dev.yangsijun.hellotest.domain.application.entity.command;

import dev.yangsijun.hellotest.domain.application.entity.command.GedApplicationCommand.GedMiddleSchoolGradeCommand;
import dev.yangsijun.hellotest.domain.application.type.ArtSportScore;
import dev.yangsijun.hellotest.domain.application.type.CurricularScore;
import dev.yangsijun.hellotest.domain.application.type.DesiredMajors;
import dev.yangsijun.hellotest.domain.application.type.GraduationStatus;
import dev.yangsijun.hellotest.domain.application.type.Major;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;


// @JsonSubTypes을 사용한 다형성 -  https://seongtak-yoon.tistory.com/70
public record GraduateApplicationCommand(
    GraduatePersonalInformationCommand personalInformationCmd,
    GraduateMiddleSchoolGradeCommand gradeCmd,
    List<Major> desiredMajors
) implements ApplicationCommand {

  @Override
  public GraduationStatus getGraduationStatus() {
    return this.personalInformationCmd().graduation();
  }

  public record GraduatePersonalInformationCommand(
      String applicantImageUri,
      String address,
      String detailAddress,
      GraduationStatus graduation,
      String telephone,
      String guardianName,
      String relationWithApplicant,
      String schoolName,
      String schoolLocation,
      String teacherName,
      String teacherPhoneNumber
  ) {

  }

  public record GraduateMiddleSchoolGradeCommand(
      BigDecimal percentileRank,
      BigDecimal attendanceScore,
      BigDecimal volunteerScore
  ) {}
}
