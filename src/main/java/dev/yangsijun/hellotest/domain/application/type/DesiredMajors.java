package dev.yangsijun.hellotest.domain.application.type;

import jakarta.persistence.Embeddable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class DesiredMajors {
    private Major firstDesiredMajor;
    private Major secondDesiredMajor;
    private Major thirdDDesiredMajor;

    public DesiredMajors(List<Major> desiredMajors) {
        assert desiredMajors.size() == 3;
        this.firstDesiredMajor = desiredMajors.get(0);
        this.secondDesiredMajor = desiredMajors.get(1);
        this.thirdDDesiredMajor = desiredMajors.get(2);
    }
}
