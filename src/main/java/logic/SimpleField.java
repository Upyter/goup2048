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

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A simple 2048 field implementation. It aims to be easy implemented and
 * readable.
 * <p>This class is mutable and not thread-safe.</p>
 * @since 0.15
 */
@EqualsAndHashCode
@ToString
public class SimpleField implements Field {
    /**
     * The number of this field.
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private int number;

    /**
     * Ctor. Creates an empty field (number = 0).
     */
    public SimpleField() {
        this(0);
    }

    /**
     * Ctor.
     * @param number The number of this field.
     */
    public SimpleField(final int number) {
        this.number = number;
    }

    @Override
    public final int number() {
        return this.number;
    }

    // @checkstyle HiddenField (2 lines)
    @Override
    public final void number(final int number) {
        this.number = number;
    }

    @Override
    public final void clean() {
        this.number = 0;
    }
}
