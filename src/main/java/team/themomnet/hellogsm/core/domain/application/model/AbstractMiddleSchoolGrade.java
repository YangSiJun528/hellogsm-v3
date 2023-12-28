package team.themomnet.hellogsm.core.domain.application.model;


import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team.themomnet.hellogsm.core.domain.application.model.CandidateApplication.CandidateMiddleSchoolGrade;
import team.themomnet.hellogsm.core.domain.application.model.GedApplication.GedMiddleSchoolGrade;
import team.themomnet.hellogsm.core.domain.application.model.GraduateApplication.GraduateMiddleSchoolGrade;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract sealed class AbstractMiddleSchoolGrade permits CandidateMiddleSchoolGrade,
    GraduateMiddleSchoolGrade, GedMiddleSchoolGrade {

  private final BigDecimal percentileRank; // 석차백분율

}
