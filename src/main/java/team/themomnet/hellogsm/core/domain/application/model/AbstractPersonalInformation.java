package team.themomnet.hellogsm.core.domain.application.model;

import lombok.NonNull;
import team.themomnet.hellogsm.core.domain.application.model.param.AbstractPersonalInformationParameter;
import team.themomnet.hellogsm.core.domain.type.Gender;
import team.themomnet.hellogsm.core.domain.type.GraduationStatus;

import java.time.LocalDate;

public abstract sealed class AbstractPersonalInformation
    permits CandidateApplication.CandidatePersonalInformation,
    GraduateApplication.GraduatePersonalInformation,
    GedApplication.GedPersonalInformation {

  protected final String applicantImageUri;

  protected final String applicantName;

  protected final Gender applicantGender;

  protected final LocalDate applicantBirth;

  protected final String address;

  protected final String detailAddress;

  protected final GraduationStatus graduation;

  protected final String telephone;

  protected final String applicantPhoneNumber;

  protected final String guardianName;

  protected final String relationWithApplicant;

  protected AbstractPersonalInformation(@NonNull AbstractPersonalInformationParameter parameter) {
    this.applicantImageUri = parameter.getApplicantImageUri();
    this.applicantName = parameter.getApplicantName();
    this.applicantGender = parameter.getApplicantGender();
    this.applicantBirth = parameter.getApplicantBirth();
    this.address = parameter.getAddress();
    this.detailAddress = parameter.getDetailAddress();
    this.graduation = parameter.getGraduation();
    this.telephone = parameter.getTelephone();
    this.applicantPhoneNumber = parameter.getApplicantPhoneNumber();
    this.guardianName = parameter.getGuardianName();
    this.relationWithApplicant = parameter.getRelationWithApplicant();
  }
}
