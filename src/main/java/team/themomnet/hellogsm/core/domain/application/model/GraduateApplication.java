package team.themomnet.hellogsm.core.domain.application.model;

import jakarta.annotation.Nullable;
import team.themomnet.hellogsm.core.domain.type.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class GraduateApplication extends AbstractApplication {

  private GraduateApplication(GraduateApplication.GraduatePersonalInformation personalInformation,
      GraduateMiddleSchoolGrade gradeCard, Boolean finalSubmitted,
      Boolean printsArrived, @Nullable Evaluation subjectEvaluation,
      @Nullable Evaluation competencyEvaluation, @Nullable Long registrationNumber,
      DesiredMajors desiredMajors, Major finalMajor) {
    super(personalInformation, gradeCard, finalSubmitted, printsArrived,
        subjectEvaluation, competencyEvaluation, registrationNumber, desiredMajors, finalMajor);
  }

  public static final class GraduatePersonalInformation extends AbstractPersonalInformation {

    private final String schoolName;

    private final String schoolLocation;

    private final String teacherName;

    private final String teacherPhoneNumber;

    private GraduatePersonalInformation(String applicantImageUri, String applicantName,
        Gender applicantGender, LocalDate applicantBirth, String address, String detailAddress,
        GraduationStatus graduation, String telephone, String applicantPhoneNumber,
        String guardianName, String relationWithApplicant, String schoolName, String schoolLocation,
        String teacherName, String teacherPhoneNumber) {
      super(applicantImageUri, applicantName, applicantGender, applicantBirth, address,
          detailAddress, graduation, telephone, applicantPhoneNumber, guardianName,
          relationWithApplicant);
      this.schoolName = schoolName;
      this.schoolLocation = schoolLocation;
      this.teacherName = teacherName;
      this.teacherPhoneNumber = teacherPhoneNumber;
    }

  }

  public static final class GraduateMiddleSchoolGrade extends AbstractMiddleSchoolGrade {

    private final BigDecimal attendanceScore; // 출석 점수

    private final BigDecimal volunteerScore; // 봉사 점수

    public GraduateMiddleSchoolGrade(BigDecimal percentileRank, BigDecimal attendanceScore,
        BigDecimal volunteerScore) {
      super(percentileRank);
      this.attendanceScore = attendanceScore;
      this.volunteerScore = volunteerScore;
    }

  }

}
