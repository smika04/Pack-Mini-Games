package com.example.packminigames.Profiles.CheckProfiles;

import com.example.packminigames.PackMiniGamesApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = PackMiniGamesApplication.class)
@DisplayName("Profiles really exist and can be activated")
class ProfileExistenceVerificationIT {

    @Autowired
    private Environment environment;

    @Nested
    @DisplayName("Checking 'dev' profile")
    @ActiveProfiles("dev")
    class DevProfileChecks {
        @Test
        @DisplayName("'dev' profile must be active")
        void devProfileShouldBeActive() {
            assertThat(environment.getActiveProfiles())
                    .as("'dev' profile should be in the list of active profiles")
                    .contains("dev");
        }

        @Test
        @DisplayName("Other profiles should not be active with 'dev'")
        void otherProfilesShouldNotBeActiveWithDev() {
            assertThat(environment.getActiveProfiles())
                    .as("The list of active profiles should contain only 'dev' (plus default ones if any)")
                    .doesNotContain("prod", "test", "nonexistent");
        }
    }

    @Nested
    @DisplayName("Checking 'prod' profile")
    @ActiveProfiles("prod")
    class ProdProfileChecks {
        @Test
        @DisplayName("'prod' profile must be active")
        void prodProfileShouldBeActive() {
            assertThat(environment.getActiveProfiles())
                    .as("'prod' profile should be in the list of active profiles")
                    .contains("prod");
        }

        @Test
        @DisplayName("Other profiles should not be active with 'prod'")
        void otherProfilesShouldNotBeActiveWithProd() {
            assertThat(environment.getActiveProfiles())
                    .as("The list of active profiles should contain only 'prod' (plus default ones if any)")
                    .doesNotContain("dev", "test", "nonexistent");
        }
    }

    @Nested
    @DisplayName("Checking 'test' profile")
    @ActiveProfiles("test")
    class TestProfileChecks {
        @Test
        @DisplayName("'test' profile must be active")
        void testProfileShouldBeActive() {
            assertThat(environment.getActiveProfiles())
                    .as("'test' profile should be in the list of active profiles")
                    .contains("test");
        }

        @Test
        @DisplayName("Other profiles should not be active with 'test'")
        void otherProfilesShouldNotBeActiveWithTest() {
            assertThat(environment.getActiveProfiles())
                    .as("The list of active profiles should contain only 'test' (plus default ones if any)")
                    .doesNotContain("dev", "prod", "nonexistent");
        }
    }
}