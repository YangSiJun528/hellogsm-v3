package team.themomnet.hellogsm.core.domain.application.model;

import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import team.themomnet.hellogsm.core.domain.type.DesiredMajors;
import team.themomnet.hellogsm.core.domain.type.Evaluation;
import team.themomnet.hellogsm.core.domain.type.Major;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract sealed class AbstractApplication permits CandidateApplication, GraduateApplication,
    GedApplication {

  // 지원자 개인정보를 담는 데이터, 단순히 많은 데이터를 가지고 있는다.
  protected final AbstractPersonalInformation personalInformation;

  // 중학교 성적 데이터를 담는 객체
  protected final AbstractMiddleSchoolGrade gradeCard;

  // 여기서부터 아래 객체을 하위 객체로 안 묶은 이유는 기준이 애매하거나 딱히 의미가 없기 때문
  // 의미가 없다고 한 정확한 이유: get/setter로 필드 접근&수정 작업을 진행해야 함, 순수 데이터 분리용으로만 쓰이지만 PersonalInformation에 비해 모을만한 기준도 없고, 필드 개수도 적음
  protected final Boolean finalSubmitted; // 최종제출되었는가? - 이때부터 관리자에게 보이며, 지원자는 수정 불가능

  protected final Boolean printsArrived; // 실물 서류가 도착하였는가? - 관리자가 직접 변경함

  @Nullable
  protected final Evaluation subjectEvaluation; // 1차 평가 - (가명: 학문 평가) - 평가 이전엔 null

  @Nullable
  protected final Evaluation competencyEvaluation; // 1차 평가 - (가명: 역량 평가) - 평가 이전엔 null

  @Nullable
  protected final Long registrationNumber;  // 접수 번호, 원서 제출 기간 후 배정됨 - 이전에는 null

  protected final DesiredMajors desiredMajors; // 희망 전공, 1,2,3 순위 - 배열을 쓰는게 맞나?

  protected final Major finalMajor; // 최종 전형, 최종 전형쳥가 이후에 전형을 가져아 함

}
