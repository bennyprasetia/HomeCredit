package id.co.hci.pretest.repository;

import id.co.hci.pretest.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category,UUID> {


}
