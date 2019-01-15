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

/**
 * A board with switched x and y axis. This class is a decorator for
 * {@link Board}.
 * <p>This class is mutable and not thread-safe.</p>
 * @since 0.22
 */
public class TurnedBoard implements Board {
    /**
     * The board to delegate the calls to.
     */
    private final Board board;

    /**
     * Ctor.
     * @param board The board to delegate the calls to.
     */
    public TurnedBoard(final Board board) {
        this.board = board;
    }

    @Override
    public final Field field(final int index) {
        return this.board.field(
            this.rowSize() * (index % this.rowSize()) + index / this.rowSize()
        );
    }

    @Override
    public final Collection<Move<Board>> possibleMoves() {
        return this.board.possibleMoves();
    }

    @Override
    public final int size() {
        return this.board.size();
    }

    @Override
    public final int rowSize() {
        return this.board.rowSize();
    }
}
