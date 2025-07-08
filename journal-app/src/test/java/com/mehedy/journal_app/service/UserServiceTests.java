//package com.mehedy.journal_app.service;
//
//import com.mehedy.journal_app.entity.User;
//import com.mehedy.journal_app.repository.UserRepository;
//import net.bytebuddy.utility.nullability.AlwaysNull;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsProvider;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.repository.query.Param;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class UserServiceTests {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Disabled
//    @Test
//    public void testAdd() {
//        assertEquals(4, 2 + 2);
//    }
//
////    @Disabled
////    @ParameterizedTest
////    @CsvSource({
////            "trent12",
////            "mbappe9",
////            "huijsen24",
////            "vini7"
////    })
////    public void testFindByUserName(String name)
////    {
////
////        assertNotNull(userRepository.findUserByUsername(name),"failed for "+name);
////     //assertNotNull(userRepository.findUserByUsername("mbappe9"));
////    }
//
//
////    @Disabled
////    @ParameterizedTest  // Also used value source annotation instead of @CsvSource
//
//    /// /    @CsvSource({
//    /// /            "1,1,2",
//    /// /            "2,10,12",
//    /// /            "3,3,9"
//    /// /    })
////
////    @ValueSource(strings = {
////            "1,1,2",
////            "2,10,12",
////            "3,3,6"
////    })
////    void test(String data) {
////        String[] parts = data.split(",");
////        int a = Integer.parseInt(parts[0]);
////        int b = Integer.parseInt(parts[1]);
////        int expected = Integer.parseInt(parts[2]);
////
////        assertEquals(expected, a + b);
////
////    }
//
//    // Another one is ArgumentSource annotation. And the workprocess is given below
//
//
//    // This is run before all the method
//    // There are two same method named @AfterEach and @AfterAll
//    @BeforeAll
//    public static void something() {
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentsProvider.class)
//    public void testSaveNewUser(User user) {
//
//        assertTrue(userService.saveNewUser(user));
//        //assertNotNull(userRepository.findUserByUsername("mbappe9"));
//    }
//
//    // This run before each method and do the config work in here used to serve the purpose like initialize
//
//
//    @BeforeEach
//    public void setup() {
//
//    }
//
//
//}
