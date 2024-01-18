package dev.yangsijun.hellotest.domain.application.entity.factory;

import dev.yangsijun.hellotest.domain.application.entity.AbstractApplication;
import dev.yangsijun.hellotest.domain.application.entity.CandidateApplication;
import dev.yangsijun.hellotest.domain.application.entity.GedApplication;
import dev.yangsijun.hellotest.domain.application.entity.GraduateApplication;
import dev.yangsijun.hellotest.domain.application.entity.command.ApplicationCommand;
import dev.yangsijun.hellotest.domain.application.entity.command.CandidateApplicationCommand;
import dev.yangsijun.hellotest.domain.application.entity.command.GedApplicationCommand;
import dev.yangsijun.hellotest.domain.application.entity.command.GraduateApplicationCommand;

public class ApplicationFactory {

  public static AbstractApplication createApplication(ApplicationCommand cmd) {
    if (cmd instanceof CandidateApplicationCommand candidateCmd) {
      return createCandidate(candidateCmd);
    } else if (cmd instanceof GraduateApplicationCommand graduateCmd) {
      return createGraduate(graduateCmd);
    } else if (cmd instanceof GedApplicationCommand gedCmd) {
      return createGed(gedCmd);
    } else {
      throw new RuntimeException();
    }
  }

  private static CandidateApplication createCandidate(CandidateApplicationCommand candidateCmd) {
    // TODO implement
    return null;
  }

  private static GraduateApplication createGraduate(GraduateApplicationCommand graduateCmd) {
    // TODO implement
    return null;
  }


  private static GedApplication createGed(GedApplicationCommand gedCmd) {
    // TODO implement
    return null;
  }


}
