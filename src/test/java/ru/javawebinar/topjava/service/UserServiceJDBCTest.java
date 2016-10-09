package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by AntonioPrime on 09.10.2016.
 */
@ActiveProfiles({Profiles.JDBC, Profiles.POSTGRES})
public class UserServiceJDBCTest extends AbstractUserServiceTest {
}
