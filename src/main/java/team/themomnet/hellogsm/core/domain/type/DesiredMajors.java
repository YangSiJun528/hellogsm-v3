package team.themomnet.hellogsm.core.domain.type;

import java.util.List;

public class DesiredMajors {
    private final List<Major> desiredMajors;

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
