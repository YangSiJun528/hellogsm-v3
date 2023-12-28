package team.themomnet.hellogsm.core.domain.application.model.command;

import jakarta.annotation.Nullable;
import java.time.LocalDate;
import team.themomnet.hellogsm.core.domain.type.DesiredMajors;
import team.themomnet.hellogsm.core.domain.type.Evaluation;
import team.themomnet.hellogsm.core.domain.type.Gender;
import team.themomnet.hellogsm.core.domain.type.GraduationStatus;
import team.themomnet.hellogsm.core.domain.type.Major;


// @JsonSubTypes을 사용한 다형성 -  https://seongtak-yoon.tistory.com/70
public record CandidateApplicationCommand(
    PersonalInformation personalInformation,
    MiddleSchoolTranscript middleSchoolTranscript,
    Boolean finalSubmitted,
    Boolean printsArrived,
    @Nullable Evaluation subjectEvaluation,
    @Nullable Evaluation competencyEvaluation,
    @Nullable Long registrationNumber,
    DesiredMajors desiredMajors,
    Major finalMajor

) implements ApplicationCommand {

  @Override
  public GraduationStatus getGraduationStatus() {
    return this.personalInformation().graduation();
  }

  public record PersonalInformation(
      String applicantImageUri,
      String applicantName,
      Gender applicantGender,
      LocalDate applicantBirth,
      String address,
      String detailAddress,
      GraduationStatus graduation,
      String telephone,
      String applicantPhoneNumber,
      String guardianName,
      String relationWithApplicant,
      String schoolName,
      String schoolLocation,
      String teacherName,
      String teacherPhoneNumber
  ) {

  }

  public record MiddleSchoolTranscript(
      //TODO 채우기
  ) {

  }
}
