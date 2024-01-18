package dev.yangsijun.hellotest.domain.application.entity.command;

import dev.yangsijun.hellotest.domain.application.type.GraduationStatus;
import dev.yangsijun.hellotest.domain.application.type.Major;
import dev.yangsijun.hellotest.domain.application.type.MiddleSchoolTranscript;
import java.util.List;


// @JsonSubTypes을 사용한 다형성 -  https://seongtak-yoon.tistory.com/70
public record CandidateApplicationCommand(
    CandidatePersonalInformationCommand personalInformationCmd,
    MiddleSchoolTranscript middleSchoolTranscript,
    List<Major> desiredMajors
) implements ApplicationCommand {

  @Override
  public GraduationStatus getGraduationStatus() {
    return this.personalInformationCmd().graduation();
  }

  public record CandidatePersonalInformationCommand(
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
}
