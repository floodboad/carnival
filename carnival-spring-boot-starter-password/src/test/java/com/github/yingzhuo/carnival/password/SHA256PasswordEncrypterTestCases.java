package com.github.yingzhuo.carnival.password;

import com.github.yingzhuo.carnival.password.impl.RepeatPasswordEncrypter;
import com.github.yingzhuo.carnival.password.impl.SHA256PasswordEncrypter;
import org.junit.Test;
import org.springframework.util.Assert;

public class SHA256PasswordEncrypterTestCases {

    private static final String ASSERTION_FAILED = "[Assertion failed]";

    private String text = "1234567890";
    private String leftSalt = "left";
    private String rightSalt = "right";

    private String badText = "x1234567890";


    @Test
    public void testInstance() {
        final PasswordEncrypter encrypter = new SHA256PasswordEncrypter();

        String hashed = encrypter.encrypt(text, leftSalt, rightSalt);
        Assert.notNull(hashed, ASSERTION_FAILED);

        Assert.isTrue(encrypter.matches(text, leftSalt, rightSalt, hashed), ASSERTION_FAILED);
        Assert.isTrue(!encrypter.notMatches(text, leftSalt, rightSalt, hashed), ASSERTION_FAILED);

        Assert.isTrue(!encrypter.matches(badText, leftSalt, rightSalt, hashed), ASSERTION_FAILED);
        Assert.isTrue(encrypter.notMatches(badText, leftSalt, rightSalt, hashed), ASSERTION_FAILED);
    }

    @Test
    public void testInstanceWithRepeat() {
        final PasswordEncrypter encrypter = new RepeatPasswordEncrypter(new SHA256PasswordEncrypter(), 3);

        String hashed = encrypter.encrypt(text, leftSalt, rightSalt);
        Assert.notNull(hashed, ASSERTION_FAILED);

        Assert.isTrue(encrypter.matches(text, leftSalt, rightSalt, hashed), ASSERTION_FAILED);
        Assert.isTrue(!encrypter.notMatches(text, leftSalt, rightSalt, hashed), ASSERTION_FAILED);

        Assert.isTrue(!encrypter.matches(badText, leftSalt, rightSalt, hashed), ASSERTION_FAILED);
        Assert.isTrue(encrypter.notMatches(badText, leftSalt, rightSalt, hashed), ASSERTION_FAILED);
    }

}