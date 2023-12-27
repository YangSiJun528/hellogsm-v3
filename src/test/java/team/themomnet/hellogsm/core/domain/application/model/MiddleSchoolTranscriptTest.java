package team.themomnet.hellogsm.core.domain.application.model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.api.function.Executable;
import team.themomnet.hellogsm.core.domain.type.SemesterType;

import static org.junit.jupiter.api.Assertions.*;

public class MiddleSchoolTranscriptTest {

  private MiddleSchoolTranscript middleSchoolTranscript;

  @BeforeEach
  public void setUp() {
    List<String> subjects = new ArrayList<>(List.of("중국어", "프로그래밍"));
    subjects.addAll(MiddleSchoolTranscript.DEFAULT_SUBJECTS);
    middleSchoolTranscript = new MiddleSchoolTranscript(createGrades(subjects), SemesterType.NO_FREE_SEMESTER, subjects);
  }

  private Map<String, Map<String, Integer>> createGrades(List<String> subjects) {
    Map<String, Map<String, Integer>> grades = new HashMap<>();
    List<String> semesters = SemesterType.NO_FREE_SEMESTER.getSemesters();

    for (String subject : subjects) {
      Map<String, Integer> subjectGrades = new HashMap<>();
      for (String semester : semesters) {
        subjectGrades.put(semester, (int) (Math.random() * 100) + 1);
      }
      grades.put(subject, subjectGrades);
    }

    return grades;
  }

  @Test
  public void testGetGradesForSubject() {
    String subject = "국어";
    Map<String, Integer> gradesForSubject = middleSchoolTranscript.getGradesForSubject(subject);

    assertTrue(gradesForSubject.keySet().containsAll(middleSchoolTranscript.getSemesters()));
    assertTrue(gradesForSubject.values().stream().allMatch(grade -> grade >= 1 && grade <= 100));
  }

  @Test
  public void testGetGradeForSubjectInSemester() {
    String subject = "수학";
    Integer grade = middleSchoolTranscript.getGradeForSubjectInSemester(subject, SemesterType.GRADE_2_2);

    assertNotNull(grade);
    assertTrue(grade >= 1 && grade <= 100);
  }

  @Test
  public void testGetSemesters() {
    List<String> semesters = middleSchoolTranscript.getSemesters();
    assertEquals(SemesterType.NO_FREE_SEMESTER.getSemesters(), semesters);
  }

  @Test
  public void testGetSubjects() {
    List<String> subjects = middleSchoolTranscript.getSubjects();
    assertTrue(subjects.containsAll(MiddleSchoolTranscript.DEFAULT_SUBJECTS));
  }

  @Test
  public void testInvalidSubject() {
    String subjectNotInGrade = "음악";
    assertThrows(IllegalArgumentException.class, () -> {
      middleSchoolTranscript.getGradeForSubjectInSemester(subjectNotInGrade, SemesterType.GRADE_1_1);
    });
  }

  @Test
  public void testObjectToJson() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(middleSchoolTranscript);
    System.out.println(json);
    assertNotNull(json);
  }

  @Test
  void invalidMiddleSchoolTranscriptMissingDefaultSubjects() {
    // Arrange
    Map<String, Map<String, Integer>> grades = new HashMap<>();
    List<String> subjects = List.of("국어", "수학", "영어");
    List<String> semesters = List.of("1-1", "1-2", "2-1");

    for (String subject : subjects) {
      Map<String, Integer> subjectGrades = new HashMap<>();
      for (String semester : semesters) {
        subjectGrades.put(semester, 80); // Sample grade (80)
      }
      grades.put(subject, subjectGrades);
    }

    // Act & Assert
    Executable executable = () -> new MiddleSchoolTranscript(grades,
        SemesterType.FREE_GRADE, subjects);
    assertThrows(IllegalArgumentException.class, executable,
        "All DEFAULT_SUBJECTS must be included in provided subjects.");
  }

  @Test
  void invalidMiddleSchoolTranscriptMissingGrades() {
    // Arrange
    Map<String, Map<String, Integer>> grades = new HashMap<>();
    List<String> subjects = List.of("국어", "수학", "영어", "과학");
    List<String> semesters = List.of("1-1", "1-2", "2-1");

    // Missing grades for the subject "과학"
    grades.put("국어", Map.of("1-1", 80, "1-2", 85, "2-1", 90));
    grades.put("수학", Map.of("1-1", 75, "1-2", 88, "2-1", 92));
    grades.put("영어", Map.of("1-1", 95, "1-2", 80, "2-1", 87));

    // Act & Assert
    Executable executable = () -> new MiddleSchoolTranscript(grades,
        SemesterType.FREE_GRADE, subjects);
    assertThrows(IllegalArgumentException.class, executable,
        "Subject 과학 is missing grades for some semesters.");
  }

}
