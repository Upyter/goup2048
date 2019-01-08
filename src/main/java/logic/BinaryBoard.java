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
import java.util.Objects;

/**
 * A 2048 binary board. Uses pre calculated states to determine the result of a
 * move.
 * <p>This class is mutable and not thread-safe.</p>
 * @since 0.9
 */
public class BinaryBoard implements Board {
    private static final long ROW_MASK = 0xFFFFL;
    private static final long COL_MASK = 0x000F000F000F000FL;

    private static final long[] ROW_LEFT_TABLE = new long[65536];
    private static final long[] ROW_RIGHT_TABLE = new long[65536];
    private static final long[] COL_UP_TABLE = new long[65536];
    private static final long[] COL_DOWN_TABLE = new long[65536];
    private static final int[] SCORE_TABLE = new int[65536];

    static {
        for (int row = 0; row < 65536; ++row) {
            // create all possible lines, from 0000 to 15-15-15-15
            // (32k - 32k - 32k - 32k)
            final long line[] = {
                row & 0xfL,
                (row >> 4) & 0xFL,
                (row >> 8) & 0xFL,
                (row >> 12) & 0xFL
            };
            // calculate the score of the current board based on the tiles and
            // all previous tiles necessary to merge
            // up to it
            int score = 0;
            for (final int i = 0; i < 4; ++i) {
                final long rank = line[i];
                if (rank >= 2) {
                    // the score is the total sum of the tile and all
                    // intermediate merged tiles
                    score += (rank - 1) * (1 << rank);
                }
            }
            BinaryBoard.SCORE_TABLE[row] = score;
            // execute a move to the left
            for (int i = 0; i < 3; ++i) {
                int j;
                for (j = i + 1; j < 4; ++j) {
                    if (line[j] != 0) {
                        break;
                    }
                }
                if (j == 4) {
                    break; // no more tiles to the right
                }
                if (line[i] == 0) {
                    line[i] = line[j];
                    line[j] = 0;
                    --i; // retry this entry
                } else if (line[i] == line[j] && line[i] != 0xFL) {
                    ++line[i];
                    line[j] = 0;
                }
            }
            final long result = line[0]
                | (line[1] << 4)
                | (line[2] << 8)
                | (line[3] << 12);
            final long rev_result = BinaryBoard.reverseRow((int) result);
            final long rev_row = BinaryBoard.reverseRow(row);
            BinaryBoard.ROW_LEFT_TABLE[row] = row ^ result;
            BinaryBoard.ROW_RIGHT_TABLE[(int) rev_row] = rev_row ^ rev_result;
            BinaryBoard.COL_UP_TABLE[row] = BinaryBoard.unpackCol(row)
                ^ BinaryBoard.unpackCol(result);
            BinaryBoard.COL_DOWN_TABLE[(int) rev_row] =
                BinaryBoard.unpackCol(rev_row)
                    ^ BinaryBoard.unpackCol(rev_result);
        }
    }

    private long board;

    public BinaryBoard(final long board) {
        this.board = board;
    }

    @Override
    public final void slide(final Move move) {
        final long backup = this.board;
        switch (move) {
            case LEFT:
                this.board = BinaryBoard.executeMoveLeft(backup);
                break;
            case RIGHT:
                this.board = BinaryBoard.executeMoveRight(backup);
                break;
            case UP:
                this.board = BinaryBoard.executeMoveUp(backup);
                break;
            case DOWN: {
                this.board = BinaryBoard.executeMoveDown(backup);
                break;
            }
            default:
                throw new IllegalArgumentException(
                    String.join(
                        "Move: ",
                        Objects.toString(move),
                        " not available."
                    )
                );
        }
    }

    @Override
    public final Collection<Move> possibleMoves() {
        throw new UnsupportedOperationException("#possibleMoves()");
    }

