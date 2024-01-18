package dev.yangsijun.hellotest.domain.application.repo;

import dev.yangsijun.hellotest.domain.application.entity.AbstractApplication;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepo extends JpaRepository<AbstractApplication, UUID> {
}
