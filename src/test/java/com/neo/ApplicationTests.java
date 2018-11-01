package com.neo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.neo.domain.UserRepositoryTests;
import com.neo.util.TestRedis;
import com.neo.web.HelloControlerTests;
import com.neo.web.ProPertiesTest;

@RunWith(Suite.class)
@SuiteClasses({ HelloControlerTests.class, ProPertiesTest.class, TestRedis.class, UserRepositoryTests.class })
public final class ApplicationTests {

}
