package team.themomnet.hellogsm.core.domain.application.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MiddleSchoolTranscriptTest {
  private CandidateApplication.MiddleSchoolTranscript middleSchoolTranscript;
  private Map<String, Map<String, Integer>> initialGrades;
  private List<String> initialSemesters;

  @BeforeEach
  public void setUp() {
    initialGrades = new HashMap<>();
    List<String> subjects = Arrays.asList("국어", "수학", "사회", "과학", "영어", "도덕");
    initialSemesters = Arrays.asList("1-1", "1-2", "2-1", "2-2", "3-1");

    for (String subject : subjects) {
      Map<String, Integer> grades = new HashMap<>();
      for (String semester : initialSemesters) {
        grades.put(semester, (int) (Math.random() * 100) + 1);  // 1~100 사이의 임의의 점수
      }
      initialGrades.put(subject, grades);
    }

    middleSchoolTranscript = new CandidateApplication.MiddleSchoolTranscript(initialGrades, initialSemesters);
  }

  @Test
  public void testGetGradesForSubject() {
    for (String subject : initialGrades.keySet()) {
      Map<String, Integer> grades = middleSchoolTranscript.getGradesForSubject(subject);
      assertEquals(initialGrades.get(subject), grades);
    }
  }

  @Test
  public void testGetGradeForSubjectInSemester() {
    for (String subject : initialGrades.keySet()) {
      for (String semester : initialSemesters) {
        assertEquals(initialGrades.get(subject).get(semester), middleSchoolTranscript.getGradeForSubjectInSemester(subject, semester));
      }
    }
  }

  @Test
  public void testGetSemesters() {
    assertEquals(initialSemesters, middleSchoolTranscript.getSemesters());
  }

  @Test
  public void testInvalidSemester() {
    assertThrows(IllegalArgumentException.class, () -> middleSchoolTranscript.getGradeForSubjectInSemester("수학", "4-1"));
  }

  @Test
  public void testObjectToJson() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(middleSchoolTranscript);
    System.out.println(json);
    assertNotNull(json);
  }

  @Test
  public void testJsonToObject() throws IOException {
    String jsonString = "{\"grades\": {\"국어\": {\"1-1\": 90, \"1-2\": 95}, \"과학\": {\"1-1\": 85, \"1-2\": 88}}, \"semesters\": [\"1-1\", \"1-2\"]}";

    ObjectMapper objectMapper = new ObjectMapper();
    CandidateApplication.MiddleSchoolTranscript transcript = objectMapper.readValue(jsonString, CandidateApplication.MiddleSchoolTranscript.class);

    // Now you can assert the values
    // For example:
    assertEquals(90, transcript.getGradeForSubjectInSemester("국어", "1-1"));
    assertEquals(95, transcript.getGradeForSubjectInSemester("국어", "1-2"));
    assertEquals(85, transcript.getGradeForSubjectInSemester("과학", "1-1"));
    assertEquals(88, transcript.getGradeForSubjectInSemester("과학", "1-2"));
  }

}
