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

import java.util.stream.LongStream;
import org.cactoos.scalar.StickyScalar;
import org.cactoos.scalar.UncheckedScalar;

/**
 * The right move on the 2048 game.
 * @since 0.12
 */
public class Right implements Move {
    /**
     * The table that contains all boards to calculate the resulting board.
     */
    private final UncheckedScalar<long[]> table;

    /**
     * Ctor.
     * @checkstyle ExecutableStatementCountCheck (2 lines)
     */
    public Right() {
        this(
            new UncheckedScalar<>(
                new StickyScalar<>(
                    () -> {
                        // @checkstyle LocalFinalVariableName (5 lines)
                        final int linePermutation = 65_536;
                        final long bitsPerTile = 4L;
                        final int tileAmount = 4;
                        final long fullTile = 0xFL;
                        final long emptyTile = 0xFL;
                        final long[] result = new long[linePermutation];
                        for (int row = 0; row < linePermutation; ++row) {
                            final long[] line = new long[tileAmount];
                            for (int tile = 0; tile < tileAmount; ++tile) {
                                line[tile] = (long) row >> bitsPerTile
                                    * (long) tile & fullTile;
                            }
                            // @checkstyle LocalVariableNameCheck (1 line)
                            for (int i = 0; i < tileAmount - 1; ++i) {
                                // @checkstyle LocalVariableNameCheck (1 line)
                                int j;
                                // @checkstyle NestedForDepth (1 line)
                                for (j = i + 1; j < tileAmount; ++j) {
                                    if (line[j] != 0L) {
                                        break;
                                    }
                                }
                                if (j == tileAmount) {
                                    break;
                                }
                                if (line[i] == emptyTile) {
                                    line[i] = line[j];
                                    line[j] = emptyTile;
                                    // @checkstyle LineLengthCheck (1 line)
                                    // @checkstyle ModifiedControlVariable (1 line)
                                    --i;
                                } else if (line[i] == line[j]
                                    && line[i] != fullTile
                                ) {
                                    ++line[i];
                                    line[j] = emptyTile;
                                }
                            }
                            result[row] = LongStream
                                .range(0L, bitsPerTile)
                                .reduce(
                                    (accu, index) -> accu | line[(int) index]
                                        << bitsPerTile * index)
                                .getAsLong();
                        }
                        return result;
                    }
                )
            )
        );
    }

    /**
     * Ctor.
     * @param table The table containing the pre calculated board transitions.
     */
    private Right(final UncheckedScalar<long[]> table) {
        this.table = table;
    }

    @Override
    public final long slided(final long board) {
        // @checkstyle LocalFinalVariableName (2 lines)
        final long boardBitAmount = 16L;
        final long tileAmount = 4L;
        final long mask = 0xFFFFL;
        long ret = board;
        // @checkstyle LocalVariableName (1 line)
        for (long i = 0L; i < tileAmount; ++i) {
            ret ^= this.table.value()[
                (int) (board >> boardBitAmount * i & mask)
                ] << boardBitAmount * i;
        }
        return ret;
    }
}
