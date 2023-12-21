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

  //TODO 이제 문제는 파생 데이터를 저장할 필요가 있는가?
  // 졸업 예정, 검정고시는 중학교 내신 / 시험 점수를 기반으로 석차백분율(percentileRank)을 구함,
  // 그럼 이걸 변수로 따로 저장할 필요가 있나?
  // 함수를 사용해서 매번 계산해서 주면 되는거 아닌가?
  // DB에 저장해놓으면 만약 DB값을 직접 수정하면 문제가 될 것 같은데...
  // 변수로 저장은 하되, 생성 시에 직접 만드는 것도 ㄱㅊ을거 같고
  // 생성자가 많은 일을 하는건 좋지 않지만(콜라보레이터를 생성하지 말라는 말이라 ㄱㅊ)
  // 그리고 또, 변수로 가지고 있어야 직렬화 문제가 없지 않나?
  // 근데 그거는 다른 변수들(소계)도 마찬가지임
  private final BigDecimal percentileRank;

}
