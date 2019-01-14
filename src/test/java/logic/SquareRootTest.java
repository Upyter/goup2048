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
 * Tests for {@link SquareRoot}.
 * @since 0.16
 */
public final class SquareRootTest {
    /**
     * {@link SquareRoot#root(int)} must throw {@link IllegalArgumentException}
     * for negative numbers.
     */
    @Test(expected = IllegalArgumentException.class)
    public void negativeRoot() {
        SquareRoot.root(-1);
    }

    /**
     * {@link SquareRoot#root(int)} with 0 must return 0.
     */
    @Test
    public void zeroIntRoot() {
        MatcherAssert.assertThat(SquareRoot.root(0), Matchers.equalTo(0));
    }

    /**
     * {@link SquareRoot#root(int)} with 1 must return 1.
     */
    @Test
    public void oneIntRoot() {
        MatcherAssert.assertThat(SquareRoot.root(1), Matchers.equalTo(1));
    }

    /**
     * {@link SquareRoot#root(int)} with a number > 1 that has a square root
     * without any left overs (like 4 -> 2) must return the correct value.
     */
    @Test
    public void higherIntRoot() {
        final var rootable = 16;
        final var root = 4;
        MatcherAssert.assertThat(
            SquareRoot.root(rootable),
            Matchers.equalTo(root)
        );
    }

    /**
     * {@link SquareRoot#root(int)} with a number > 1 that has a square root
     * with left overs (like 5) must return the closest value that is smaller
     * than the actual square root (5 -> 2 instead of 2.23606...).
     */
    @Test
    public void rootWithLeftOuts() {
        // @checkstyle LocalFinalVariableName (1 line)
        final var withLeftovers = 17;
        final var root = 4;
        MatcherAssert.assertThat(
            SquareRoot.root(withLeftovers),
            Matchers.equalTo(root)
        );
    }
}
