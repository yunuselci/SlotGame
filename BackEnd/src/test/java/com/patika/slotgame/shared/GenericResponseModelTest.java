package com.patika.slotgame.shared;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GenericResponseModelTest {

    @Test
    void allArgsConstructorShouldWork() {
        GenericResponse genericResponse = new GenericResponse("Test, message.");
        assertThat(genericResponse).isNotNull();
        assertThat(genericResponse.getMessage()).isEqualTo("Test, message.");
    }

    @Test
    void setFieldShouldWork() {
        GenericResponse genericResponse = new GenericResponse("Constructor message.");
        genericResponse.setMessage("Test, message.");
        assertThat(genericResponse).isNotNull();
        assertThat(genericResponse.getMessage()).isEqualTo("Test, message.");
    }

    @Test
    void toStringMethodShouldWork() {
        GenericResponse genericResponse = new GenericResponse("Test, message.");
        assertThat(genericResponse.toString()).hasToString("GenericResponse(message=Test, message.)");
    }

    @Test
    void toHashCodeMethodShouldWork() {
        GenericResponse genericResponse = new GenericResponse("Test, message.");
        assertThat(genericResponse.hashCode()).hasSameHashCodeAs(genericResponse.hashCode());
    }

    @Test
    void equalsShouldWork() {
        GenericResponse genericResponse = new GenericResponse("Test, message.");
        GenericResponse genericResponse2 = new GenericResponse("Test, message.");
        assertThat(genericResponse.equals(genericResponse2)).isTrue();
        assertThat(genericResponse.canEqual(genericResponse2)).isTrue();
    }
}
