package dev.yangsijun.hellotest.domain.application.entity.command;


import dev.yangsijun.hellotest.domain.application.type.GraduationStatus;

public interface ApplicationCommand {
  GraduationStatus getGraduationStatus();
}
