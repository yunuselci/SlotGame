package com.patika.slotgame.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserModelTest {

    @Test
    void allArgsConstructorShouldWork(){
        User user = new User(1L, "Yunus","123456", 100, 20, 20);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getUsername()).isEqualTo("Yunus");
        assertThat(user.getPassword()).isEqualTo("123456");
        assertThat(user.getCredits()).isEqualTo(100);
        assertThat(user.getLoan()).isEqualTo(20);
        assertThat(user.getWithdraw()).isEqualTo(20);
    }

    @Test
    void setFieldShouldWork(){
        User user = new User();
        user.setId(1L);
        user.setUsername("Yunus");
        user.setPassword("123456");
        user.setCredits(100);
        user.setLoan(20);
        user.setWithdraw(20);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getUsername()).isEqualTo("Yunus");
        assertThat(user.getPassword()).isEqualTo("123456");
        assertThat(user.getCredits()).isEqualTo(100);
        assertThat(user.getLoan()).isEqualTo(20);
        assertThat(user.getWithdraw()).isEqualTo(20);
    }

    @Test
    void builderShouldWork(){
        User user = User.builder()
                .id(1L)
                .username("Yunus")
                .password("123456")
                .credits(100)
                .loan(20)
                .withdraw(20)
                .build();
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getUsername()).isEqualTo("Yunus");
        assertThat(user.getPassword()).isEqualTo("123456");
        assertThat(user.getCredits()).isEqualTo(100);
        assertThat(user.getLoan()).isEqualTo(20);
        assertThat(user.getWithdraw()).isEqualTo(20);
    }

    @Test
    void toStringShouldWork(){
        User user = new User(1L, "Yunus","123456", 100, 20, 20);
        assertThat(user.toString()).hasToString("User(id=1, username=Yunus, password=123456, credits=100, loan=20, withdraw=20)");
    }

    @Test
    void toHashCodeShouldWork(){
        User user = new User(1L, "Yunus","123456", 100, 20, 20);
        assertThat(user.hashCode()).hasSameHashCodeAs(user.hashCode());
    }

    @Test
    void equalsShouldWork(){
        User user = new User(1L, "Yunus","123456", 100, 20, 20);
        User user2 = new User(1L, "Yunus","123456", 100, 20, 20);
        assertThat(user.equals(user2)).isTrue();
        assertThat(user.canEqual(user2)).isTrue();
    }

    @Test
    void userDetailImplemantationsShouldWork(){
        User user = new User(1L, "Yunus","123456", 100, 20, 20);
        assertThat(user.getAuthorities()).isNotNull();
        assertThat(user.isAccountNonExpired()).isTrue();
        assertThat(user.isAccountNonLocked()).isTrue();
        assertThat(user.isCredentialsNonExpired()).isTrue();
        assertThat(user.isEnabled()).isTrue();
    }
}
