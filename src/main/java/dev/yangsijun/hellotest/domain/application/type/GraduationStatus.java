package dev.yangsijun.hellotest.domain.application.type;

public enum GraduationStatus {
    /**
     * 졸업예정자
     * 입학 서비스 년도 당년에 졸업을 앞둔 학생
     */
    CANDIDATE,
    /**
     * 졸업자
     */
    GRADUATE,
    /**
     * 검정고시
     */
    GED;

    public static class Values {
        public static final String CANDIDATE = "CANDIDATE";
        public static final String GRADUATE = "GRADUATE";
        public static final String GED = "GED";
    }
}
