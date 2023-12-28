package team.themomnet.hellogsm.core.domain.application.model;

import jakarta.annotation.Nullable;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.boot.model.source.spi.Sortable;
import team.themomnet.hellogsm.core.domain.application.model.CandidateApplication.CandidateMiddleSchoolGrade;
import team.themomnet.hellogsm.core.domain.application.model.param.AbstractApplicationParameter;
import team.themomnet.hellogsm.core.domain.type.DesiredMajors;
import team.themomnet.hellogsm.core.domain.type.Evaluation;
import team.themomnet.hellogsm.core.domain.type.Major;

public abstract sealed class AbstractApplication implements Comparable<AbstractApplication> permits
    CandidateApplication, GraduateApplication, GedApplication {

  // 지원자 개인정보를 담는 데이터, 단순히 많은 데이터를 가지고 있는다.
  protected final AbstractPersonalInformation personalInformation;

  // 중학교 성적 데이터를 담는 객체
  protected final AbstractMiddleSchoolGrade gradeCard;

  // 여기서부터 아래 객체을 하위 객체로 안 묶은 이유는 기준이 애매하거나 딱히 의미가 없기 때문
  // 의미가 없다고 한 정확한 이유: get/setter로 필드 접근&수정 작업을 진행해야 함, 순수 데이터 분리용으로만 쓰이지만 PersonalInformation에 비해 모을만한 기준도 없고, 필드 개수도 적음
  @Setter // @Setter는 꼭 필요한 코드만 적용
  protected Boolean finalSubmitted; // 최종제출되었는가? - 이때부터 관리자에게 보이며, 지원자는 수정 불가능

  @Setter
  protected Boolean printsArrived; // 실물 서류가 도착하였는가? - 관리자가 직접 변경함

  @Setter
  @Nullable
  protected Evaluation subjectEvaluation; // 1차 평가 - (가명: 학문 평가) - 평가 이전엔 null

  @Setter
  @Nullable
  protected Evaluation competencyEvaluation; // 2차 평가 - (가명: 역량 평가) - 평가 이전엔 null

  @Setter
  @Nullable
  protected BigDecimal competencyExamScore; // 2차 평가 - (가명: 역량 평가) - 평가 이전엔 null

  @Setter
  @Nullable
  protected Long registrationNumber;  // 접수 번호, 원서 제출 기간 후 배정됨 - 이전에는 null

  protected final DesiredMajors desiredMajors; // 희망 전공, 1,2,3 순위 - 배열을 쓰는게 맞나?

  @Setter
  protected Major finalMajor; // 최종 전형, 최종 전형쳥가 이후에 전형을 가져아 함

  protected AbstractApplication(
      @NonNull AbstractPersonalInformation personalInformation,
      @NonNull AbstractMiddleSchoolGrade gradeCard,
      @NonNull AbstractApplicationParameter parameter) {
    this.personalInformation = personalInformation;
    this.gradeCard = gradeCard;
    this.finalSubmitted = parameter.getFinalSubmitted();
    this.printsArrived = parameter.getPrintsArrived();
    this.subjectEvaluation = parameter.getSubjectEvaluation();
    this.competencyEvaluation = parameter.getCompetencyEvaluation();
    this.registrationNumber = parameter.getRegistrationNumber();
    this.desiredMajors = parameter.getDesiredMajors();
    this.finalMajor = parameter.getFinalMajor();
  }

  @Override
  public int compareTo(AbstractApplication o) {
    // 석차백분율 비교
    int result = this.gradeCard.getPercentileRank().compareTo(o.gradeCard.getPercentileRank());

    // 동점이 아니면 결과 반환
    if (result != 0) {
      return result;
    }

    // 만약 동점이고 둘 다 졸업 예정자라면 (중학교 성적 점수를 가지고 있음)
    if (o instanceof CandidateApplication && this instanceof CandidateApplication) {
      CandidateMiddleSchoolGrade thisGradeCard = (CandidateMiddleSchoolGrade) this.gradeCard;
      CandidateMiddleSchoolGrade oGradeCard = (CandidateMiddleSchoolGrade) o.gradeCard;

      // 중학교 성적을 기반으로 비교
      int compareToByGrade = thisGradeCard.compareTo(oGradeCard);

      // 동점이 아니면 결과 반환
      if (compareToByGrade != 0) {
        return compareToByGrade;
      }

    } else if (this instanceof CandidateApplication) {
      return 1;
    } else if (o instanceof CandidateApplication) { // 일부만 졸업 예정자라면, 졸업 예정자가 더 큼
      return -1;
    }

    // 아래의 조건 중 하나라면, 이름을 기준으로 정렬 이후, 결과 반환
    // 1. 둘 다 졸업예정자이고, 중학교 성적을 기반으로 비교해도 동점이 경우
    // 2. 둘 다 졸업예정자가 아니고, 석차백분율이 동일한 경우
    return this.personalInformation.applicantName.compareTo(o.personalInformation.applicantName);
  }

}
