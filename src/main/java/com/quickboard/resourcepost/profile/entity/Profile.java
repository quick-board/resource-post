package com.quickboard.resourcepost.profile.entity;

import com.quickboard.resourcepost.common.entity.BaseEntity;
import com.quickboard.resourcepost.profile.enums.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;

@Entity
@Table(name = "profiles")
@DynamicInsert
@Getter
@NoArgsConstructor
public class Profile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "credential_id")
    private Long credentialId;

    @Setter
    @Column(nullable = false)
    private String nickname;

    @Setter
    @Column(name = "first_name")
    private String firstName;

    @Setter
    @Column(name = "last_name")
    private String lastName;

    @Setter
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Setter
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Builder
    public Profile(Long credentialId, String nickname, String firstName, String lastName, Gender gender, LocalDate birthDate) {
        this.credentialId = credentialId;
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }
}
