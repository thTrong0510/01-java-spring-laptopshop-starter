package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.ProductDetail;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    ProductDetail save(ProductDetail productDetail);

    void deleteById(long id);
}
