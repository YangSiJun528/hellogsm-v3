package dev.yangsijun.hellotest.domain.application.type;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class EvaluationResult {

    private EvaluationStatus status; // 실패, 성공
    private Screening preScreeningEvaluation; // 평가 이전 시점의 전형
    private Screening postScreeningEvaluation; // 평가 이후 시점의 전형
    private BigDecimal score; // 평가 기준 점수

    public boolean isPass() {
        return Objects.equals(status, EvaluationStatus.PASS);
    }
}
