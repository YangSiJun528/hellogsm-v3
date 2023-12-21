package team.themomnet.hellogsm.core.domain.application.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import team.themomnet.hellogsm.core.domain.type.Gender;
import team.themomnet.hellogsm.core.domain.type.GraduationStatus;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract sealed class AbstractPersonalInformation permits
    CandidateApplication.CandidatePersonalInformation,
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

}
