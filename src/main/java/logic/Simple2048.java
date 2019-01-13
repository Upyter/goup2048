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
 * A simple 2048 implementation. It's not fast, but aims to be easy to implement
 * and understand.
 * <p>This class is mutable and not thread-safe.</p>
 * @since 0.2
 */
public class Simple2048 implements Game2048<Board> {
    /**
     * The board of this game.
     */
    private final Board board;

    /**
     * Ctor.
     * @param board The board of the game.
     */
    public Simple2048(final Board board) {
        this.board = board;
    }

    @Override
    public final void play(final Move<Board> move) {
        throw new UnsupportedOperationException("To be implemented");
    }

    @Override
    public final Collection<Move<Board>> possibleMoves() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
