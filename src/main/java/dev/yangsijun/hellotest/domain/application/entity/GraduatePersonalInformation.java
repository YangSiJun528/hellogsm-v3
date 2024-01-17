package dev.yangsijun.hellotest.domain.application.entity;

import dev.yangsijun.hellotest.domain.application.entity.param.AbstractPersonalInformationParameter;
import jakarta.persistence.Entity;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class GraduatePersonalInformation extends AbstractPersonalInformation {

  private String schoolName;

  private String schoolLocation;

  private String teacherName;

  private String teacherPhoneNumber;

  GraduatePersonalInformation(
      @NonNull UUID id,
      @NonNull AbstractPersonalInformationParameter superParameter,
      @NonNull String schoolName,
      @NonNull String schoolLocation,
      @NonNull String teacherName,
      @NonNull String teacherPhoneNumber) {
    super(id, superParameter);
    this.schoolName = schoolName;
    this.schoolLocation = schoolLocation;
    this.teacherName = teacherName;
    this.teacherPhoneNumber = teacherPhoneNumber;
  }
}
