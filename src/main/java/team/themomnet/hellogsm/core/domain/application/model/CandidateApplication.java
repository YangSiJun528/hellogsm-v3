package team.themomnet.hellogsm.core.domain.application.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private final MiddleSchoolTranscript transcript;// 중학교 성적증명서

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
    private CandidateMiddleSchoolGrade(BigDecimal percentileRank, MiddleSchoolTranscript transcript,
        BigDecimal grade1Semester1Score,
        BigDecimal grade1Semester2Score, BigDecimal grade2Semester1Score,
        BigDecimal grade2Semester2Score, BigDecimal grade3Semester1Score, BigDecimal artisticScore,
        BigDecimal curricularSubtotalScore, BigDecimal attendanceScore, BigDecimal volunteerScore,
        BigDecimal extraCurricularSubtotalScore, BigDecimal totalScore) {
      super(percentileRank);
      this.transcript = transcript;
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


  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class MiddleSchoolTranscript {

    private final Map<String, Map<String, Integer>> grades;
    private final List<String> semesters;

    // Default constructor
    public MiddleSchoolTranscript() {
      this.grades = new HashMap<>();
      this.semesters = new ArrayList<>();
    }

    // Constructor for deserialization
    @JsonCreator
    public MiddleSchoolTranscript(@JsonProperty("grades") Map<String, Map<String, Integer>> initialGrades,
        @JsonProperty("semesters") List<String> initialSemesters) {
      this.grades = (initialGrades != null) ? new HashMap<>(initialGrades) : new HashMap<>();
      this.semesters = (initialSemesters != null) ? new ArrayList<>(initialSemesters) : new ArrayList<>();
    }

    public Map<String, Integer> getGradesForSubject(String subject) {
      return Collections.unmodifiableMap(grades.get(subject));
    }

    public Integer getGradeForSubjectInSemester(String subject, String semester) {
      if (!semesters.contains(semester)) {
        throw new IllegalArgumentException("Invalid semester: " + semester);
      }
      return grades.get(subject).get(semester);
    }

    public List<String> getSemesters() {
      return Collections.unmodifiableList(semesters);
    }

    @JsonGetter // com.fasterxml.jackson 의존성 생김 - 괜찮나?
    public Map<String, Map<String, Integer>> getGrades() {
      return grades;
    }

    @JsonSetter // setter 메서드 추가
    public void setGrades(Map<String, Map<String, Integer>> grades) {
      this.grades.clear();
      this.grades.putAll(grades);
    }

    @JsonCreator
    public static MiddleSchoolTranscript createFromJson(@JsonProperty("grades") Map<String, Map<String, Integer>> grades,
        @JsonProperty("semesters") List<String> semesters) {
      return new MiddleSchoolTranscript(grades, semesters);
    }

  }

}
