package org.solo.repository;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.solo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataMongoTest
public class UsersTest {
    @Autowired
    private Users users;

    @Test
    public void SaveTest() {
        users.deleteAll();
        User user = new User();
        users.save(user);
        assertThat(users.count(), is(1L));
    }

    }

