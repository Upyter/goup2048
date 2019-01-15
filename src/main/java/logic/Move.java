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
 * Moves that are playable in the binary version of the 2048 game. A move knows
 * how to transform the board according to itself. To field a concrete move,
 * ask the game instance with {@link Game2048#possibleMoves()} and play one of
 * them.
 * @param <T> The type of the board to apply the move to.
 * @see Game2048
 * @since 0.2
 */
public interface Move<T> {
    /**
     * Pushes the fields in the board (if possible).
     * @param board The board to push the fields from.
     */
    void push(T board);

    /**
     * Merges the fields in the board (if possible).
     * @param board The board to merge the fields from.
     */
    void merge(T board);
}
