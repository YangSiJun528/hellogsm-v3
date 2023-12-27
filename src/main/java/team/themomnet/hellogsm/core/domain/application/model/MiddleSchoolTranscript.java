package team.themomnet.hellogsm.core.domain.application.model;

import jakarta.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import team.themomnet.hellogsm.core.domain.type.SemesterType;

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
public class MiddleSchoolTranscript {

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


    //TODO grades의 keySet의 요소가 subjects의 요소와 동일해야 한다. 아니라면 포함되지 않는 과목이 존재한다는 메시지를 담아 에러 발생
    //TODO grades의 모든 values인 Map의 모든 KeySet가 동일한 semesters를 가져야 한다. 아니라면 포함되지 semesters가 유효하지 않는다는 메시지를 담아 에러 발생
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
