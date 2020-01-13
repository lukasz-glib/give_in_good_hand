package pl.lg.charity.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.lg.charity.domain.entities.Category;

import javax.transaction.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    /*
    Below - query for heroku
     */

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categories(id, name) VALUES (1, 'zabawki'), (2, 'ubrania, które nadają się do ponownego użycia'), " +
            "(3, 'ubrania, do wyrzucenia'), (4, 'książki'), (5, 'inne')", nativeQuery = true)
    void createCategoryForHeroku();
}
