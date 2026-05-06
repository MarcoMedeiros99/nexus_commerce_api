package com.marcomedeiros.nexus_commerce_api.config;

import com.marcomedeiros.nexus_commerce_api.model.access.Address;
import com.marcomedeiros.nexus_commerce_api.model.access.Role;
import com.marcomedeiros.nexus_commerce_api.model.access.User;
import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;
import com.marcomedeiros.nexus_commerce_api.model.catalog.Category;
import com.marcomedeiros.nexus_commerce_api.model.catalog.DimensionsProduct;
import com.marcomedeiros.nexus_commerce_api.model.catalog.Product;
import com.marcomedeiros.nexus_commerce_api.model.catalog.Stock;
import com.marcomedeiros.nexus_commerce_api.model.sales.Order;
import com.marcomedeiros.nexus_commerce_api.model.sales.OrderItem;
import com.marcomedeiros.nexus_commerce_api.model.sales.enums.OrderStatus;
import com.marcomedeiros.nexus_commerce_api.repository.access.RoleRepository;
import com.marcomedeiros.nexus_commerce_api.repository.access.UserRepository;
import com.marcomedeiros.nexus_commerce_api.repository.catalog.CategoryRepository;
import com.marcomedeiros.nexus_commerce_api.repository.catalog.ProductRepository;
import com.marcomedeiros.nexus_commerce_api.repository.catalog.StockRepository;
import com.marcomedeiros.nexus_commerce_api.repository.sales.OrderItemRepository;
import com.marcomedeiros.nexus_commerce_api.repository.sales.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public void run(String... args) throws Exception {

        // 1. Role

        Role r1 = Role.builder().nameRole("ADMIN").build();
        Role r2 = Role.builder().nameRole("CLIENT").build();
        roleRepository.saveAll(Arrays.asList(r1, r2));

        // 2. User

        User u1 = User.builder()
                .name("Majin Kodak")
                .email("majinkodak@test.com")
                .password("321741369")
                .cpfCnpj("123.321.456-25")
                .phone("31 9 9999-9999")
                .role(r1)
                .typePerson(TypePerson.CORPORATE)
                .build();

        Address ad1 = Address.builder()
                .streetAddress("bequin do mangueiras").number("420")
                .city("Belo horizonte").state("MG").zipCode("30670-420")
                .user(u1)
                .build();

        u1.getAddresses().add(ad1);

        User u2 = User.builder()
                .name("Lil Top")
                .email("liltop@test.com")
                .password("985632589")
                .cpfCnpj("653.485.456-25")
                .phone("31 9 9999-9999")
                .role(r2)
                .typePerson(TypePerson.INDIVIDUAL)
                .build();

        Address ad2 = Address.builder()
                .streetAddress("predinho do c1").number("480")
                .city("Belo horizonte").state("MG").zipCode("30789-420")
                .user(u2)
                .build();

        u2.getAddresses().add(ad2);
        userRepository.saveAll(Arrays.asList(u1, u2));


        // 3. Category

        Category celular = Category.builder()
                .name("Celular")
                .build();

        Category eletronicos = Category.builder()
                .name("Eletronicos")
                .build();

        categoryRepository.saveAll(Arrays.asList(celular, eletronicos));

        // 4. Product

        Product prod1 = Product.builder()
                .name("Iphone 10")
                .description("Celular da ipple")
                .price(new java.math.BigDecimal("2000.00"))
                .dimensionsProduct(new DimensionsProduct(0.5, 15.0, 7.0, 1.0))
                .build();

        Product prod2 = Product.builder()
                .name("Smart TV 65\" TCL 4K")
                .description("A Smart TV TCL 65P7K de 65\" oferece uma experiência visual. " +
                        "Ela combina tecnologia com recursos, elevando o entretenimento doméstico. " +
                        "Com a resolução 4K UHD")
                .price(new java.math.BigDecimal("2300.00"))
                .dimensionsProduct(new DimensionsProduct(21.2, 144.5, 14.3, 89.3))
                .build();

        prod1.getCategories().add(celular);
        prod1.getCategories().add(eletronicos);
        prod2.getCategories().add(eletronicos);

        productRepository.saveAll(Arrays.asList(prod1, prod2));

        // 5. Stock

        Stock stock1 = Stock.builder()
                .product(prod1)
                .itemQuantity(50)
                .minQuantity(5)
                .priceCost(new java.math.BigDecimal("1500.00"))
                .build();

        Stock stock2 = Stock.builder()
                .product(prod2)
                .itemQuantity(50)
                .minQuantity(5)
                .priceCost(new java.math.BigDecimal("1450.00"))
                .build();

        stockRepository.saveAll(Arrays.asList(stock1, stock2));

        // 6. Pedido e Itens

        Order ord1 = Order.builder()
                .user(u1).address(ad1)
                .totalValue(new BigDecimal("2000.00"))
                .finalValue(new BigDecimal("2000.00"))
                .freightValue(BigDecimal.ZERO)
                .orderStatus(OrderStatus.WAITING_PAYMENT)
                .build();

        OrderItem ordIt1 = OrderItem.builder()
                .quantity(1)
                .unitPrice(prod1.getPrice())
                .build();

        ordIt1.setOrder(ord1);
        ordIt1.setProduct(prod1);

        ord1.getItems().add(ordIt1);
        orderRepository.save(ord1);

        System.out.println("BANCO H2 POPULADO COM SUCESSO!");
    }
}
