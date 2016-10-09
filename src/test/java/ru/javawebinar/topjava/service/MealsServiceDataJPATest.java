package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by AntonioPrime on 09.10.2016.
 */
@ActiveProfiles({Profiles.DATAJPA, Profiles.POSTGRES})
public class MealsServiceDataJPATest extends AbstractMealServiceTest {
}
