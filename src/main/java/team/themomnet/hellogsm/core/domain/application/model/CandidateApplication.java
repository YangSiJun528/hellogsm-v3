package team.themomnet.hellogsm.core.domain.application.model;


import jakarta.annotation.Nullable;
import team.themomnet.hellogsm.core.domain.type.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class CandidateApplication extends AbstractApplication {

  private CandidateApplication(CandidatePersonalInformation personalInformation,
      CandidateMiddleSchoolGrade gradeCard,
      Boolean finalSubmitted, Boolean printsArrived, @Nullable Evaluation subjectEvaluation,
      @Nullable Evaluation competencyEvaluation, @Nullable Long registrationNumber,
      DesiredMajors desiredMajors, Major finalMajor) {
    super(personalInformation, gradeCard, finalSubmitted, printsArrived,
        subjectEvaluation, competencyEvaluation, registrationNumber, desiredMajors, finalMajor);
  }

  public static final class CandidatePersonalInformation extends AbstractPersonalInformation {

    private final String schoolName;

    private final String schoolLocation;

    private final String teacherName;

    private final String teacherPhoneNumber;

    private CandidatePersonalInformation(String applicantImageUri, String applicantName,
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

  public static final class CandidateMiddleSchoolGrade extends AbstractMiddleSchoolGrade {
    // 근데 이거 자율학기제/학년제 때문에 다른 상태를 가질수도 있음
    // 리스트나 그련 형태를 가지는게 좋을거 같음

    private final BigDecimal grade1Semester1Score; // 1 grade(학년) 1 semester(학기) 점수

    private final BigDecimal grade1Semester2Score;

    private final BigDecimal grade2Semester1Score;

    private final BigDecimal grade2Semester2Score;

    private final BigDecimal grade3Semester1Score;

    private final BigDecimal artisticScore; // 예체능 점수

    private final BigDecimal curricularSubtotalScore; // 교과 성적 소계 - 이거 굳이 필드로 저장할 필요 있나? 메서드로 만들어도 될거 같은데

    private final BigDecimal attendanceScore; // 출석 점수

    private final BigDecimal volunteerScore; // 봉사 점수

    private final BigDecimal extraCurricularSubtotalScore; // 비교과 성적 소계 - 이것도 굳이 저장할 필요가 있나?

    private final BigDecimal totalScore; // 합계 - 환산총점

    private CandidateMiddleSchoolGrade(BigDecimal percentileRank, BigDecimal grade1Semester1Score,
        BigDecimal grade1Semester2Score, BigDecimal grade2Semester1Score,
        BigDecimal grade2Semester2Score, BigDecimal grade3Semester1Score, BigDecimal artisticScore,
        BigDecimal curricularSubtotalScore, BigDecimal attendanceScore, BigDecimal volunteerScore,
        BigDecimal extraCurricularSubtotalScore, BigDecimal totalScore) {
      super(percentileRank);
      this.grade1Semester1Score = grade1Semester1Score;
      this.grade1Semester2Score = grade1Semester2Score;
      this.grade2Semester1Score = grade2Semester1Score;
      this.grade2Semester2Score = grade2Semester2Score;
      this.grade3Semester1Score = grade3Semester1Score;
      this.artisticScore = artisticScore;
      this.curricularSubtotalScore = curricularSubtotalScore;
      this.attendanceScore = attendanceScore;
      this.volunteerScore = volunteerScore;
      this.extraCurricularSubtotalScore = extraCurricularSubtotalScore;
      this.totalScore = totalScore;
    }

  }

}
