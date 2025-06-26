# 출입 시스템 -> ERP 근태 연동 (프로토타입)

# 1. 프로젝트 목표
본 프로젝트는 사내 출입 관리 시스템의 기록을 ERP 시스템의 근태 데이터와 동기화하는 요구사항을 해결하기 위해 제작되었습니다. 특히, 소스 시스템(출입 관리 시스템)의 데이터 제공 방식이 미확정인 상황에서 아래의 목표를 달성하는 데 중점을 둡니다.

* ✅ __유연한 아키텍처 설계__: 향후 어떤 데이터 소스(API, DB, File)가 결정되더라도 최소한의 수정으로 빠르게 대응할 수 있는 확장성을 확보합니다.

* ✅ __안정적인 데이터 처리__: 전체 데이터가 아닌 변경분만 처리하는 증분 동기화(Incremental Sync) 방식을 적용하여 시스템 부하와 리소스를 최소화합니다.

* ✅ __독립적인 개발 및 테스트 환경__: 실제 외부 시스템에 의존하지 않고, 내장된 Mock API 서버를 통해 프로젝트의 모든 기능을 독립적으로 개발하고 검증할 수 있는 환경을 구축합니다.

# 2. 핵심 아키텍처
이 프로젝트는 __전략 패턴(Strategy Pattern)__ 과 __상태 기반(Stateful)__ 접근 방식을 결합하여 설계되었습니다.

* __전략 패턴을 통한 유연성 확보__: 데이터 획득 로직을 AccessLogFetcher 인터페이스로 추상화했습니다. 현재는 내장된 Mock API를 호출하는 MockApiAccessLogFetcher가 이 전략을 수행하고 있습니다. 향후 DB 직접 접근이 필요하다면 DbAccessLogFetcher를, 파일 처리가 필요하다면 FileAccessLogFetcher를 구현하여 '부품'처럼 교체하기만 하면 됩니다.

* __상태 기반의 증분 동기화__: 마지막으로 처리한 데이터의 ID를 H2 인메모리 DB의 SyncStatus 테이블에 기록합니다. 스케줄러가 실행될 때마다 이 "책갈피"를 참조하여, 이전에 처리하지 않은 새로운 데이터만을 효율적으로 가져옵니다.

* __내장 Mock API를 통한 독립 환경__: MockApiController는 외부 시스템을 흉내 내어, 개발자가 자신의 로컬 환경에서 네트워크나 인증 문제없이 핵심 로직 개발에만 집중할 수 있게 해줍니다.

### 전체 실행 흐름 다이어그램

![image](https://github.com/user-attachments/assets/71af6c18-a5ca-4b14-a119-72ce4d78f16d)

# 3. 기술 스택
* Language: <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white"/>

* Framework: <img src="https://img.shields.io/badge/Spring Boot-3.5.3-brightgreen?logo=spring&logoColor=white"/>

* Data Persistence: <img src="https://img.shields.io/badge/Spring Data JPA-4C8A2C?logo=spring&logoColor=white"/> <img src="https://img.shields.io/badge/H2 Database-596D78?logo=h2&logoColor=white"/>

* Build Tool: <img src="https://img.shields.io/badge/Gradle-02303A?logo=gradle&logoColor=white"/>
# 4. 실행 방법

### 사전 요구사항
* JAVA 21
* Git

### 실행 순서

1. 저장소 복제
```
git clone https://github.com/Yseek/erp-attendance-sync.git
```
2. 프로젝트 디렉토리로 이동
```
cd erp-attendance-sync
```
3. 애플리케이션 실행 (Gradle 사용)
```
./gradlew bootRun
```
4. 애플리케이션이 실행되면, 10초 간격으로 스케줄러가 동작하며 동기화 과정이 콘솔에 출력됩니다.


# 5. 프로젝트 구조
```
📂 src
 ┗ 📂 main
    ┣ 📂 java
    ┃  ┗ 📂 com.prototype.erpsync
    ┃     ┣ 📂 config ✨       - 애플리케이션의 주요 Bean 설정을 관리합니다. (e.g., WebClient)
    ┃     ┣ 📂 controller 🎮    - 외부 시스템을 흉내 내는 Mock API 컨트롤러가 위치합니다.
    ┃     ┣ 📂 domain 📦       - 프로젝트의 핵심 데이터 객체(Entity, DTO)를 정의합니다.
    ┃     ┣ 📂 fetcher 🔄       - 외부에서 데이터를 가져오는 로직(인터페이스 및 구현체)을 담당합니다.
    ┃     ┣ 📂 repository 💾    - 데이터베이스와 통신하는 JPA 리포지토리 인터페이스가 위치합니다.
    ┃     ┣ 📂 scheduler ⏰    - 주기적인 작업을 실행하는 스케줄러를 정의합니다.
    ┃     ┗ 📂 service ✅       - 핵심 비즈니스 로직을 수행하는 서비스 클래스가 위치합니다.
    ┗ 📂 resources
       ┣ 📜 application.properties - 애플리케이션의 주요 설정을 담당합니다.
       ┗ 📜 data.sql             - 애플리케이션 시작 시 H2 DB를 초기화하는 스크립트입니다.
```

# 6. 동작 데모 (GIF)
![demo 2025-06-26 153648](https://github.com/user-attachments/assets/56a03bb2-a0ab-4c6b-8ab1-bc43fd9c267f)
