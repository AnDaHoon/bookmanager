package com.dh.jpa.bookmanager.repository;

import com.dh.jpa.bookmanager.domain.Gender;
import com.dh.jpa.bookmanager.domain.User;
import com.dh.jpa.bookmanager.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;


@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Test
    void select(){
//        System.out.println(userRepository.findByName("martin"));
//
//        System.out.println("findByEmail : " + userRepository.findByEmail("martin@fastcampus.com"));
//        System.out.println("getByEmail : " + userRepository.getByEmail("martin@fastcampus.com"));
//        System.out.println("readByEmail : " + userRepository.readByEmail("martin@fastcampus.com"));
//        System.out.println("queryByEmail : " + userRepository.queryByEmail("martin@fastcampus.com"));
//        System.out.println("searchByEmail : " + userRepository.searchByEmail("martin@fastcampus.com"));
//        System.out.println("streamByEmail : " + userRepository.streamByEmail("martin@fastcampus.com"));
//        System.out.println("findUserByEmail : " + userRepository.findUserByEmail("martin@fastcampus.com"));
//        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
//        System.out.println("findFirst1ByName : " + userRepository.findFirst1ByName("martin"));
//        System.out.println("findLast1ByName : " + userRepository.findLast1ByName("martin"));

//        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("martin@fastcampus.com", "martin"));
//        System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("martin@fastcampus.com", "martin"));
//
//        // ?????? ?????? ?????? ?????????
//        System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L));
//        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1)));
//        System.out.println("findByCreatedAtGreaterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1)));
//        System.out.println("findByCreatedAtGreaterThanEqual : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1)));
//
//        System.out.println("findByCreatedAtBetween : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1)));
//        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L, 3L));
//        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L));

        // ?????? ?????? ?????????
//        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());

        // findByIdIsNotEmpty??? ????????? ????????? NotEmpty??? ?????????
//        System.out.println("findByAddressIsNotEmpty : " + userRepository.findByAddressIsNotEmpty());

        // in??? ?????? ???????????? ?????? ?????? ????????? ??????????????? ???????????? (in?????? or??? ???????????? ?????? ????????? ????????? ?????????)
//        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("martin", "dennis")));

        // like
        System.out.println("findByNameLike : " + userRepository.findByNameLike("%art%"));
        // ????????? like??? ????????? %??? ???????????? ????????? ?????? ???????????? ???????????? %??? ????????? ????????? ??? ??????.
        // mar%
        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("mar"));
        // %tin
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("tin"));
        // %art%
        System.out.println("findByNameContains : " + userRepository.findByNameContains("art"));


    }

    @Test
    void pagingAndSortingTest(){
//        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
//        // findLast1ByName ??? ???????????? ????????? ????????? ?????? ?????????.
//        // Test??? ?????????????????? ?????? ????????? ?????????.
//        // Last ?????? Keyword??? JPA?????? ???????????? ????????? ????????????. ????????? ?????? select????????? ????????????.
//        System.out.println("findLast1ByName : " + userRepository.findLast1ByName("martin"));
//        // ?????? ????????? 1?????? ???????????? ?????? ????????? OrderByIdDesc ???????????? ????????? ?????? ???????????? ????????? ??????.
//        System.out.println("findTopByNameOrderByIdDesc : " + userRepository.findTopByNameOrderByIdDesc("martin"));
//
//        // id ?????? sort, email ?????? sort
//        System.out.println("findFirstByNameOrderByIdDescEmailAsc : " + userRepository.findFirstByNameOrderByIdDescEmailAsc("martin"));

        // ??????????????? sort??? ????????? ??????
//        System.out.println("findFirstByName : " + userRepository.findFirstByName("martin", Sort.by(Sort.Order.desc("id"))));

        // ?????????
        System.out.println("findByNameWithPaging : " + userRepository.findByName("martin", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getContent());
    }

    @Test
    void insertAndUpdateTest(){
        User user = new User();

        user.setName("martin");
        user.setEmail("martin2@fastcampus.com");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrrrrrrrtin");

        userRepository.save(user2);
    }

    @Test
    void enumTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRawRecord().get("gender"));
    }

    @Test
    void listenerTest(){
        User user = new User();
        user.setEmail("martin@aa.com");
        user.setName("martin");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrr");

        userRepository.save(user2);

        userRepository.deleteById(4L);

    }

    @Test
    void prePersistTest(){
        User user = new User();
        user.setEmail("asd@naver.com");
        user.setName("martin");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        System.out.println(userRepository.findByEmail("asd@naver.com"));
    }

    @Test
    void preUpdateTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println("as-is : " + user);

        user.setName("asdasd");
        userRepository.save(user);

        System.out.println("to-be : " + userRepository.findAll().get(0));

    }

    @Test
    void userHistoryTest(){
        User user = new User();
        user.setEmail("asdsad@asd.com");
        user.setName("martin-new");

        userRepository.save(user);

        user.setName("asdsad");

        userRepository.save(user);

        userRepository.deleteById(1L);

        userHistoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    void userTest(){
        User user = new User();
        user.setEmail("dh@naver.com");
        user.setName("dh-new");

        userRepository.save(user);
        System.out.println(userRepository.findByName("dh-new"));
    }

    @Test
    void userRelationTest(){
        User user = new User();
        user.setName("david");
        user.setEmail("david@fastcampus.com");
        user.setGender(Gender.MALE);
        userRepository.save(user);

        user.setName("dada");
        userRepository.save(user);

        user.setEmail("dada@naver.com");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);

//        List<UserHistory> result = userHistoryRepository.findByUserId(
//                userRepository.findByEmail("dada@naver.com").getId()
//        );

        List<UserHistory> result = userRepository.findByEmail("dada@naver.com").getUserHistories();
        result.forEach(System.out::println);

        System.out.println("userHistory.getUser() : " + userHistoryRepository.findAll().get(0).getUser());
    }

}
