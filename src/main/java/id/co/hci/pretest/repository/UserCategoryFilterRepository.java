package id.co.hci.pretest.repository;

import id.co.hci.pretest.entity.UserCategoryFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserCategoryFilterRepository extends JpaRepository<UserCategoryFilter, UUID> {

    @Query(value = "SELECT * FROM USER_CATEGORY_FILTER WHERE USER_ID = :user ORDER BY ORDER_ID ASC",
            nativeQuery = true)
    List<UserCategoryFilter> findByUserId(@Param("user") String user);

}
