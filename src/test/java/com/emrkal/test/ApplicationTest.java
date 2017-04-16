package com.emrkal.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.emrkal.test.service.ActivityServiceTest;
import com.emrkal.test.service.CommentServiceTest;
import com.emrkal.test.service.UserRepositoryTest;

/**
 * Servis testlerinin sırasıyla koşulduğu main test sınıfıdır.
 * <p>
 * 
 * @see ApplicationTest
 * @version 1.0
 * @author Emrullah KALKAN
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ UserRepositoryTest.class, CommentServiceTest.class, ActivityServiceTest.class })
public class ApplicationTest {

	@BeforeClass
	public static void beforeClass() {
		System.out.println("Testler Başlatıldı!");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("Testler Tamamlandı!");
	}

}
