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

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link TurnedBoard}.
 * @since 0.22
 */
public final class TurnedBoardTest {
    /**
     * {@link TurnedBoard#field(int)} must return the first field for index = 0.
     */
    @Test
    public void zeroField() {
        final int num = 31;
        final Board board = new TurnedBoard(
            new SimpleBoard(
                new int[]{
                    num, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                }
            )
        );
        MatcherAssert.assertThat(
            board.field(0).number(),
            Matchers.is(num)
        );
    }

    /**
     * {@link TurnedBoard#field(int)} must return the fifth field for
     * index = 1.
     */
    @Test
    public void oneField() {
        final int num = 43;
        final Board board = new TurnedBoard(
            new SimpleBoard(
                new int[]{
                    0, 0, 0, 0,
                    num, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                }
            )
        );
        final int index = 1;
        MatcherAssert.assertThat(
            board.field(index).number(),
            Matchers.is(num)
        );
    }

    /**
     * {@link TurnedBoard#field(int)} must return the thirteenth field for
     * index = 3.
     */
    @Test
    public void lastFieldFirstLine() {
        final int num = 46;
        final Board board = new TurnedBoard(
            new SimpleBoard(
                new int[]{
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    num, 0, 0, 0,
                }
            )
        );
        final int index = 3;
        MatcherAssert.assertThat(
            board.field(index).number(),
            Matchers.is(num)
        );
    }

    /**
     * {@link TurnedBoard#field(int)} must return the second field for
     * index = 4.
     */
    @Test
    public void firstFieldSecondLine() {
        final int num = 23;
        final Board board = new TurnedBoard(
            new SimpleBoard(
                new int[]{
                    0, num, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                }
            )
        );
        final int index = 4;
        MatcherAssert.assertThat(
            board.field(index).number(),
            Matchers.is(num)
        );
    }

    /**
     * {@link TurnedBoard#field(int)} must return the sixteenth field for
     * index = 15.
     */
    @Test
    public void lastField() {
        final int num = 25;
        final Board board = new TurnedBoard(
            new SimpleBoard(
                new int[]{
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, num,
                }
            )
        );
        final int index = 15;
        MatcherAssert.assertThat(
            board.field(index).number(),
            Matchers.is(num)
        );
    }
}
