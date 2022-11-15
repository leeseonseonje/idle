package com.idle.weapon.repository;

import com.idle.weapon.domain.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {
}
