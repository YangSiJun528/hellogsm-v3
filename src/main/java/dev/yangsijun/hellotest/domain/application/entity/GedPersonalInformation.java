package dev.yangsijun.hellotest.domain.application.entity;

import dev.yangsijun.hellotest.domain.application.entity.param.AbstractPersonalInformationParameter;
import jakarta.persistence.Entity;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class GedPersonalInformation extends AbstractPersonalInformation {

  // 추가로 필요한 정보 없음
  private GedPersonalInformation(@NonNull UUID id, @NonNull AbstractPersonalInformationParameter superParameter) {
    super(id, superParameter);
  }
}
