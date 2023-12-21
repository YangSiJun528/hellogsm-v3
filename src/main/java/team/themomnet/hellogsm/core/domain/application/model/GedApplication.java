package team.themomnet.hellogsm.core.domain.application.model;

import jakarta.annotation.Nullable;
import team.themomnet.hellogsm.core.domain.type.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class GedApplication extends AbstractApplication {

  private GedApplication(GedPersonalInformation personalInformation, GedMiddleSchoolGrade gradeCard,
      Boolean finalSubmitted,
      Boolean printsArrived, @Nullable Evaluation subjectEvaluation,
      @Nullable Evaluation competencyEvaluation, @Nullable Long registrationNumber,
      DesiredMajors desiredMajors, Major finalMajor) {
    super(personalInformation, gradeCard, finalSubmitted, printsArrived,
        subjectEvaluation, competencyEvaluation, registrationNumber, desiredMajors, finalMajor);
  }

  public static final class GedPersonalInformation extends AbstractPersonalInformation {
    // 추가로 필요한 정보 없음


    private GedPersonalInformation(String applicantImageUri, String applicantName,
        Gender applicantGender, LocalDate applicantBirth, String address, String detailAddress,
        GraduationStatus graduation, String telephone, String applicantPhoneNumber,
        String guardianName, String relationWithApplicant) {
      super(applicantImageUri, applicantName, applicantGender, applicantBirth, address,
          detailAddress, graduation, telephone, applicantPhoneNumber, guardianName,
          relationWithApplicant);
    }

  }

  public static final class GedMiddleSchoolGrade extends AbstractMiddleSchoolGrade {

    private final BigDecimal gedTotalScore; // GED 시험 총점

    private final BigDecimal gedMaxScore; // GED 시험 만점 - 과목수 * 100

    public GedMiddleSchoolGrade(BigDecimal percentileRank, BigDecimal gedTotalScore,
        BigDecimal gedMaxScore) {
      super(percentileRank);
      this.gedTotalScore = gedTotalScore;
      this.gedMaxScore = gedMaxScore;
    }

  }

}
