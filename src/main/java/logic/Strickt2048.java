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
 * A fail fast implementation of the 2048 game. {@link Strickt2048} throws an
 * exception when an illegal move has been played.
 * <p>This class is mutable and not thread-safe.</p>
 * @since 0.2
 */
public class Strickt2048 implements Game2048 {
    /**
     * The map of this game.
     */
    private final Map2048 map;

    /**
     * Ctor.
     * @param map The map of the game.
     */
    public Strickt2048(final Map2048 map) {
        this.map = map;
    }

    @Override
    public final void play(final Move move) {
        if (this.possibleMoves().contains(move)) {
            this.map.slide(move);
        } else {
            throw new IllegalArgumentException(
                String.format(
                    "The chosen  move isn't possible. Map: %s, moves: %s",
                    this.map,
                    this.possibleMoves()
                )
            );
        }
    }

    @Override
    public final Collection<Move> possibleMoves() {
        return this.map.possibleMoves();
    }
}
