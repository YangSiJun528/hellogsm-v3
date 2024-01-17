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

  private final String applicantName;

  private final Gender applicantGender;

  private final LocalDate applicantBirth;

  private final String address;

  private final String detailAddress;

  private final GraduationStatus graduation;

  private final String telephone;

  private final String applicantPhoneNumber;

  private final String guardianName;

  private final String relationWithApplicant;

  @Builder
  public AbstractPersonalInformationParameter(
      @NonNull String applicantImageUri,
      @NonNull String applicantName,
      @NonNull Gender applicantGender,
      @NonNull LocalDate applicantBirth,
      @NonNull String address, String detailAddress,
      @NonNull GraduationStatus graduation,
      @NonNull String telephone,
      @NonNull String applicantPhoneNumber,
      @NonNull String guardianName,
      @NonNull String relationWithApplicant) {
    this.applicantImageUri = applicantImageUri;
    this.applicantName = applicantName;
    this.applicantGender = applicantGender;
    this.applicantBirth = applicantBirth;
    this.address = address;
    this.detailAddress = detailAddress;
    this.graduation = graduation;
    this.telephone = telephone;
    this.applicantPhoneNumber = applicantPhoneNumber;
    this.guardianName = guardianName;
    this.relationWithApplicant = relationWithApplicant;
  }
}
