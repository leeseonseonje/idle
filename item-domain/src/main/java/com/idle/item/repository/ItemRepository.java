package com.idle.item.repository;

import com.idle.item.domain.Item;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.id in :ids")
    List<Item> findByIds(@Param("ids") List<Long> ids, Sort sort);

    @Query("select i from Item i where i.member.id = :memberId")
    List<Item> findByMemberId(@Param("memberId") Long memberId, Sort sort);

//    @Query("select i from Item i where i.member = :member and i.isWear = true")
//    Item findByWearingItems(@Param("memberId") Long memberId);
}
