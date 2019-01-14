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
 * A single field of the 2048 game grid.
 * @since 0.3
 */
public interface Field {
    /**
     * Returns the number of the field.
     * @return The number of the field.
     */
    int number();

    /**
     * Replaces the number of this field with the given number.
     * @param number The number that this field will take.
     */
    void number(int number);

    /**
     * Sets this field to be empty according to {@link #isEmpty(Field)}.
     */
    void clean();

    /**
     * A field is empty when it has a {@link #number()} of 0.
     * @param field The field to check for emptiness.
     * @return True if the field is 0 and false otherwise.
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    static boolean isEmpty(final Field field) {
        return field.number() == 0;
    }

    /**
     * A field is filled when it has a {@link #number()} != 0 which means that
     * it's not empty according to {@link this#isEmpty(Field)}.
     * @param field The field to check for fillness.
     * @return True if the field isn't 0 and false otherwise.
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    static boolean isFilled(final Field field) {
        return !Field.isEmpty(field);
    }

    /**
     * Doubles the number of the field. This should happen after merging two
     * fields with the same value.
     * @param field The field that will get the doubled number.
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    static void upgrade(final Field field) {
        field.number(field.number() * 2);
    }

    /**
     * Swaps the numbers of the fields.
     * @param first The first field.
     * @param second The second field.
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    static void swap(final Field first, final Field second) {
        final int temp = first.number();
        first.number(second.number());
        second.number(temp);
    }
}
