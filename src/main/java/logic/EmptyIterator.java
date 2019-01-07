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
 * An iterator for a list of fields. With him you can iterate through all empty
 * (based on {@link Field#isEmpty(Field)}) fields.
 * <p>This class is mutable and not thread-safe.</p>
 * @see Field
 * @since 0.6
 */
public class EmptyIterator implements FieldIterator {

    @Override
    public final boolean hasNext() {
        throw new UnsupportedOperationException("#hasNext()");
    }

    @Override
    public final Field next() {
        throw new UnsupportedOperationException("#next()");
    }

    @Override
    public final void swapWith(final FieldIterator other) {
        throw new UnsupportedOperationException("#swapWith()");
    }
}
