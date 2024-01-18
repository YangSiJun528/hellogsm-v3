package dev.yangsijun.hellotest.domain.applicant.repo;

import dev.yangsijun.hellotest.domain.applicant.entity.Applicant;
import dev.yangsijun.hellotest.domain.application.entity.AbstractApplication;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepo extends JpaRepository<Applicant, Long> {
}
