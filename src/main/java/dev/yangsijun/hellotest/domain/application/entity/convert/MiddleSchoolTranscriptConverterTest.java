package dev.yangsijun.hellotest.domain.application.entity.convert;

import dev.yangsijun.hellotest.domain.application.type.ArtSportScore;
import dev.yangsijun.hellotest.domain.application.type.CurricularScore;
import dev.yangsijun.hellotest.domain.application.type.MiddleSchoolTranscript;
import dev.yangsijun.hellotest.domain.application.type.SemesterType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MiddleSchoolTranscriptConverterTest {

  // 임시로 MiddleSchoolTranscript 공유하기 위해 복제함

  public MiddleSchoolTranscript validTranscript;

  private static final SemesterType semesterType = SemesterType.GRADE_1_1_FREE_SEMESTER;
  private static final List<String> semesters = semesterType.getSemesters();

  private static final List<String> subjects = new ArrayList<>();

  static {
    subjects.addAll(List.of("중국어", "프로그래밍"));
    subjects.addAll(MiddleSchoolTranscript.CURRICULUM_DEFAULT_SUBJECTS);
  }

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
    List<String> semesters = MiddleSchoolTranscriptConverterTest.semesters;

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


  MiddleSchoolTranscriptConverter converter = new MiddleSchoolTranscriptConverter();

}
