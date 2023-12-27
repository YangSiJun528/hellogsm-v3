package team.themomnet.hellogsm.core.domain.application.model;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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

    // 이거 굳이 필드로 저장할 필요 있나? 메서드로 만들어도 될거 같은데,
    // -> 오브젝트 매퍼가 프로퍼티(필드 + get/setter)를 참고해서 변수로 선언해줘야 함 - https://sedangdang.tistory.com/307
    // -> 대신 외부에서 입력받지 않고, 직접 계산해서 넣거나 할 수 있음
    // -> 근데 그런걸 외부 계산 객체에서 할꺼라 냅두는게 나을듯

    private final BigDecimal curricularSubtotalScore; // 교과 성적 소계

    private final BigDecimal attendanceScore; // 출석 점수

    private final BigDecimal volunteerScore; // 봉사 점수

    private final BigDecimal extraCurricularSubtotalScore; // 비교과 성적 소계 - 이것도 굳이 저장할 필요가 있나?

    private final BigDecimal totalScore; // 합계 - 환산총점

    private CandidateMiddleSchoolGrade(BigDecimal percentileRank, MiddleSchoolTranscript transcript,
        CandidateMiddleSchoolGradeCommand command) {
      super(percentileRank);
      this.transcript = transcript;
      this.grade1Semester1Score = command.grade1Semester1Score();
      this.grade1Semester2Score = command.grade1Semester2Score();
      this.grade2Semester1Score = command.grade2Semester1Score();
      this.grade2Semester2Score = command.grade2Semester2Score();
      this.grade3Semester1Score = command.grade3Semester1Score();
      this.artisticScore = command.artisticScore();
      this.curricularSubtotalScore = command.curricularSubtotalScore();
      this.attendanceScore = command.attendanceScore();
      this.volunteerScore = command.volunteerScore();
      this.extraCurricularSubtotalScore = command.extraCurricularSubtotalScore();
      this.totalScore = command.totalScore();
    }

  }


  /*
   * 다음과 같은 형식의 데이터를 가짐
   *  {
   *    "grades": {
   *      "국어": {
   *        "1-1": 19,
   *        "1-2": 10,
   *        "2-1": 43,
   *        "2-2": 98,
   *        "3-1": 66
   *    },
   *  ...
   *      "영어": {
   *        "1-1": 67,
   *        "1-2": 66,
   *        "2-1": 46,
   *        "2-2": 60,
   *        "3-1": 54
   *      }
   *    },
   *    "semesters": [
   *      "1-1",
   *      "1-2",
   *      "2-1",
   *      "2-2",
   *      "3-1"
   *    ]
   *    "subjects": [
   *      "국어",
   *      "수학",
   *      "사회",
   *      ...
   *      "중국어" - 기본 과목 이외에 과목 추가 가능
   *    ]
   *  }
   */
  public static class MiddleSchoolTranscript {

    public static final List<String> DEFAULT_SUBJECTS = List.of("국어", "도덕", "사회", "역사", "수학", "과학",
        "기술가정", "정보", "영어");

    private final Map<String, Map<String, Integer>> grades;

    private final List<String> semesters;

    private final List<String> subjects;

    public MiddleSchoolTranscript(
        @NotNull Map<String, Map<String, Integer>> grades,
        @NotNull SemesterType semesterType,
        @NotNull List<String> subjects
    ) {
      this.semesters = semesterType.getSemesters();
      this.subjects = subjects;
      this.grades = new HashMap<>(); // new로 선언하는 이유는 선언 시 사용한 외부의 grades를 수정하면 객체 내부의 값이 변경될 수 있기 때문

      // subjects에는 DEFAULT_SUBJECTS의 모든 항목이 포함되어야 함
      if (!new HashSet<>(subjects).containsAll(DEFAULT_SUBJECTS)) {
        throw new IllegalArgumentException("All DEFAULT_SUBJECTS must be included in provided subjects.");
      }

      // grades에 subjects 중 일부 요소가 누락되었는지 확인
      for (String subject : subjects) {
        if (!grades.containsKey(subject)) {
          throw new IllegalArgumentException("Missing subject in provided grades: " + subject);
        }
      }

      // grades의 각 항목이 모든 semesters를 가지는지 확인
      for (Map.Entry<String, Map<String, Integer>> entry : grades.entrySet()) {
        String subject = entry.getKey();
        Map<String, Integer> subjectGrades = entry.getValue();

        if (!subjectGrades.keySet().containsAll(semesters)) {
          throw new IllegalArgumentException("Subject " + subject + " is missing grades for some semesters.");
        }
      }
    }

    public boolean areInnerMapKeysMatchingSemesters() {
      for (Map<String, Integer> subjectGrades : grades.values()) {
        if (!subjectGrades.keySet().containsAll(semesters) || !semesters.containsAll(subjectGrades.keySet())) {
          return false;
        }
      }
      return true;
    }

    public boolean areGradesKeysMatchingSubjects() {
      return grades.keySet().containsAll(subjects) && subjects.containsAll(grades.keySet());
    }


    public Map<String, Integer> getGradesForSubject(String subject) {
      return Collections.unmodifiableMap(grades.get(subject));
    }

    public Integer getGradeForSubjectInSemester(String subject, String semester) {
      if (!grades.containsKey(subject) || !semesters.contains(semester)) {
        throw new IllegalArgumentException("Invalid subject or semester");
      }
      return grades.get(subject).get(semester);
    }

    public List<String> getSemesters() {
      return Collections.unmodifiableList(semesters);
    }

    public Map<String, Map<String, Integer>> getGrades() {
      return Collections.unmodifiableMap(grades);
    }

    public List<String> getSubjects() {
      return Collections.unmodifiableList(subjects);
    }
  }

  public record CandidateMiddleSchoolGradeCommand(
      BigDecimal grade1Semester1Score,
      BigDecimal grade1Semester2Score,
      BigDecimal grade2Semester1Score,
      BigDecimal grade2Semester2Score,
      BigDecimal grade3Semester1Score,
      BigDecimal artisticScore,
      BigDecimal curricularSubtotalScore,
      BigDecimal attendanceScore,
      BigDecimal volunteerScore,
      BigDecimal extraCurricularSubtotalScore,
      BigDecimal totalScore
  ) {

  }

  public enum SemesterType {
    FREE_GRADE(List.of(SemesterType.GRADE_2_1, SemesterType.GRADE_2_2, SemesterType.GRADE_3_1)),
    GRADE_1_1_FREE_SEMESTER(List.of(SemesterType.GRADE_1_2, SemesterType.GRADE_2_1, SemesterType.GRADE_2_2, SemesterType.GRADE_3_1)),
    GRADE_1_2_FREE_SEMESTER(List.of(SemesterType.GRADE_1_1, SemesterType.GRADE_2_1, SemesterType.GRADE_2_2, SemesterType.GRADE_3_1)),
    GRADE_2_1_FREE_SEMESTER(List.of(SemesterType.GRADE_1_1, SemesterType.GRADE_1_2, SemesterType.GRADE_2_2, SemesterType.GRADE_3_1)),
    GRADE_2_2_FREE_SEMESTER(List.of(SemesterType.GRADE_1_1, SemesterType.GRADE_1_2, SemesterType.GRADE_2_1, SemesterType.GRADE_3_1)),
    GRADE_3_1_FREE_SEMESTER(List.of(SemesterType.GRADE_1_1, SemesterType.GRADE_1_2, SemesterType.GRADE_2_1, SemesterType.GRADE_2_2)),
    NO_FREE_SEMESTER(List.of(SemesterType.GRADE_1_1, SemesterType.GRADE_1_2, SemesterType.GRADE_2_1, SemesterType.GRADE_2_2, SemesterType.GRADE_3_1));

    public static final String GRADE_1_1 = "1-1";
    public static final String GRADE_1_2 = "1-2";
    public static final String GRADE_2_1 = "2-1";
    public static final String GRADE_2_2 = "2-2";
    public static final String GRADE_3_1 = "3-1";

    private final List<String> semesters;

    SemesterType(List<String> semesters) {
      this.semesters = semesters;
    }

    public List<String> getSemesters() {
      return Collections.unmodifiableList(semesters);
    }
  }

}