    private static long transpose(final long x) {
        final long a1 = x & 0xF0F00F0FF0F00F0FL;
        final long a2 = x & 0x0000F0F00000F0F0L;
        final long a3 = x & 0x0F0F00000F0F0000L;
        final long a = a1 | (a2 << 12) | (a3 >> 12);
        final long b1 = a & 0xFF00FF0000FF00FFL;
        final long b2 = a & 0x00FF00FF00000000L;
        final long b3 = a & 0x00000000FF00FF00L;
        return b1 | (b2 >> 24) | (b3 << 24);
    }

    public static long executeMoveDown(final long board) {
        long ret = board;
        final long t = transpose(board);
        ret ^= BinaryBoard.COL_UP_TABLE[
            (int) (t & BinaryBoard.ROW_MASK)
            ];
        ret ^= BinaryBoard.COL_UP_TABLE[
            (int) ((t >> 16) & BinaryBoard.ROW_MASK)
            ] <<  4;
        ret ^= BinaryBoard.COL_UP_TABLE[
            (int) ((t >> 32) & BinaryBoard.ROW_MASK)
            ] <<  8;
        ret ^= BinaryBoard.COL_UP_TABLE[
            (int) ((t >> 48) & BinaryBoard.ROW_MASK)
            ] << 12;
        return ret;
    }

    private static long executeMoveUp(final long board) {
        long ret = board;
        final long t = transpose(board);
        ret ^= BinaryBoard.COL_DOWN_TABLE[
            (int) ((t) & BinaryBoard.ROW_MASK)
            ];
        ret ^= BinaryBoard.COL_DOWN_TABLE[
            (int) ((t >> 16) & BinaryBoard.ROW_MASK)
            ] <<  4;
        ret ^= BinaryBoard.COL_DOWN_TABLE[
            (int) ((t >> 32) & BinaryBoard.ROW_MASK)
            ] <<  8;
        ret ^= BinaryBoard.COL_DOWN_TABLE[
            (int) ((t >> 48) & BinaryBoard.ROW_MASK)
            ] << 12;
        return ret;
    }

    private static long executeMoveRight(final long board) {
        long ret = board;
        ret ^= BinaryBoard.ROW_LEFT_TABLE[
            (int) ((board) & BinaryBoard.ROW_MASK)
            ];
        ret ^= BinaryBoard.ROW_LEFT_TABLE[
            (int) ((board >> 16) & BinaryBoard.ROW_MASK)
            ] << 16;
        ret ^= BinaryBoard.ROW_LEFT_TABLE[
            (int) ((board >> 32) & BinaryBoard.ROW_MASK)
            ] << 32;
        ret ^= BinaryBoard.ROW_LEFT_TABLE[
            (int) ((board >> 48) & BinaryBoard.ROW_MASK)
            ] << 48;
        return ret;
    }

    private static long executeMoveLeft(final long board) {
        long ret = board;
        ret ^= BinaryBoard.ROW_RIGHT_TABLE[
            (int) (board & BinaryBoard.ROW_MASK)
            ];
        ret ^= BinaryBoard.ROW_RIGHT_TABLE[
            (int) ((board >> 16) & BinaryBoard.ROW_MASK)
            ] << 16;
        ret ^= BinaryBoard.ROW_RIGHT_TABLE[
            (int) ((board >> 32) & BinaryBoard.ROW_MASK)
            ] << 32;
        ret ^= BinaryBoard.ROW_RIGHT_TABLE[
            (int) ((board >> 48) & BinaryBoard.ROW_MASK)
            ] << 48;
        return ret;
    }

    private static long unpackCol(final long row) {
        return (row | (row << 12L) | (row << 24L) | (row << 36L))
            & BinaryBoard.COL_MASK;
    }

    private static int reverseRow(final int row) {
        return Short.toUnsignedInt(
            (short) ((row >> 12) | ((row >> 4) & 0x00F0)
                | ((row << 4) & 0x0F00) | (row << 12)));
    }
}
