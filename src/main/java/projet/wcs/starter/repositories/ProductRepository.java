package projet.wcs.starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.wcs.starter.dao.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}