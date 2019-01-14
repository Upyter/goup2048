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
 * Tests for {@link SimpleBoard}.
 * @since 0.17
 */
public final class SimpleBoardTest {
    /**
     * {@link SimpleBoard#toString()} must return the correct string for a 2x2
     * board with some random fields.
     */
    @Test
    public void someBoardTwoXTwoToString() {
        final var first = 23;
        final var second = 54;
        final var third = 232;
        final var fourth = 283;
        MatcherAssert.assertThat(
            new SimpleBoard(
                new SimpleField(first), new SimpleField(second),
                new SimpleField(third), new SimpleField(fourth)
            ),
            Matchers.hasToString(
                String.format(
                    "[ %d| %d]\n[%d|%d]",
                    first,
                    second,
                    third,
                    fourth
                )
            )
        );
    }
}
