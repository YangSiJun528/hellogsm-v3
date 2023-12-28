package team.themomnet.hellogsm.core.domain.application.model;


import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import team.themomnet.hellogsm.core.domain.application.model.CandidateApplication.CandidateMiddleSchoolGrade;
import team.themomnet.hellogsm.core.domain.application.model.GedApplication.GedMiddleSchoolGrade;
import team.themomnet.hellogsm.core.domain.application.model.GraduateApplication.GraduateMiddleSchoolGrade;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract sealed class AbstractMiddleSchoolGrade permits CandidateMiddleSchoolGrade,
    GraduateMiddleSchoolGrade, GedMiddleSchoolGrade {

  private BigDecimal percentileRank;

}
