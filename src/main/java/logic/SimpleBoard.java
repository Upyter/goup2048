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

import java.util.Collection;
import java.util.Iterator;
import java.util.function.BiFunction;

/**
 * A simple 2048 board implementation. It aims to be easy implemented and
 * readable.
 * @since 0.13
 */
public class SimpleBoard implements Board {

    @Override
    public final Field get(final int index) {
        throw new UnsupportedOperationException("#get()");
    }

    @Override
    public final Collection<Move<Board>> possibleMoves() {
        throw new UnsupportedOperationException("#possibleMoves()");
    }

    @Override
    public final int size() {
        throw new UnsupportedOperationException("#size()");
    }

    @Override
    public final int rowSize() {
        throw new UnsupportedOperationException("#rowSize()");
    }

    // @checkstyle ParameterNameCheck (4 lines)
    @Override
    public final Iterator<Field> filled(
        final int row,
        final BiFunction<Integer, Integer, Integer> cellFunction
    ) {
        throw new UnsupportedOperationException("#filled()");
    }
}
