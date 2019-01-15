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
 * The board of the 2048 game. The size of it depends on the concrete
 * implementation, but it has to be quadratic.
 * @since 0.2
 */
public interface Board {
    /**
     * Returns the value at the given position.
     * @param index The index of the value.
     * @return The value at the index.
     */
    Field field(int index);

    /**
     * Returns the moves that are possible on the current map.
     * @return The moves currently possible.
     */
    Collection<Move<Board>> possibleMoves();

    /**
     * Returns the amount of the fields that this board has.
     * @return The size of this board.
     */
    int size();

    /**
     * Returns the size of a row.
     * @return The size of a row.
     */
    int rowSize();
}
