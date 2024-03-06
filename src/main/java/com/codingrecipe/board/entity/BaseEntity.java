package com.codingrecipe.board.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
/*
@MappedSuperclass 주석은 다음과 같은 목적으로 사용됩니다:
1. 공통 속성을 가진 엔티티 클래스를 정의할 때 사용됩니다.
예를 들어, 여러 엔티티 클래스가 동일한 속성을 가지고 있을 때,
이러한 속성을 추출하여 부모 클래스에 정의하고, @MappedSuperclass를 사용하여 부모 클래스를 만들 수 있습니다.
2. 데이터베이스 테이블에 매핑되지 않는 엔티티 클래스를 정의할 때 사용됩니다.
즉, @MappedSuperclass가 붙은 클래스는 테이블을 생성하지 않으며,
자식 엔티티 클래스에게만 상속 관계로 전파됩니다.
 */
@EntityListeners(AuditingEntityListener.class)
/*
@EntityListeners는 JPA(Java Persistence API)에서 사용되는 주석 중 하나입니다.
이 주석은 엔티티에 대한 이벤트 리스너를 정의할 때 사용됩니다.
엔티티의 상태 변경에 대한 이벤트를 감지하고 처리하는데 사용됩니다.
@EntityListeners의 매개변수로는 엔티티 이벤트를 처리하는 클래스를 지정합니다.
보통 이 클래스는 JPA에서 제공하는 리스너 인터페이스를 구현한 클래스입니다.

AuditingEntityListener는 Spring Data JPA에서 제공하는 클래스 중 하나로,
엔티티의 생성 및 수정 시간을 자동으로 관리하기 위해 사용됩니다.
주로 생성일자 및 수정일자를 추적하는 데 사용됩니다.
 */
@Getter
public class BaseEntity {

    @CreationTimestamp // 생성 시간 정보
    @Column(updatable = false) // 수정 시에는 관여 안함
    private LocalDateTime createdTime;

    @UpdateTimestamp // 수정 시간 정보
    @Column(insertable = false) // 생성 시에는 관여 안함
    private LocalDateTime updatedTime;
}
