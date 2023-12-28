package team.themomnet.hellogsm.core.domain.application.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.themomnet.hellogsm.core.domain.type.ArtSportScore;
import team.themomnet.hellogsm.core.domain.type.CurricularScore;
import team.themomnet.hellogsm.core.domain.type.SemesterType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MiddleSchoolTranscriptTest {

  private MiddleSchoolTranscript validTranscript;

  private static final SemesterType semesterType = SemesterType.GRADE_1_1_FREE_SEMESTER;
  private static final Set<String> semesters = semesterType.getSemesters();

  private static final Set<String> subjects = new HashSet<>();

  static {
    subjects.addAll(Set.of("중국어", "프로그래밍"));
    subjects.addAll(MiddleSchoolTranscript.CURRICULUM_DEFAULT_SUBJECTS);
  }

  @BeforeEach
  public void setUp() {
    // 예체능, 비교과 점수 생성
    validTranscript = new MiddleSchoolTranscript(
        createCurricularGrades(subjects),
        semesterType,
        subjects,
        artSportGradesGrades(),
        nonCurricularGradesGrades()
    );
  }

  private Map<String, Map<String, CurricularScore>> createCurricularGrades(Set<String> subjects) {
    Map<String, Map<String, CurricularScore>> curricularGrades = new HashMap<>();
    Set<String> semesters = MiddleSchoolTranscriptTest.semesters;

    for (String subject : subjects) {
      Map<String, CurricularScore> subjectGrades = new HashMap<>();
      for (String semester : semesters) {
        subjectGrades.put(semester, randomCurricularScore());
      }
      curricularGrades.put(subject, subjectGrades);
    }

    return curricularGrades;
  }

  private Map<String, Map<String, ArtSportScore>> artSportGradesGrades() {
    Map<String, Map<String, ArtSportScore>> artSportGrades = new HashMap<>();
    Set<String> semesters = MiddleSchoolTranscript.ART_SPORT_KEY_SET;

    for (String subject : MiddleSchoolTranscript.ART_SPORT_SUBJECTS) {
      Map<String, ArtSportScore> subjectGrades = new HashMap<>();
      for (String semester : semesters) {
        subjectGrades.put(semester, randomArtSportScore());
      }
      artSportGrades.put(subject, subjectGrades);
    }

    return artSportGrades;
  }

  private Map<String, Map<String, Integer>> nonCurricularGradesGrades() {
    Map<String, Map<String, Integer>> nonCurricularGrades = new HashMap<>();
    Set<String> semesters = MiddleSchoolTranscript.SCHOOL_YEAR;

    for (String subject : MiddleSchoolTranscript.NON_CURRICULUM_SUBJECTS) {
      Map<String, Integer> subjectGrades = new HashMap<>();
      for (String semester : semesters) {
        subjectGrades.put(semester, randomNonCurricularScore());
      }
      nonCurricularGrades.put(subject, subjectGrades);
    }

    return nonCurricularGrades;
  }

  public CurricularScore randomCurricularScore() {
    List<CurricularScore> curricularScores = Arrays.asList(CurricularScore.values());
    Random rand = new Random();
    CurricularScore randomElement = curricularScores.get(rand.nextInt(curricularScores.size()));
    return randomElement;
  }

  public ArtSportScore randomArtSportScore() {
    List<ArtSportScore> artSportScores = Arrays.asList(ArtSportScore.values());
    Random rand = new Random();
    ArtSportScore randomElement = artSportScores.get(rand.nextInt(artSportScores.size()));
    return randomElement;
  }

  public int randomNonCurricularScore() {
    Random rand = new Random();
    return rand.nextInt(10); //0 <= int < N
  }

  @Test
  public void testObjectToJson() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(validTranscript);
    System.out.println(json);
    assertNotNull(json);
  }

  @Test
  public void createWithoutDefaultSubject() {
    // given
    Set<String> inValidSubjects = new HashSet<>(MiddleSchoolTranscriptTest.subjects);
    inValidSubjects.remove("국어");

    // when & then
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new MiddleSchoolTranscript(
            createCurricularGrades(inValidSubjects),
            semesterType,
            subjects,
            artSportGradesGrades(),
            nonCurricularGradesGrades()
        ));
    assertThat(exception.getMessage()).contains("subjects와 generalCurriculumGrades의 keySet이 일치하지 않습니다.");
  }

//  @Test
//  public void tyrInValidCreate() {
//    // given
//    Set<String> inValidSubjects = new HashSet<>(MiddleSchoolTranscriptTest.subjects);
//    inValidSubjects.remove("국어");
//
//    // when & then
//    Throwable exception = assertThrows(IllegalArgumentException.class,
//        () -> new MiddleSchoolTranscript(
//            createCurricularGrades(inValidSubjects),
//            semesterType,
//            subjects,
//            artSportGradesGrades(),
//            nonCurricularGradesGrades()
//        ));
//    assertThat(exception.getMessage()).contains("subjects와 generalCurriculumGrades의 keySet이 일치하지 않습니다.");
//  }
//
//  @Test
//  public void tyrInValidCreate() {
//    // given
//    Set<String> inValidSubjects = new HashSet<>(MiddleSchoolTranscriptTest.subjects);
//    inValidSubjects.remove("국어");
//
//    // when & then
//    Throwable exception = assertThrows(IllegalArgumentException.class,
//        () -> new MiddleSchoolTranscript(
//            createCurricularGrades(inValidSubjects),
//            semesterType,
//            subjects,
//            artSportGradesGrades(),
//            nonCurricularGradesGrades()
//        ));
//    assertThat(exception.getMessage()).contains("subjects와 generalCurriculumGrades의 keySet이 일치하지 않습니다.");
//  }
}
