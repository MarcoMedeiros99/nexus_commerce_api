package com.marcomedeiros.nexus_commerce_api.config;

import com.marcomedeiros.nexus_commerce_api.model.access.Address;
import com.marcomedeiros.nexus_commerce_api.model.access.Role;
import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;
import com.marcomedeiros.nexus_commerce_api.model.access.users.ProfessionalUser;
import com.marcomedeiros.nexus_commerce_api.model.access.users.User;
import com.marcomedeiros.nexus_commerce_api.model.catalog.Category;
import com.marcomedeiros.nexus_commerce_api.model.catalog.DimensionsProduct;
import com.marcomedeiros.nexus_commerce_api.model.catalog.Product;
import com.marcomedeiros.nexus_commerce_api.model.catalog.Stock;
import com.marcomedeiros.nexus_commerce_api.model.sales.Order;
import com.marcomedeiros.nexus_commerce_api.model.sales.OrderItem;
import com.marcomedeiros.nexus_commerce_api.model.sales.enums.OrderStatus;
import com.marcomedeiros.nexus_commerce_api.repository.access.RoleRepository;
import com.marcomedeiros.nexus_commerce_api.repository.access.users.ProfessionalUserRepository;
import com.marcomedeiros.nexus_commerce_api.repository.access.users.UserRepository;
import com.marcomedeiros.nexus_commerce_api.repository.catalog.CategoryRepository;
import com.marcomedeiros.nexus_commerce_api.repository.catalog.ProductRepository;
import com.marcomedeiros.nexus_commerce_api.repository.catalog.StockRepository;
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
        private ProfessionalUserRepository professionalUserRepository;

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private CategoryRepository categoryRepository;

        @Autowired
        private StockRepository stockRepository;

        @Autowired
        private OrderRepository orderRepository;

        @Override
        public void run(String... args) throws Exception {

                // Roles

                Role r1 = Role.builder().nameRole("ADMIN").build();
                Role r2 = Role.builder().nameRole("CLIENT").build();
                Role r3 = Role.builder().nameRole("SELLER").build();
                Role r4 = Role.builder().nameRole("MANAGER").build();
                Role r5 = Role.builder().nameRole("SUPPORT").build();
                roleRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5));

                // Users

                User u1 = User.builder()
                                .name("Majin Kodak")
                                .email("majinkodak@test.com")
                                .password("321741369")
                                .document("142.153.316-25")
                                .phone("(31)99999-9999")
                                .role(r2)
                                .typePerson(TypePerson.INDIVIDUAL)
                                .build();

                Address ad1 = Address.builder()
                                .street("Virgilio Salomão").number("49")
                                .city("Belo horizonte").state("MG").zipCode("30670-250")
                                .neighborhood("Vila Pinho")
                                .user(u1)
                                .build();

                Address ad2 = Address.builder()
                                .street("Terezinha viana de assis").number("79")
                                .city("Belo horizonte").state("MG").zipCode("30670-240")
                                .neighborhood("Vila Pinho")
                                .user(u1)
                                .build();

                u1.getAddresses().add(ad1);
                u1.getAddresses().add(ad2);

                User u2 = User.builder()
                                .name("Lil Top")
                                .email("liltop@test.com")
                                .password("985632589")
                                .document("117.862.426-99")
                                .phone("(31)98888-8888")
                                .role(r2)
                                .typePerson(TypePerson.INDIVIDUAL)
                                .build();

                Address ad3 = Address.builder()
                                .street("predinho do c1").number("480")
                                .city("Belo horizonte").state("MG").zipCode("30789-420")
                                .neighborhood("C1")
                                .user(u2)
                                .build();

                u2.getAddresses().add(ad3);
                userRepository.saveAll(Arrays.asList(u1, u2));

                // Professional Users

                ProfessionalUser pu1 = ProfessionalUser.builder()
                                .name("Carlos Vendedor")
                                .email("carlos.vendedor@nexus.com")
                                .password("789456123")
                                .document("528.143.697-09")
                                .phone("(31)97777-7777")
                                .role(r3)
                                .typePerson(TypePerson.INDIVIDUAL)
                                .build();

                ProfessionalUser pu2 = ProfessionalUser.builder()
                                .name("Ana Gerente")
                                .email("ana.gerente@nexus.com")
                                .password("456789123")
                                .document("364.218.759-55")
                                .phone("(31)96666-6666")
                                .role(r4)
                                .typePerson(TypePerson.INDIVIDUAL)
                                .build();

                ProfessionalUser pu3 = ProfessionalUser.builder()
                                .name("Roberto Suporte")
                                .email("roberto.suporte@nexus.com")
                                .password("159753468")
                                .document("891.456.237-91")
                                .phone("(31)95555-5555")
                                .role(r5)
                                .typePerson(TypePerson.INDIVIDUAL)
                                .build();

                ProfessionalUser pu4 = ProfessionalUser.builder()
                                .name("Tech Solutions LTDA")
                                .email("contato@techsolutions.com")
                                .password("741852963")
                                .document("45.678.901/0001-75")
                                .phone("(31)94444-4444")
                                .role(r3)
                                .typePerson(TypePerson.CORPORATE)
                                .build();

                professionalUserRepository.saveAll(Arrays.asList(pu1, pu2, pu3, pu4));

                // Categories

                Category celular = Category.builder()
                                .name("Celular")
                                .build();

                Category eletronicos = Category.builder()
                                .name("Eletronicos")
                                .build();

                categoryRepository.saveAll(Arrays.asList(celular, eletronicos));

                // Products
                Product prod1 = Product.builder()
                                .name("Iphone 10")
                                .description("Celular da ipple")
                                .price(new java.math.BigDecimal("2000.00"))
                                .dimensionsProduct(new DimensionsProduct(0.5, 15.0, 7.0, 1.0))
                                .build();

                Product prod2 = Product.builder()
                                .name("Smart TV 65\" TCL 4K")
                                .description("A Smart TV TCL 65P7K de 65\" oferece uma experiência visual. " +
                                                "Ela combina tecnologia com recursos, elevando o entretenimento doméstico. "
                                                +
                                                "Com a resolução 4K UHD")
                                .price(new java.math.BigDecimal("2300.00"))
                                .dimensionsProduct(new DimensionsProduct(21.2, 144.5, 14.3, 89.3))
                                .build();

                prod1.getCategories().add(celular);
                prod1.getCategories().add(eletronicos);
                prod2.getCategories().add(eletronicos);

                productRepository.saveAll(Arrays.asList(prod1, prod2));

                // Stock

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

                // Orders

                Order ord1 = Order.builder()
                                .user(u1)
                                .deliveryAddress(new com.marcomedeiros.nexus_commerce_api.model.sales.DeliveryAddress(
                                                ad1.getStreet(), ad1.getNumber(), ad1.getCity(), ad1.getState(),
                                                ad1.getNeighborhood(), ad1.getZipCode(), ad1.getComplement()))
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
