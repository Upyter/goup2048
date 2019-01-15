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

/**
 * Utility class to field the amount of digits in a number.
 * @since 0.17
 */
public final class Digits {
    /**
     * Private ctor to prevent creation.
     */
    private Digits() { }

    /**
     * Returns the number of digits in an integer.
     * Source: https://www.baeldung.com/java-number-of-digits-in-int
     * @param number The number to field the number of digits from.
     * @return The number of digits.
     * @checkstyle ExecutableStatementCount (3 lines)
     */
    @SuppressWarnings(
        {
            "PMD.ProhibitPublicStaticMethods",
            "PMD.CyclomaticComplexity"
        }
    )
    public static int number(final int number) {
        final int unsigned = Math.abs(number);
        final int result;
        // @checkstyle NestedIfDepth (35 lines)
        // @checkstyle ExecutableStatementCount (2 lines)
        // @checkstyle MagicNumberCheck (33 lines)
        if (unsigned < 100000) {
            if (unsigned < 100) {
                if (unsigned < 10) {
                    result = 1;
                } else {
                    result = 2;
                }
            } else {
                if (unsigned < 1000) {
                    result = 3;
                } else {
                    if (unsigned < 10000) {
                        result = 4;
                    } else {
                        result = 5;
                    }
                }
            }
        } else {
            if (unsigned < 10000000) {
                if (unsigned < 1000000) {
                    result = 6;
                } else {
                    result = 7;
                }
            } else {
                if (unsigned < 100000000) {
                    result = 8;
                } else {
                    if (unsigned < 1000000000) {
                        result = 9;
                    } else {
                        result = 10;
                    }
                }
            }
        }
        return result;
    }
}
