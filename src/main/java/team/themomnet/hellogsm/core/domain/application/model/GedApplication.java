package team.themomnet.hellogsm.core.domain.application.model;

import org.jetbrains.annotations.Nullable;
import team.themomnet.hellogsm.core.domain.type.*;

import java.time.LocalDate;
import java.util.concurrent.Future;

public class GedApplication extends AbstractApplication {

    protected GedApplication(GedPersonalInformation personalInformation, GedApplication.GedGradeCard gradeCard, Future<String> aa, Boolean finalSubmitted, Boolean printsArrived, @Nullable Evaluation subjectEvaluation, @Nullable Evaluation competencyEvaluation, @Nullable Long registrationNumber, DesiredMajors desiredMajors, Major finalMajor) {
        super(personalInformation, gradeCard, aa, finalSubmitted, printsArrived, subjectEvaluation, competencyEvaluation, registrationNumber, desiredMajors, finalMajor);
    }

    public class GedPersonalInformation extends AbstractPersonalInformation{
        // 추가로 필요한 정보 없음

        protected GedPersonalInformation(String applicantImageUri, String applicantName, Gender applicantGender, LocalDate applicantBirth, String address, String detailAddress, GraduationStatus graduation, String telephone, String applicantPhoneNumber, String guardianName, String relationWithApplicant) {
            super(applicantImageUri, applicantName, applicantGender, applicantBirth, address, detailAddress, graduation, telephone, applicantPhoneNumber, guardianName, relationWithApplicant);
        }
    }

    public class GedGradeCard extends AbstractGradeCard {
    }
}
