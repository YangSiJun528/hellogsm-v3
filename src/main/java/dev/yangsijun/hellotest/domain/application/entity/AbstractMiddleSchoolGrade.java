package dev.yangsijun.hellotest.domain.application.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractMiddleSchoolGrade {

  @Id @GeneratedValue(strategy = GenerationType.UUID)
  protected UUID id;

  protected BigDecimal percentileRank; // 석차백분율

  public AbstractMiddleSchoolGrade(UUID id, @NonNull BigDecimal percentileRank) {
    this.id = id;
    this.percentileRank = percentileRank;
  }
}
