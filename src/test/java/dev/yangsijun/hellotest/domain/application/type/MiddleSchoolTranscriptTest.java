package dev.yangsijun.hellotest.domain.application.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MiddleSchoolTranscriptTest {

  private MiddleSchoolTranscript validTranscript;

  private static final SemesterType semesterType = SemesterType.GRADE_1_1_FREE_SEMESTER;
  private static final List<String> semesters = semesterType.getSemesters();

  private static final List<String> subjects = new ArrayList<>();

  static {
    subjects.addAll(List.of("중국어", "프로그래밍"));
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

  private Map<String, Map<String, CurricularScore>> createCurricularGrades(List<String> subjects) {
    Map<String, Map<String, CurricularScore>> curricularGrades = new HashMap<>();
    List<String> semesters = MiddleSchoolTranscriptTest.semesters;

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
    List<String> semesters = MiddleSchoolTranscript.ART_SPORT_KEY_SET;

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
    List<String> semesters = MiddleSchoolTranscript.SCHOOL_YEAR;

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
  public void createInconsistencyBetweenGeneralCurriculumGradesAndGeneralCurriculumSubjects() {
    // given
    List<String> inValidSubjects = new ArrayList<>(MiddleSchoolTranscriptTest.subjects);
    inValidSubjects.add("국제외교");

    // when & then
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new MiddleSchoolTranscript(
            createCurricularGrades(inValidSubjects),
            semesterType,
            subjects,
            artSportGradesGrades(),
            nonCurricularGradesGrades()
        ));
    assertThat(exception.getMessage()).contains("generalCurriculumSubjects와 generalCurriculumGrades의 keySet이 일치하지 않습니다.");
  }

  @Test
  public void createWithoutDefaultSubject() {
    // given
    List<String> inValidSubjects = new ArrayList<>(MiddleSchoolTranscriptTest.subjects);
    inValidSubjects.remove("국어");

    // when & then
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new MiddleSchoolTranscript(
            createCurricularGrades(inValidSubjects),
            semesterType,
            inValidSubjects,
            artSportGradesGrades(),
            nonCurricularGradesGrades()
        ));
    assertThat(exception.getMessage()).contains("generalCurriculumSubjects가 CURRICULUM_DEFAULT_SUBJECTS를 전부 포함하지 않습니다.");
  }

  @Test
  public void generalCurriculumGrades의_모든_과목의_학기_중_일부와_SemesterType의_학기와_일치하지_않음() {
    // given
    Map<String, Map<String, CurricularScore>> validGrades = createCurricularGrades(subjects);
    validGrades.get("국어").remove(SemesterType.GRADE_2_1);

    // when & then
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new MiddleSchoolTranscript(
            validGrades,
            semesterType,
            subjects,
            artSportGradesGrades(),
            nonCurricularGradesGrades()
        ));
    assertThat(exception.getMessage()).contains("과목 국어의 학기가 SemesterType의 학기 정보와 일치하지 않습니다.");
  }

  @Test
  public void 필요_과목과_artSportGrades의_과목의_불일치() {
    // given
    var inValidArtSportGradesGrades = artSportGradesGrades();
    inValidArtSportGradesGrades.remove("체육");

    // when & then
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new MiddleSchoolTranscript(
            createCurricularGrades(subjects),
            semesterType,
            subjects,
            inValidArtSportGradesGrades,
            nonCurricularGradesGrades()
        ));
    assertThat(exception.getMessage()).contains("체육 과목을 가지고 있지 않습니다. 다음과 같은 과목이 필요: [체육, 음악, 미술]");
  }

  @Test
  public void 필요_학기와_artSportGrades의_모든_과목의_학기_중_일부_사이에서_불일치() {
    // given
    var inValidArtSportGradesGrades = artSportGradesGrades();
    inValidArtSportGradesGrades.get("체육").remove(SemesterType.GRADE_2_1);

    // when & then
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new MiddleSchoolTranscript(
            createCurricularGrades(subjects),
            semesterType,
            subjects,
            inValidArtSportGradesGrades,
            nonCurricularGradesGrades()
        ));
    assertThat(exception.getMessage()).contains("체육 과목이 필요한 모든 학기를 가지고 있지 않습니다. 현재 체육의 학기: [2-2, 3-1]. 필요한 학기: [2-1, 2-2, 3-1]");
  }

  @Test
  public void 필요_과목과_nonCurriculumGrades의_과목의_불일치() {
    // given
    var inValidNonCurricularGradesGrades = nonCurricularGradesGrades();
    // MiddleSchoolTranscript.SCHOOL_YEAR.get(0) == 1
    inValidNonCurricularGradesGrades.remove("지각");

    // when & then
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new MiddleSchoolTranscript(
            createCurricularGrades(subjects),
            semesterType,
            subjects,
            artSportGradesGrades(),
            inValidNonCurricularGradesGrades
        ));
    assertThat(exception.getMessage()).contains( "지각 과목을 가지고 있지 않습니다. 다음과 같은 과목이 필요: [결석, 지각, 조퇴, 결과, 봉사활동(시간)]");
  }

  @Test
  public void 필요_학기와_nonCurriculumGrades의_모든_과목의_학기_중_일부_사이에서_불일치() {
    // given
    var inValidNonCurricularGradesGrades = nonCurricularGradesGrades();
    // MiddleSchoolTranscript.SCHOOL_YEAR.get(0) == 1
    inValidNonCurricularGradesGrades.get("지각").remove(MiddleSchoolTranscript.SCHOOL_YEAR.get(0));

    // when & then
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new MiddleSchoolTranscript(
            createCurricularGrades(subjects),
            semesterType,
            subjects,
            artSportGradesGrades(),
            inValidNonCurricularGradesGrades
        ));
    assertThat(exception.getMessage()).contains("지각 과목이 필요한 모든 학기를 가지고 있지 않습니다. 현재 지각의 학기: [2, 3]. 필요한 학기: [1, 2, 3]");
  }
}
