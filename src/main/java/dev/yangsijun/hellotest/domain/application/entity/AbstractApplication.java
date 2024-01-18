package dev.yangsijun.hellotest.domain.application.entity;

import dev.yangsijun.hellotest.domain.applicant.entity.Applicant;
import dev.yangsijun.hellotest.domain.application.entity.param.AbstractApplicationStatusParameter;
import dev.yangsijun.hellotest.domain.application.type.DesiredMajors;
import dev.yangsijun.hellotest.domain.application.type.EvaluationResult;
import dev.yangsijun.hellotest.domain.application.type.EvaluationStatus;
import dev.yangsijun.hellotest.domain.application.type.Major;
import dev.yangsijun.hellotest.domain.application.type.Screening;
import jakarta.annotation.Nullable;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractApplication implements Comparable<AbstractApplication> {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  protected UUID id;

  // 지원자 개인정보를 담는 데이터, 단순히 많은 데이터를 가지고 있는다.
  @NotNull
  @OneToOne(fetch = FetchType.EAGER, optional = false)
  @Cascade(value = CascadeType.ALL)
  protected AbstractPersonalInformation personalInformation;

  @NotNull
  @OneToOne(fetch = FetchType.EAGER, optional = false)
  @Cascade(value = CascadeType.ALL)
  protected AbstractMiddleSchoolGrade gradeCard;
  // 중학교 성적 데이터를 담는 객체

  // 여기서부터 아래 객체을 하위 객체로 안 묶은 이유는 기준이 애매하거나 딱히 의미가 없기 때문
  // 의미가 없다고 한 정확한 이유: get/setter로 필드 접근&수정 작업을 진행해야 함, PersonalInformation에 비해 모을만한 기준도 없고, 모은다고 치더라도 필드 개수가 적음
  @Setter // @Setter는 꼭 필요한 코드만 적용
  @NotNull
  protected Boolean finalSubmitted; // 최종제출되었는가? - 이때부터 관리자에게 보이며, 지원자는 수정 불가능

  @Setter
  @NotNull
  protected Boolean printsArrived; // 실물 서류가 도착하였는가? - 관리자가 직접 변경함

  @Setter
  @Nullable
  @Embedded
  @AttributeOverrides({
      @AttributeOverride( name = "status", column = @Column(name = "subject_evaluation_result_status")),
      @AttributeOverride( name = "preScreeningEvaluation", column = @Column(name = "subject_evaluation_result_pre_screening")),
      @AttributeOverride( name = "postScreeningEvaluation", column = @Column(name = "subject_evaluation_result_post_screening")),
      @AttributeOverride( name = "score", column = @Column(name = "subject_evaluation_result_score"))
  })
  protected EvaluationResult subjectEvaluationResult; // 1차 평가 결과 - (가명: 학문 평가) - 평가 이전엔 null

  @Setter
  @Nullable
  @Embedded
  @AttributeOverrides({
      @AttributeOverride( name = "status", column = @Column(name = "competency_evaluation_result_status")),
      @AttributeOverride( name = "preScreeningEvaluation", column = @Column(name = "competency_evaluation_result_pre_screening")),
      @AttributeOverride( name = "postScreeningEvaluation", column = @Column(name = "competency_evaluation_result_post_screening")),
      @AttributeOverride( name = "score", column = @Column(name = "competency_evaluation_result_score"))
  })
  protected EvaluationResult competencyEvaluationResult; // 2차 평가 결과 - (가명: 역량 평가) - 평가 이전엔 null

  @Setter
  @Nullable
  protected BigDecimal competencyExamScore; // 2차 평가 점수 - 평가 이전엔 null

  @Setter
  @Nullable
  protected Long registrationNumber;  // 접수 번호, 원서 제출 기간 후 배정됨 - 이전에는 null

  @NotNull
  @Embedded
  protected DesiredMajors desiredMajors; // 희망 전공, 1,2,3 순위 - 배열을 쓰는게 맞나?

  @Setter
  @Nullable
  protected Major finalMajor; // 최종 전형, 최종 전형쳥가 이후에 전형을 가져아 함

  @NotNull
  @OneToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "applicant_id")
  protected Applicant applicant;

  protected AbstractApplication(
      UUID id,
      @NonNull AbstractPersonalInformation personalInformation,
      @NonNull AbstractMiddleSchoolGrade gradeCard,
      @NonNull AbstractApplicationStatusParameter parameter,
      @NonNull Applicant applicant) {
    this.id = id;
    this.personalInformation = personalInformation;
    this.gradeCard = gradeCard;
    this.finalSubmitted = parameter.finalSubmitted();
    this.printsArrived = parameter.printsArrived();
    this.subjectEvaluationResult = parameter.subjectEvaluationResult();
    this.competencyEvaluationResult = parameter.competencyEvaluationResult();
    this.registrationNumber = parameter.registrationNumber();
    this.desiredMajors = parameter.desiredMajors();
    this.finalMajor = parameter.finalMajor();
    this.applicant = applicant;
  }

  public Boolean isApplicationCompleted() {
    return finalSubmitted && printsArrived;
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
    return this.applicant.getName().compareTo(o.applicant.getName());
  }
}
