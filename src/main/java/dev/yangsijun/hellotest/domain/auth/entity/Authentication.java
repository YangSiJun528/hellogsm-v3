package dev.yangsijun.hellotest.domain.auth.entity;

import dev.yangsijun.hellotest.domain.auth.type.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authentication {
  @Id
  private Long id;
  private String providerId;
  private String providerName;
  private Role role;
}
