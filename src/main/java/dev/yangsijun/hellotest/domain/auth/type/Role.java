package dev.yangsijun.hellotest.domain.auth.type;

public enum Role {
  APPLICANT,
  ADMIN, // 선생님 & 개발 팀원
  ROOT // 개발 팀원 - 배치 실행, 특정 상태 관리 등 수행 담당
}
