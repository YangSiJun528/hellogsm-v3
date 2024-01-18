package dev.yangsijun.hellotest;

import dev.yangsijun.hellotest.domain.applicant.entity.Applicant;
import dev.yangsijun.hellotest.domain.applicant.repo.ApplicantRepo;
import dev.yangsijun.hellotest.domain.applicant.type.Gender;
import dev.yangsijun.hellotest.domain.application.entity.CandidateApplication;
import dev.yangsijun.hellotest.domain.application.entity.CandidateMiddleSchoolGrade;
import dev.yangsijun.hellotest.domain.application.entity.CandidatePersonalInformation;
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

    appRepo.save(app); // 여기서 버그 발생함, transcript의 Stirng <-> Enum 변환화는 과정에서 에러가 나는데, 따로 테스트하면 에러가 안났음

    var apps = appRepo.findAll();

    if(!apps.get(0).equals(app)) {
      throw new RuntimeException("Test 실패");
    }
  }
}
