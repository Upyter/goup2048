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
 * Tests for {@link Field}.
 * @since 0.17
 */
public final class FieldTest {
    /**
     * {@link SimpleField#equals(Object)} must return true if two fields have
     * the same number.
     */
    @Test
    public void sameNumEqual() {
        final var num = 132;
        MatcherAssert.assertThat(
            new SimpleField(num), Matchers.equalTo(new SimpleField(num))
        );
    }

    /**
     * {@link SimpleField#equals(Object)} must return false if two fields have
     * different numbers.
     */
    @Test
    public void differentNumUnEqual() {
        final var first = 324;
        final var second = 23;
        MatcherAssert.assertThat(
            new SimpleField(first),
            Matchers.not(
                Matchers.equalTo(new SimpleField(second))
            )
        );
    }

    /**
     * {@link SimpleField#upgrade()} must double the number of a field with some
     * number > 0.
     */
    @Test
    public void upgradeSomeNum() {
        final var num = 43;
        final Field field = new SimpleField(num);
        Field.upgrade(field);
        MatcherAssert.assertThat(field.number(), Matchers.equalTo(num * 2));
    }
}
