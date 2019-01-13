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
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A simple 2048 board implementation. It aims to be easy implemented and
 * readable.
 * <p>This class is mutable and not thread-safe.</p>
 * @since 0.13
 */
@EqualsAndHashCode
@ToString
public class SimpleBoard implements Board {
    /**
     * The fields of the board.
     */
    private final Field[] fields;

    /**
     * The amount of fields in a row.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final int rowSize;

    /**
     * Ctor.
     * @param fields The fields of the board.
     * @param rowSize The amount of fields in a row.
     * @checkstyle ParameterName (2 lines)
     */
    public SimpleBoard(final Field[] fields, final int rowSize) {
        this.fields = fields;
        this.rowSize = rowSize;
    }

    @Override
    public final Field get(final int index) {
        return this.fields[index];
    }

    @Override
    public final Collection<Move<Board>> possibleMoves() {
        throw new UnsupportedOperationException("#possibleMoves()");
    }

    @Override
    public final int size() {
        return this.fields.length;
    }

    @Override
    public final int rowSize() {
        return this.rowSize;
    }

    // @checkstyle ParameterNameCheck (4 lines)
    @Override
    public final Iterator<Field> filled(
        final int row,
        final BiFunction<Integer, Integer, Integer> cellFunction
    ) {
        return new FilledIterator(this, row, cellFunction);
    }
}
