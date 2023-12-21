package team.themomnet.hellogsm.core.domain.application.model;

import java.util.List;

public interface ApplicationRepository {

  AbstractApplication findByApplicationId(Long id);

  List<AbstractApplication> findAll();

}
