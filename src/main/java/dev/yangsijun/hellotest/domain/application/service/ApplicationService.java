package dev.yangsijun.hellotest.domain.application.service;

import dev.yangsijun.hellotest.domain.application.entity.AbstractApplication;
import dev.yangsijun.hellotest.domain.application.entity.command.ApplicationCommand;
import dev.yangsijun.hellotest.domain.application.entity.factory.ApplicationFactory;
import dev.yangsijun.hellotest.domain.auth.entity.Authentication;

public class ApplicationService {
  public AbstractApplication create(Authentication auth, ApplicationCommand cmd) {
    // Todo valid
    AbstractApplication application = ApplicationFactory.createApplication(cmd);
    // TODO Save
    return application;
  }

  public AbstractApplication modify(Authentication auth, ApplicationCommand cmd) {
    // Todo valid
    AbstractApplication application = ApplicationFactory.createApplication(cmd);
    // TODO Save
    return application;
  }
}
