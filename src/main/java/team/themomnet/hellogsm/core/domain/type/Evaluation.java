package team.themomnet.hellogsm.core.domain.type;

import java.math.BigDecimal;
import java.util.Objects;

public class Evaluation {

    private EvaluationStatus evaluation; // 실패, 성공
    private Screening preScreeningEvaluation; // 평가 이전 시점의 전형
    private Screening postScreeningEvaluation; // 평가 이후 시점의 전형
    private BigDecimal score; // 평가 기준 점수

    public boolean isPass() {
        return Objects.equals(evaluation, EvaluationStatus.PASS);
    }
}
