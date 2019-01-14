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
 * Utility class to calculate square roots.
 * @since 0.16
 */
public final class SquareRoot {
    /**
     * Private ctor to prevent construction.
     */
    private SquareRoot() { }

    /**
     * Returns the square root of an integer and returns it as an integer.
     * This method assumes that the square root of the given number can be
     * calculated without left overs.
     * @param number The number to calculate the square root from. It has to be
     *  >= 0.
     * @return The square root of the given number.
     * @throws IllegalArgumentException If the given number is negative.
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    public static int root(final int number) {
        if (number < 0) {
            throw new IllegalArgumentException(
                String.format(
                    "Number must be positive but is %d",
                    number
                )
            );
        }
        int sqrt = -1;
        do {
            ++sqrt;
        } while (sqrt * sqrt <= number);
        return sqrt - 1;
    }
}
