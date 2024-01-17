package dev.yangsijun.hellotest.domain.application.type;

import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/*
 * 다음과 같은 형식의 데이터를 가짐
 * {
 *   "generalCurriculumGrades": {
 *     "국어": { "2-1": "C", "1-2": "B", "3-1": "C", "2-2": "E" },
 *     ...
 *     "사회": { "2-1": "B", "1-2": "A", "3-1": "E", "2-2": "D" }
 *   },
 *   "generalCurriculumSemesters": [
 *     "2-1", "1-2", "3-1", "2-2"
 *   ],
 *   "generalCurriculumSubjects": [
 *     "중국어", "국어", "역사", "정보", "도덕", "과학", "사회", "기술가정", "수학", "영어", "프로그래밍"
 *   ],
 *   "nonCurriculumGrades": {
 *     "결석": { "1": 9, "2": 6, "3": 5 },
 *     "지각": { "1": 1, "2": 9, "3": 7 },
 *     "결과": { "1": 7, "2": 5, "3": 7 },
 *     "봉사활동(시간)": { "1": 2, "2": 8, "3": 5 },
 *     "조퇴": { "1": 1, "2": 2, "3": 9 }
 *   },
 *   "artSportGrades": {
 *     "음악": { "2-1": "B", "3-1": "C", "2-2": "C" },
 *     "체육": { "2-1": "B", "3-1": "C", "2-2": "C" },
 *     "미술": { "2-1": "B", "3-1": "A", "2-2": "B" }
 *   }
 * }
 */
// 사용하려고 했는데, DBConvert 과정이 너무 복잡해서 임시 구현에서는 사용 안함
public class MiddleSchoolTranscript {

  public static final List<String> CURRICULUM_DEFAULT_SUBJECTS = List.of("국어", "도덕", "사회", "역사",
      "수학", "과학", "기술가정", "정보", "영어");
  public static final List<String> ART_SPORT_SUBJECTS = List.of("체육", "음악", "미술");
  public static final List<String> NON_CURRICULUM_SUBJECTS = List.of("결석", "지각", "조퇴", "결과",
      "봉사활동(시간)");
  public static final List<String> ART_SPORT_KEY_SET = new ArrayList<>(
      List.of(SemesterType.GRADE_2_1, SemesterType.GRADE_2_2, SemesterType.GRADE_3_1));
  public static final List<String> SCHOOL_YEAR = new ArrayList<>(
      List.of("1", "2", "3"));

  private final Map<String, Map<String, CurricularScore>> generalCurriculumGrades;

  private final List<String> generalCurriculumSemesters;

  private final List<String> generalCurriculumSubjects;

  private final Map<String, Map<String, Integer>> nonCurriculumGrades;

  private final Map<String, Map<String, ArtSportScore>> artSportGrades;

  public MiddleSchoolTranscript(
      @NotNull Map<String, Map<String, CurricularScore>> generalCurriculumGrades,
      @NotNull SemesterType generalCurriculumSemesterType,
      @NotNull List<String> generalCurriculumSubjects,
      @NotNull Map<String, Map<String, ArtSportScore>> artSportGrades,
      @NotNull Map<String, Map<String, Integer>> nonCurriculumGrades
  ) {
    // new로 선언하는 이유는 선언 시 사용한 외부의 generalCurriculumGrades를 수정하면 객체 내부의 값이 변경될 수 있기 때문
    this.generalCurriculumSubjects = new ArrayList<>(generalCurriculumSubjects);
    // generalCurriculumSemesterType는 Enum에서 unmodifiable 한 상태로 가져와서 new 안써도 됨
    this.generalCurriculumSemesters = generalCurriculumSemesterType.getSemesters();

    validCurriculumGrades(generalCurriculumGrades, generalCurriculumSemesterType,
        generalCurriculumSubjects);
    this.generalCurriculumGrades = new HashMap<>(generalCurriculumGrades);

    validArtSportGrades(artSportGrades);
    this.artSportGrades = new HashMap<>(artSportGrades);

    validNonCurriculumGrades(nonCurriculumGrades);
    this.nonCurriculumGrades = new HashMap<>(nonCurriculumGrades);
  }

  private void validNonCurriculumGrades(Map<String, Map<String, Integer>> nonCurriculumGrades) {
    for (String subject : NON_CURRICULUM_SUBJECTS) {
      if (!nonCurriculumGrades.containsKey(subject)) {
        throw new IllegalArgumentException(
            subject + " 과목을 가지고 있지 않습니다. 다음과 같은 과목이 필요: " + NON_CURRICULUM_SUBJECTS);
      }
      if (!nonCurriculumGrades.get(subject).keySet().equals(Set.copyOf(SCHOOL_YEAR))) {
        throw new IllegalArgumentException(
            subject + " 과목이 필요한 모든 학기를 가지고 있지 않습니다. 현재 " + subject + "의 학기: "
                + nonCurriculumGrades.get(subject).keySet() + ". 필요한 학기: " + SCHOOL_YEAR);
      }
    }
  }

