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
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

/**
 * An iterator that returns only the filled fields of a board.
 * <p>This class is mutable and not thread-safe.</p>
 * @see Board
 * @since 0.14
 */
public class FilledIterator implements Iterator<Field> {
    /**
     * The board to iterate through.
     */
    private final Board board;

    /**
     * The row to iterate through.
     */
    private final int row;

    /**
     * The function to transform an index to the concrete cell.
     * @checkstyle MemberName (2 lines)
     */
    private final BiFunction<Integer, Integer, Integer> cellFunction;

    /**
     * The cursor of the iteration. It stays one index behind the next element.
     */
    private int cursor;

    /**
     * Ctor.
     * @param board The board to iterate through.
     * @param row The row to iterate through.
     * @param cellFunction The function to transform an index to the concrete
     *  cell. It gets the current index and the size of a row to return the
     *  cell. Example:
     *  <pre>{@code (index, rowSize) -> index; // normal order}</pre>
     *  <pre>{@code (index, rowSize) -> size - 1 - index; // reversed order}
     *  </pre>
     * @checkstyle ParameterNameCheck (5 lines)
     */
    public FilledIterator(
        final Board board,
        final int row,
        final BiFunction<Integer, Integer, Integer> cellFunction
    ) {
        this.board = board;
        this.row = row;
        this.cellFunction = cellFunction;
        this.cursor = -1;
    }

    // @checkstyle ReturnCount (3 lines)
    @SuppressWarnings("PMD.OnlyOneReturn")
    @Override
    public final boolean hasNext() {
        // @checkstyle LocalVariableName (1 line)
        int nextCursor = this.cursor + 1;
        if (0 <= this.row && this.row < this.board.rowSize()) {
            while (nextCursor < this.board.rowSize()) {
                if (
                    Field.isFilled(
                        this.board.get(
                            this.cellFunction.apply(
                                this.board.rowSize(),
                                nextCursor
                            ) + this.row * this.board.rowSize()
                        )
                    )
                ) {
                    return true;
                } else {
                    ++nextCursor;
                }
            }
        }
        return false;
    }

    @Override
    public final Field next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException(
                String.join(
                    "",
                    "There is no filled field left ",
                    "inside the given board. ",
                    this.errorInformation()
                )
            );
        }
        do {
            ++this.cursor;
            final var field = this.board.get(
                this.cellFunction.apply(
                    this.board.rowSize(),
                    this.cursor
                ) + this.row * this.board.rowSize()
            );
            if (Field.isFilled(field)) {
                return field;
            }
        } while (this.cursor < this.board.rowSize());
        throw new IllegalStateException(
            String.join(
                "",
                "Didn't find the next filled field, unexpectedly.",
                this.errorInformation()
            )
        );
    }

    /**
     * Creates a string regarding the iterator for exceptional purposes.
     * @return A string containing the cursor, the transformed cursor, the
     *  board and the row.
     */
    private String errorInformation() {
        return String.join(
            "\nCurrent cursor: ", Integer.toString(this.cursor),
            "\nApplied with given function: ",
            Integer.toString(
                this.cellFunction.apply(this.board.rowSize(), this.cursor)
            ),
            "\nBoard:\n", this.board.toString(),
            "\nRow: ", Integer.toString(this.row)
        );
    }
}
