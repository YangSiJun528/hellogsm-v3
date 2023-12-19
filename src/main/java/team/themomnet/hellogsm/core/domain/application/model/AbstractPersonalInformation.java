package team.themomnet.hellogsm.core.domain.application.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import team.themomnet.hellogsm.core.domain.type.Gender;
import team.themomnet.hellogsm.core.domain.type.GraduationStatus;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractPersonalInformation {
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
}
