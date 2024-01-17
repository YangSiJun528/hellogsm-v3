package dev.yangsijun.hellotest.domain.application.entity;

import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class GedMiddleSchoolGrade extends AbstractMiddleSchoolGrade {

  private BigDecimal gedTotalScore; // GED 시험 총점

  private BigDecimal gedMaxScore; // GED 시험 만점 - 과목수 * 100

  public GedMiddleSchoolGrade(
      @NonNull UUID id,
      @NonNull BigDecimal percentileRank,
      @NonNull BigDecimal gedTotalScore,
      @NonNull BigDecimal gedMaxScore) {
    super(id, percentileRank);
    this.gedTotalScore = gedTotalScore;
    this.gedMaxScore = gedMaxScore;
  }
}