  private void validCurriculumGrades(
      Map<String, Map<String, CurricularScore>> generalCurriculumGrades,
      SemesterType semesterType,
      List<String> subjects
  ) {
    // assert generalCurriculumGrades.keySet().equals(subjects); assert를 쓰는 기준을 잘 모르곘음

    Set<String> setSubject = Set.copyOf(subjects);

    // subjects와 generalCurriculumGrades가 서로 일치하는가?
    if (!generalCurriculumGrades.keySet().equals(setSubject)) {
      throw new IllegalArgumentException(
          "generalCurriculumSubjects와 generalCurriculumGrades의 keySet이 일치하지 않습니다." +
              "generalCurriculumSubjects: " + subjects + "." +
              "generalCurriculumGrades.keySet(): " + generalCurriculumGrades.keySet());
    }

    // subjects가 CURRICULUM_DEFAULT_SUBJECTS를 전부 포함하는가?
    if (!setSubject.containsAll(CURRICULUM_DEFAULT_SUBJECTS)) {
      throw new IllegalArgumentException(
          "generalCurriculumSubjects가 CURRICULUM_DEFAULT_SUBJECTS를 전부 포함하지 않습니다." +
              "CURRICULUM_DEFAULT_SUBJECTS는 다음과 같다: " + CURRICULUM_DEFAULT_SUBJECTS + ". " +
              "현재 subjects: " + subjects + ".");
    }

    for (Entry<String, Map<String, CurricularScore>> entry : generalCurriculumGrades.entrySet()) {
      String subject = entry.getKey();
      Map<String, CurricularScore> scores = entry.getValue();

      // 모든 과목의 학기가 유효한지 확인
      // 예를 들어,
      //   1학년 1학기 자유학기제라면, 1-1을 제외한 1-2~3-1 학기를 가져야 한다.
      //   따라서 모든 과목이 1-2~3-1를 가지는지 확인해야 한다.
      if (!scores.keySet().equals(Set.copyOf(semesterType.getSemesters()))) {
        throw new IllegalArgumentException(
            "과목 " + subject + "의 학기가 SemesterType의 학기 정보와 일치하지 않습니다.");
      }
    }
  }

  private void validArtSportGrades(Map<String, Map<String, ArtSportScore>> artSportGrades) {
    for (String subject : ART_SPORT_SUBJECTS) {
      if (!artSportGrades.containsKey(subject)) {
        throw new IllegalArgumentException(
            subject + " 과목을 가지고 있지 않습니다. 다음과 같은 과목이 필요: " + ART_SPORT_SUBJECTS);
      }
      if (!artSportGrades.get(subject).keySet().equals(Set.copyOf(ART_SPORT_KEY_SET))) {
        throw new IllegalArgumentException(
            subject + " 과목이 필요한 모든 학기를 가지고 있지 않습니다. 현재 " + subject + "의 학기: "
                + artSportGrades.get(subject).keySet() + ". 필요한 학기: " + ART_SPORT_KEY_SET);
      }
    }
  }


  public Map<String, CurricularScore> getGradesForSubject(String subject) {
    return Collections.unmodifiableMap(generalCurriculumGrades.get(subject));
  }

  public CurricularScore getGradeForSubjectInSemester(String subject, String semester) {
    if (!generalCurriculumGrades.containsKey(subject)
        || !generalCurriculumSemesters.contains(semester)) {
      throw new IllegalArgumentException("Invalid subject or semester");
    }
    return generalCurriculumGrades.get(subject).get(semester);
  }

  public List<String> getGeneralCurriculumSemesters() {
    return Collections.unmodifiableList(generalCurriculumSemesters);
  }

  public Map<String, Map<String, CurricularScore>> getGeneralCurriculumGrades() {
    return Collections.unmodifiableMap(generalCurriculumGrades);
  }

  public List<String> getGeneralCurriculumSubjects() {
    return Collections.unmodifiableList(generalCurriculumSubjects);
  }

  public Map<String, Map<String, ArtSportScore>> getArtSportGrades() {
    return Collections.unmodifiableMap(artSportGrades);
  }

  public Map<String, Map<String, Integer>> getNonCurriculumGrades() {
    return Collections.unmodifiableMap(nonCurriculumGrades);
  }
}
