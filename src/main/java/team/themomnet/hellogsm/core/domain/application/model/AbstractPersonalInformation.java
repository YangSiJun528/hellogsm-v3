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

  protected String applicantImageUri;

  protected String applicantName;

  protected Gender applicantGender;

  protected LocalDate applicantBirth;

  protected String address;

  protected String detailAddress;

  protected GraduationStatus graduation;

  protected String telephone;

  protected String applicantPhoneNumber;

  protected String guardianName;

  protected String relationWithApplicant;

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
