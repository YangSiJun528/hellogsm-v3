package dev.yangsijun.hellotest.domain.application.entity.param;

import dev.yangsijun.hellotest.domain.applicant.type.Gender;
import dev.yangsijun.hellotest.domain.application.type.GraduationStatus;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class AbstractPersonalInformationParameter {

  private final String applicantImageUri;

  private final String address;

  private final String detailAddress;

  private final GraduationStatus graduation;

  private final String telephone;

  private final String guardianName;

  private final String relationWithApplicant;

  @Builder
  public AbstractPersonalInformationParameter(
      @NonNull String applicantImageUri,
      @NonNull String address,
      @NonNull String detailAddress,
      @NonNull GraduationStatus graduation,
      @NonNull String telephone,
      @NonNull String guardianName,
      @NonNull String relationWithApplicant) {
    this.applicantImageUri = applicantImageUri;
    this.address = address;
    this.detailAddress = detailAddress;
    this.graduation = graduation;
    this.telephone = telephone;
    this.guardianName = guardianName;
    this.relationWithApplicant = relationWithApplicant;
  }
}
