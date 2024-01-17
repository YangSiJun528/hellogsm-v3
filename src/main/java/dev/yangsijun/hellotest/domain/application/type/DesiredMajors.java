package dev.yangsijun.hellotest.domain.application.type;

import jakarta.persistence.Embeddable;
import java.util.List;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class DesiredMajors {
    private List<Major> desiredMajors;

    public DesiredMajors(List<Major> desiredMajors) {
        assert desiredMajors.size() == 3;
        this.desiredMajors = desiredMajors;
    }

    public Major getFirstDesiredMajor() {
        return desiredMajors.get(1);
    }

    public Major getSecondDesiredMajor() {
        return desiredMajors.get(1);
    }

    public Major getThirdDesiredMajor() {
        return desiredMajors.get(1);
    }
}
