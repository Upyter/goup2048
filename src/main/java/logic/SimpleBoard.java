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

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiFunction;
import lombok.EqualsAndHashCode;

/**
 * A simple 2048 board implementation. It aims to be easy implemented and
 * readable.
 * <p>This class is mutable and not thread-safe.</p>
 * @since 0.13
 */
@EqualsAndHashCode
public class SimpleBoard implements Board {
    /**
     * The fields of the board.
     */
    private final Field[] fields;

    /**
     * Ctor for test purposes. Creates a 4x4 (16 fields) board of empty fields..
     */
    public SimpleBoard() {
        this(
            new SimpleField(0), new SimpleField(0), new SimpleField(0),
            new SimpleField(0), new SimpleField(0), new SimpleField(0),
            new SimpleField(0), new SimpleField(0), new SimpleField(0),
            new SimpleField(0), new SimpleField(0), new SimpleField(0),
            new SimpleField(0), new SimpleField(0), new SimpleField(0),
            new SimpleField(0)
        );
    }

    /**
     * Ctor.
     * @param fields The fields of the board.
     * @checkstyle ParameterName (2 lines)
     */
    public SimpleBoard(final Field... fields) {
        this.fields = Arrays.copyOf(fields, fields.length);
    }

    @Override
    public final Field field(final int index) {
        if (this.fields.length <= index) {
            throw new IllegalArgumentException(
                String.format(
                    String.join(
                        "",
                        "Index must be smaller than the number of fields on ",
                        "this board. Index: %d, Size: %d, Board: \n%s"
                    ),
                    index,
                    this.fields.length,
                    this.toString()
                )
            );
        }
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
        return SquareRoot.root(this.fields.length);
    }

    // @checkstyle ParameterNameCheck (4 lines)
    @Override
    public final Iterator<Field> filled(
        final int row,
        final BiFunction<Integer, Integer, Integer> cellFunction
    ) {
        return new FilledIterator(this, row, cellFunction);
    }

    @Override
    public final String toString() {
        final var result = new StringBuilder();
        final int width = Digits.number(
            Arrays.stream(this.fields).max(
                Comparator.comparingInt(
                    field -> Digits.number(field.number())
                )
            ).orElseThrow().number()
        );
        // @checkstyle LocalFinalVariableName (2 lines)
        final int rowSize = this.rowSize();
        final int rowAmount = this.fields.length / rowSize;
        for (int row = 0; row < rowAmount; ++row) {
            result.append('[');
            for (
                int cell = row * rowSize;
                cell < row * rowSize + rowSize;
                ++cell
            ) {
                // @checkstyle StringLiteralsConcatenation (3 line)
                result.append(
                    String.format(
                        "%1$" + width + 'd',
                        this.fields[cell].number()
                    )
                ).append('|');
            }
            result.deleteCharAt(result.length() -  1);
            result.append("]\n");
        }
        result.deleteCharAt(result.length() -  1);
        return result.toString();
    }
}
