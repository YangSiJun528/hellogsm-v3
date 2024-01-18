package dev.yangsijun.hellotest.domain.application.entity;

import dev.yangsijun.hellotest.domain.application.entity.convert.MiddleSchoolTranscriptConverter;
import dev.yangsijun.hellotest.domain.application.entity.param.CandidateMiddleSchoolGradeParameter;
import dev.yangsijun.hellotest.domain.application.type.MiddleSchoolTranscript;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class CandidateMiddleSchoolGrade extends AbstractMiddleSchoolGrade
    implements Comparable<CandidateMiddleSchoolGrade> {

  // 근데 이거 자율학기제/학년제 때문에 다른 상태를 가질수도 있음
  // 리스트나 그련 형태를 가지는게 좋을거 같음

  @Convert(converter = MiddleSchoolTranscriptConverter.class)
  private MiddleSchoolTranscript transcript;// 중학교 성적증명서

  private BigDecimal grade1Semester1Score; // 1 grade(학년) 1 semester(학기) 점수

  private BigDecimal grade1Semester2Score;

  private BigDecimal grade2Semester1Score;

  private BigDecimal grade2Semester2Score;

  private BigDecimal grade3Semester1Score;

  private BigDecimal artisticScore; // 예체능 점수

  // 이거 굳이 필드로 저장할 필요 있나? 메서드로 만들어도 될거 같은데,
  // -> 오브젝트 매퍼가 프로퍼티(필드 + get/setter)를 참고해서 변수로 선언해줘야 함 - https://sedangdang.tistory.com/307
  // -> 대신 외부에서 입력받지 않고, 직접 계산해서 넣거나 할 수 있음
  // -> 근데 그런걸 외부 계산 객체에서 할꺼라 냅두는게 나을듯
  private BigDecimal curricularSubtotalScore; // 교과 성적 소계

  private BigDecimal attendanceScore; // 출석 점수

  private BigDecimal volunteerScore; // 봉사 점수

  private BigDecimal extraCurricularSubtotalScore; // 비교과 성적 소계

  private BigDecimal totalScore; // 합계 - 환산총점


  public CandidateMiddleSchoolGrade(
      UUID id,
      @NonNull CandidateMiddleSchoolGradeParameter parameter) {
    super(id, parameter.getPercentileRank());
    this.transcript = parameter.getTranscript();
    this.grade1Semester1Score = parameter.getGrade1Semester1Score();
    this.grade1Semester2Score = parameter.getGrade1Semester2Score();
    this.grade2Semester1Score = parameter.getGrade2Semester1Score();
    this.grade2Semester2Score = parameter.getGrade2Semester2Score();
    this.grade3Semester1Score = parameter.getGrade3Semester1Score();
    this.artisticScore = parameter.getArtisticScore();
    this.curricularSubtotalScore = parameter.getCurricularSubtotalScore();
    this.attendanceScore = parameter.getAttendanceScore();
    this.volunteerScore = parameter.getVolunteerScore();
    this.extraCurricularSubtotalScore = parameter.getExtraCurricularSubtotalScore();
    this.totalScore = parameter.getTotalScore();
  }

  @Override
  public int compareTo(CandidateMiddleSchoolGrade o) {
    // 교과성적(예·체능 교과포함) 순
    int result = this.curricularSubtotalScore.compareTo(o.curricularSubtotalScore);
    if (result == 0) {
      // 교과성적(예·체능 교과 미포함)중 3학년 점수
      result = this.grade3Semester1Score.compareTo(o.grade3Semester1Score);
    }
    if (result == 0) {
      // 교과성적(예·체능 교과 미포함)중 2학년 점수
      result = (sum(this.grade2Semester1Score, this.grade2Semester2Score))
          .compareTo((sum(o.grade2Semester1Score, o.grade2Semester2Score)));
    }
    if (result == 0) {
      // 2학년 2학기 교과성적 (예·체능교과 미포함)
      result = this.grade3Semester1Score.compareTo(o.grade3Semester1Score);
    }
    if (result == 0) {
      // 2학년 1학기 교과성적 (예·체능교과 미포함)
      result = this.grade3Semester1Score.compareTo(o.grade3Semester1Score);
    }
    if (result == 0) {
      // 비교과 성적
      result = this.grade3Semester1Score.compareTo(o.grade3Semester1Score);
    }
    return result;
  }

  private BigDecimal sum(BigDecimal a, BigDecimal b) {
    BigDecimal num = BigDecimal.ZERO;
    num = num.add(a);
    num = num.add(b);
    return num;
  }
}
