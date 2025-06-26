# 1. 프로젝트 개요
본 프로젝트는 사내 출입 관리 시스템의 출입 기록을 ERP 시스템의 근태 데이터와 주기적으로 동기화하는 서비스의 프로토타입입니다.

실제 회사 시스템에 적용하기에 앞서, 안전한 환경에서 아키텍처를 설계하고 핵심 로직의 기술적 타당성을 검증하는 것을 목표로 합니다.

# 2. 프로젝트 배경 및 목적
## 배경
회사 내 출입 관리 시스템과 ERP 시스템의 근태 데이터를 연동하여, 수작업으로 이루어지던 출퇴근 시간 기록 및 관리를 자동화해야 하는 과제가 있습니다.

하지만 현재, 출입 관리 시스템으로부터 데이터를 어떤 방식으로 제공받을 수 있을지(예: REST API, DB 직접 접근, 파일 Export 등) 아직 확정되지 않은 불확실한 상황입니다.

## 프로토타입의 목적
이러한 불확실한 상황에서 무작정 개발에 착수하는 대신, 다음과 같은 목적을 달성하기 위해 본 프로토타입을 우선적으로 제작합니다.

* 안정적인 아키텍처 설계: 향후 데이터 제공 방식이 어떻게 결정되더라도 최소한의 코드 수정으로 유연하게 대응할 수 있는 확장 가능한 구조를 사전에 설계하고 검증합니다.

* 핵심 로직 검증: 실제 시스템에 영향을 주지 않는 격리된 환경에서, 데이터 동기화의 핵심 로직(증분 데이터 처리, 스케줄링, 상태 관리 등)을 구현하고 안정성을 테스트합니다.

* 효율적인 개발: 불확실한 외부 요인(네트워크, 인증 등)을 배제하고 순수한 비즈니스 로직 개발에 집중하여 전체 개발 시간을 단축합니다.

# 3. 핵심 설계 구상
이 프로토타입은 다음과 같은 핵심 설계 사상을 기반으로 구현될 예정입니다.

1. 데이터 획득 로직의 추상화:
데이터를 가져오는 로직을 인터페이스로 추상화하여, 실제 데이터 획득 방식(API, DB 등)을 '부품'처럼 쉽게 교체할 수 있도록 설계합니다.

2. 증분 동기화 (Incremental Sync):
매번 전체 데이터를 처리하는 비효율을 방지하기 위해, 마지막으로 처리한 데이터의 위치를 기록하고 그 이후의 변경분만 가져와 처리하는 방식으로 시스템 부하를 최소화합니다.

# 4. 적용 예정 기술 
Language: <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white"/>

Framework: <img src="https://img.shields.io/badge/Spring Boot-3.5.3-brightgreen?logo=spring&logoColor=white"/>

Data Persistence: <img src="https://img.shields.io/badge/Spring Data JPA-4C8A2C?logo=spring&logoColor=white"/> <img src="https://img.shields.io/badge/H2 Database-596D78?logo=h2&logoColor=white"/>

Build Tool: <img src="https://img.shields.io/badge/Gradle-02303A?logo=gradle&logoColor=white"/>
