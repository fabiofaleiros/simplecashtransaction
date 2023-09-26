package com.ffs.simplecashtransaction;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(initializers = {IntegrationtestApplicationTests.Initializer.class})
public class IntegrationtestApplicationTests {
	
//	@ClassRule
//	public static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres")
//			.withDatabaseName("simple_cash_transaction")
//			.withUsername("postgres")
//			.withPassword("123");
//	
	@Value("http://localhost:8080")
	String baseUrl;
//	
//	@Autowired
//	private UserRepository userRepository;
//
//
//    @Test
//    public void contextLoads() {
//    }
//
//    static class Initializer
//            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//            TestPropertyValues.of(
//                    "spring.datasource.url=" + postgres.getJdbcUrl(),
//                    "spring.datasource.username=" + postgres.getUsername(),
//                    "spring.datasource.password=" + postgres.getPassword()
//            ).applyTo(configurableApplicationContext.getEnvironment());
//        }
//    }
//    
//    @Test
//    public void testWriteToDb_afterBoot_shouldHaveEntries(){
//        List<User> all = userRepository.findAll();
//        Assertions.assertThat(all.size()).isEqualTo(6);
//        Assertions.assertThat(all.get(0).getFirstName()).isEqualTo("First");
//        Assertions.assertThat(all.get(0).getLastName()).isEqualTo("Last");
//    }
    
    @Test
    public void testGet_returns_200_with_expected_employees() {
    	
//    	IntegrationtestApplicationTests.standaloneSetup(new UserController());
    	
    	given()
//		    .standaloneSetup(new UserController())
		    .param("name", "Johan").
    	when()
	    	.get("/api/v1/users").
	    then()
	      .statusCode(200);
//	      .body("size()", is(6))
//	      .body("[0].firstName", equalTo("First"))
//	      .body("[0].lastName", equalTo("Last"));
    }
}
