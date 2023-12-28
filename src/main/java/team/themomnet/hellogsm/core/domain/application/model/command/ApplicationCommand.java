package team.themomnet.hellogsm.core.domain.application.model.command;

import team.themomnet.hellogsm.core.domain.type.GraduationStatus;

public interface ApplicationCommand {
  GraduationStatus getGraduationStatus();
}
