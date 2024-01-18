package dev.yangsijun.hellotest;

import dev.yangsijun.hellotest.domain.applicant.entity.Applicant;
import dev.yangsijun.hellotest.domain.applicant.repo.ApplicantRepo;
import dev.yangsijun.hellotest.domain.applicant.type.Gender;
import dev.yangsijun.hellotest.domain.application.entity.AbstractApplication;
import dev.yangsijun.hellotest.domain.application.entity.CandidateApplication;
import dev.yangsijun.hellotest.domain.application.entity.CandidateMiddleSchoolGrade;
import dev.yangsijun.hellotest.domain.application.entity.CandidatePersonalInformation;
import dev.yangsijun.hellotest.domain.application.entity.convert.MiddleSchoolTranscriptConverter;
import dev.yangsijun.hellotest.domain.application.entity.convert.MiddleSchoolTranscriptConverterTest;
import dev.yangsijun.hellotest.domain.application.entity.param.AbstractApplicationStatusParameter;
import dev.yangsijun.hellotest.domain.application.entity.param.AbstractPersonalInformationParameter;
import dev.yangsijun.hellotest.domain.application.entity.param.CandidateMiddleSchoolGradeParameter;
import dev.yangsijun.hellotest.domain.application.repo.AppRepo;
import dev.yangsijun.hellotest.domain.application.type.DesiredMajors;
import dev.yangsijun.hellotest.domain.application.type.GraduationStatus;
import dev.yangsijun.hellotest.domain.application.type.Major;
import dev.yangsijun.hellotest.domain.application.type.MiddleSchoolTranscript;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Tester implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    candidateSave();
  }

  @Autowired
  private AppRepo appRepo;

  @Autowired
  private ApplicantRepo applicantRepo;

  private MiddleSchoolTranscriptConverter converter = new MiddleSchoolTranscriptConverter();

  private MiddleSchoolTranscriptConverterTest test = new MiddleSchoolTranscriptConverterTest(); // MiddleSchoolTranscript 가져오기 위해서 사용


  public void candidateSave() {
    test.setUp();

    applicantRepo.deleteAll();
    appRepo.deleteAll();

    var applicant = new Applicant(
        null,
        "지원자이름",
        "01012345678",
        LocalDate.now(),
        Gender.MALE,
        1L
    );

    applicantRepo.save(applicant);


    MiddleSchoolTranscript transcript = test.validTranscript;

    UUID id = UUID.randomUUID();

    var app = new CandidateApplication(
        null,
        new CandidatePersonalInformation(
            null,
            new AbstractPersonalInformationParameter(
                "https://gsm.applicantImageUri?id=10000",
                "주소",
                "세부 주소",
                GraduationStatus.CANDIDATE,
                "01012345678",
                "부모이름",
                "부모 관계"
            ),
            "학교 이름",
            "학교 주소",
            "선생 이름",
            "01087654321"
        ),
        new CandidateMiddleSchoolGrade(
            null,
            new CandidateMiddleSchoolGradeParameter(
                transcript,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE
            )
        ),
        AbstractApplicationStatusParameter.init(new DesiredMajors(List.of(Major.SW, Major.AI, Major.IOT))),
        applicant
    );

    appRepo.save(app);

    List<AbstractApplication> apps = appRepo.findAll();

    if(!apps.get(0).getApplicant().getId().equals(app.getApplicant().getId())) {
      throw new RuntimeException("테스트 실패");
    } else {
      System.out.println("테스트 성공");
      var t = ((CandidateMiddleSchoolGrade) apps.get(0).getGradeCard()).getTranscript();
      System.out.println(converter.convertToDatabaseColumn(t));
    }
  }
}
