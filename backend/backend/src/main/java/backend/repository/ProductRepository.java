package backend.repository;

import backend.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products, Long> {

    Optional <Products> findByName(String name);
    Optional <Products> findById(Long id);

}
