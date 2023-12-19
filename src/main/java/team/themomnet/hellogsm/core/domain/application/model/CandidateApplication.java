package team.themomnet.hellogsm.core.domain.application.model;


import org.jetbrains.annotations.Nullable;
import team.themomnet.hellogsm.core.domain.type.*;

import java.time.LocalDate;
import java.util.concurrent.Future;

public class CandidateApplication extends AbstractApplication {

    protected CandidateApplication(CandidateApplication.CandidatePersonalInformation personalInformation, CandidateApplication.CandidateGradeCard gradeCard, Future<String> aa, Boolean finalSubmitted, Boolean printsArrived, @Nullable Evaluation subjectEvaluation, @Nullable Evaluation competencyEvaluation, @Nullable Long registrationNumber, DesiredMajors desiredMajors, Major finalMajor) {
        super(personalInformation, gradeCard, aa, finalSubmitted, printsArrived, subjectEvaluation, competencyEvaluation, registrationNumber, desiredMajors, finalMajor);
    }

    public static class CandidatePersonalInformation extends AbstractPersonalInformation {
        private final String schoolName;

        private final String schoolLocation;

        private final String teacherName;

        private final String teacherPhoneNumber;

        protected CandidatePersonalInformation(String applicantImageUri, String applicantName, Gender applicantGender, LocalDate applicantBirth, String address, String detailAddress, GraduationStatus graduation, String telephone, String applicantPhoneNumber, String guardianName, String relationWithApplicant, String schoolName, String schoolLocation, String teacherName, String teacherPhoneNumber) {
            super(applicantImageUri, applicantName, applicantGender, applicantBirth, address, detailAddress, graduation, telephone, applicantPhoneNumber, guardianName, relationWithApplicant);
            this.schoolName = schoolName;
            this.schoolLocation = schoolLocation;
            this.teacherName = teacherName;
            this.teacherPhoneNumber = teacherPhoneNumber;
        }
    }

    public static class CandidateGradeCard extends AbstractGradeCard {
    }
}
