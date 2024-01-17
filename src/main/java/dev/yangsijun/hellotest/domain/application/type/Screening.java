package dev.yangsijun.hellotest.domain.application.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Screening {

    /**
     * 일반전형
     */
    GENERAL(ScreeningCategory.GENERAL),

    /**
     * 특별전형
     */
    SPECIAL(ScreeningCategory.SPECIAL),

    /**
     * 국가보훈대상자
     */
    EXTRA_VETERANS(ScreeningCategory.EXTRA),

    /**
     * 특례입학대상자
     */
    EXTRA_ADMISSION(ScreeningCategory.EXTRA);

    private final ScreeningCategory category;
}
