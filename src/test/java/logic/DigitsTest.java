/*
 * Copyright 2019 Upyter
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package logic;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Digits}.
 * @since 0.17
 */
public final class DigitsTest {
    /**
     * {@link Digits#number(int)} must return 1 for a number with one digit.
     */
    @Test
    public void singleDigitInt() {
        final int num = 5;
        final int digits = 1;
        MatcherAssert.assertThat(Digits.number(num), Matchers.equalTo(digits));
    }

    /**
     * {@link Digits#number(int)} must return 1 for a negative number with one
     * digit.
     */
    @Test
    public void singleNegativeDigitInt() {
        final int num = -4;
        final int digits = 1;
        MatcherAssert.assertThat(Digits.number(num), Matchers.equalTo(digits));
    }

    /**
     * {@link Digits#number(int)} must return 1 for 0.
     */
    @Test
    public void zeroIntDigit() {
        MatcherAssert.assertThat(Digits.number(0), Matchers.equalTo(1));
    }

    /**
     * {@link Digits#number(int)} must return the correct number of digits for
     * an int with multiple digits.
     */
    @Test
    public void multipleDigitsInt() {
        final int num = 2859;
        final int digits = 4;
        MatcherAssert.assertThat(Digits.number(num), Matchers.equalTo(digits));
    }

    /**
     * {@link Digits#number(int)} must return the correct number of digits for
     * a negative int with multiple digits.
     */
    @Test
    public void negativeMultipleDigitsInt() {
        final int num = -328562;
        final int digits = 6;
        MatcherAssert.assertThat(Digits.number(num), Matchers.equalTo(digits));
    }
}
