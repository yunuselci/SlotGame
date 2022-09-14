package com.patika.slotgame.security;

import com.patika.slotgame.auth.Credentials;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CredentialsModelTest {

    Credentials credentials = new Credentials();

    @Test
    void setFieldShouldWork() {
        credentials.setUsername("Yunus");
        credentials.setPassword("123456");
        assertThat(credentials).isNotNull();
        assertThat(credentials.getUsername()).isEqualTo("Yunus");
        assertThat(credentials.getPassword()).isEqualTo("123456");
    }

    @Test
    void toStringMethodShouldWork() {
        credentials.setUsername("Yunus");
        credentials.setPassword("123456");
        assertThat(credentials.toString()).hasToString("Credentials(username=Yunus, password=123456)");
    }

    @Test
    void toHashCodeMethodShouldWork() {
        credentials.setUsername("Yunus");
        credentials.setPassword("123456");
        assertThat(credentials.hashCode()).hasSameHashCodeAs(credentials.hashCode());
    }

    @Test
    void equalsShouldWork() {
        Credentials credentials1 = new Credentials();
        credentials.setUsername("Yunus");
        credentials.setPassword("123456");
        credentials1.setUsername("Yunus");
        credentials1.setPassword("123456");
        assertThat(credentials.equals(credentials1)).isTrue();
    }
}
