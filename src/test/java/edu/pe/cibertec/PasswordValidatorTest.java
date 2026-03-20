package edu.pe.cibertec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Password validator - PCI DSS Guidelines")
public class PasswordValidatorTest {

    private PasswordValidator validator;

    @BeforeEach
    void setUp(){
        validator = new PasswordValidator();
    }

    @Test
    @DisplayName("Valid password meets all PCI guidelines")
    void givenValidPassword_whenIsValid_thenTrue(){
        assertTrue(validator.isValid("Cibertec1@"));
    }

    @Test
    @DisplayName("Password shorter than 7 characteres is invalid")
    void givenShortPassword_whenIsValid_thenFalse(){
        assertFalse(validator.isValid("Dar3@"));
    }

    @Test
    @DisplayName("Password without uppercase letter is invalid")
    void givenNoUpperCase_whenIsValid_thenFalse()
    {
        assertFalse(validator.isValid("cibertec1@"));
    }

    @Test
    @DisplayName("Password without digit is invalid")
    void givenPasswordNotDigit_whenIsValid_thenFalse()
    { assertFalse(validator.isValid("cibertecsac@"));}

    //Password without lowercase letter is invalid
    @Test
    @DisplayName("Password without lowercase letter is invalid")
    void givenNoLowerCase_whenIsValid_thenFalse()
    {
        assertFalse(validator.isValid("CIBERTEC1@"));
    }

    //Null password is invalid
    @Test
    @DisplayName("Null password is invalid")
    void givenNullPassword_whenIsValid_thenFalse()
    {
        assertFalse(validator.isValid(null));
    }
}
