package dev.yangsijun.hellotest.domain.application.entity.convert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.yangsijun.hellotest.domain.application.type.ArtSportScore;
import dev.yangsijun.hellotest.domain.application.type.CurricularScore;
import dev.yangsijun.hellotest.domain.application.type.MiddleSchoolTranscript;
import dev.yangsijun.hellotest.domain.application.type.SemesterType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;
import java.util.Map;

@Converter
public class MiddleSchoolTranscriptConverter implements AttributeConverter<MiddleSchoolTranscript, String> {

  private static final ObjectMapper mapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(MiddleSchoolTranscript attribute) {
    try {
      String json = mapper.writeValueAsString(attribute);
      return json;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public MiddleSchoolTranscript convertToEntityAttribute(String dbData) {
    try {
      Map<String, Object> jsonMap = mapper.readValue(dbData, new TypeReference<Map<String, Object>>() {});
      Map<String, Map<String, CurricularScore>> generalCurriculumGrades = (Map<String, Map<String, CurricularScore>>) jsonMap.get("generalCurriculumGrades");
      List<String> generalCurriculumSemesters = (List<String>) jsonMap.get("generalCurriculumSemesters");
      List<String> generalCurriculumSubjects = (List<String>) jsonMap.get("generalCurriculumSubjects");
      Map<String, Map<String, ArtSportScore>> artSportGrades = (Map<String, Map<String, ArtSportScore>>) jsonMap.get("artSportGrades");
      Map<String, Map<String, Integer>> nonCurriculumGrades = (Map<String, Map<String, Integer>>) jsonMap.get("nonCurriculumGrades");
      return new MiddleSchoolTranscript(generalCurriculumGrades, getSemesterType(generalCurriculumSemesters), generalCurriculumSubjects, artSportGrades, nonCurriculumGrades);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private SemesterType getSemesterType(List<String> generalCurriculumSemesters) {
    for (SemesterType type : SemesterType.values()) {
      if (type.getSemesters().equals(generalCurriculumSemesters)) {
        return type;
      }
    }
    throw new RuntimeException();
  }
}
