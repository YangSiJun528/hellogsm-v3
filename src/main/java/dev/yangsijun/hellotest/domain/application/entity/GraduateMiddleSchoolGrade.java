package dev.yangsijun.hellotest.domain.application.entity;

import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class GraduateMiddleSchoolGrade extends AbstractMiddleSchoolGrade {

  private BigDecimal attendanceScore; // 출석 점수

  private BigDecimal volunteerScore; // 봉사 점수

  public GraduateMiddleSchoolGrade(
      @NonNull UUID id,
      @NonNull BigDecimal percentileRank,
      @NonNull BigDecimal attendanceScore,
      @NonNull BigDecimal volunteerScore) {
    super(id, percentileRank);
    this.attendanceScore = attendanceScore;
    this.volunteerScore = volunteerScore;
  }
}
