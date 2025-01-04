package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import vn.hoidanit.laptopshop.domain.User;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartDetailService cartDetailService;
    private final CartService cartService;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, CartDetailService cartDetailService,
            CartService cartService, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.cartDetailService = cartDetailService;
        this.cartService = cartService;
    }

    public Optional<Product> fetchProductById(long id) {
        return this.productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    public List<Product> fetchProducts() {
        return this.productRepository.findAll();
    }

    public void addProductToCart(String email, long productId) {
        User user = this.userService.fetchUserByEmail(email);
        if (user != null) {
            Cart cart = this.cartService.fetchCartByUser(user);
            if (cart == null) {
                // create new cart
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setTotal_quantity(1);

                cart = this.cartService.saveCart(otherCart);
            }

            CartDetail cartDetail = new CartDetail();
            // save cart detail into cart
            Product product = fetchProductById(productId).get();
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cartDetail.setPrice(product.getPrice());
            cartDetail.setQuantity(0);
            this.cartDetailService.savCartDetail(cartDetail);
        }
    }
}
