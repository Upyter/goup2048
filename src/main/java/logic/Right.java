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

import java.util.Iterator;

/**
 * A right move for the {@link SimpleBoard}.
 * @since 0.13
 */
public class Right implements Move<Board> {
    @Override
    public final void push(final Board board) {
        throw new UnsupportedOperationException("To be implemented");
    }

    @Override
    public final void merge(final Board board) {
        for (int row = 0; row < board.rowSize(); ++row) {
            final Iterator<Field> filled = board.filled(
                row,
                (size, index) -> size - index - 1
            );
            while (filled.hasNext()) {
                final var current = filled.next();
                if (filled.hasNext()) {
                    final var next = filled.next();
                    if (current.equals(next)) {
                        current.upgrade();
                        next.clean();
                    }
                }
            }
        }
    }
}
