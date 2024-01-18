package dev.yangsijun.hellotest.domain.application.entity;

import dev.yangsijun.hellotest.domain.application.entity.param.AbstractPersonalInformationParameter;
import dev.yangsijun.hellotest.domain.application.type.GraduationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractPersonalInformation {

  @Id @GeneratedValue(strategy = GenerationType.UUID)
  protected UUID id;

  protected String applicantImageUri;

  protected String address;

  protected String detailAddress;

  protected GraduationStatus graduation;

  protected String telephone;

  protected String guardianName;

  protected String relationWithApplicant;

  protected AbstractPersonalInformation(UUID id, @NonNull AbstractPersonalInformationParameter parameter) {
    this.id = id;
    this.applicantImageUri = parameter.getApplicantImageUri();
    this.address = parameter.getAddress();
    this.detailAddress = parameter.getDetailAddress();
    this.graduation = parameter.getGraduation();
    this.telephone = parameter.getTelephone();
    this.guardianName = parameter.getGuardianName();
    this.relationWithApplicant = parameter.getRelationWithApplicant();
  }
}
