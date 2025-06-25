package com.quickboard.resourcepost.profile.repository;

import com.quickboard.resourcepost.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
