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
 * Tests for {@link Right}.
 * @since 0.14
 */
@SuppressWarnings("PMD.TooManyMethods")
public final class RightTest {
    /**
     * {@link Right#merge(Board)} with an empty board must be a no-op.
     */
    @Test
    public void emptyLineMerge() {
        final Board board = new SimpleBoard();
        new Right().merge(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard()
            )
        );
    }

    /**
     * {@link Right#merge(Board)} with one filled field must be a no-op.
     */
    @Test
    public void oneFilledMerge() {
        final var num = 2;
        final Board board = new SimpleBoard(
            new SimpleField(num), new SimpleField(),
            new SimpleField(), new SimpleField()
        );
        new Right().merge(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard(
                    new SimpleField(num), new SimpleField(),
                    new SimpleField(), new SimpleField()
                )
            )
        );
    }

    /**
     * {@link Right#merge(Board)} with multiple filled but non mergeable fields
     * must be a no-op.
     */
    @Test
    public void multipleNonMergeable() {
        final var num = 2;
        final var other = 5;
        final Board board = new SimpleBoard(
            new SimpleField(num), new SimpleField(other),
            new SimpleField(), new SimpleField()
        );
        new Right().merge(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard(
                    new SimpleField(num), new SimpleField(other),
                    new SimpleField(), new SimpleField()
                )
            )
        );
    }

    /**
     * {@link Right#merge(Board)} with two mergeable fields next to each other
     * must result in a correct merge.
     */
    @Test
    public void twoFieldsNeighboursMerge() {
        final var num = 2;
        final Board board = new SimpleBoard(
            new SimpleField(num), new SimpleField(num),
            new SimpleField(), new SimpleField()
        );
        new Right().merge(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard(
                    new SimpleField(), new SimpleField(num * 2),
                    new SimpleField(), new SimpleField()
                )
            )
        );
    }

    /**
     * {@link Right#merge(Board)} with two mergeable fields with an empty field
     * in between must result in a correct merge.
     */
    @Test
    public void twoFieldsGabMerge() {
        final var num = 6;
        final Board board = new SimpleBoard(
            new SimpleField(num), new SimpleField(), new SimpleField(num),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField()
        );
        new Right().merge(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard(
                    new SimpleField(), new SimpleField(),
                    new SimpleField(num * 2),
                    new SimpleField(), new SimpleField(), new SimpleField(),
                    new SimpleField(), new SimpleField(), new SimpleField()
                )
            )
        );
    }

    /**
     * {@link Right#merge(Board)} with two mergeable fields that have a non
     * empty field in between must be a no-op.
     */
    @Test
    public void twoFieldsBreakerMerge() {
        final var num = 4;
        final var other = 7;
        final Board board = new SimpleBoard(
            new SimpleField(num), new SimpleField(other), new SimpleField(num),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField()
        );
        new Right().merge(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard(
                    new SimpleField(num), new SimpleField(other),
                    new SimpleField(num),
                    new SimpleField(), new SimpleField(), new SimpleField(),
                    new SimpleField(), new SimpleField(), new SimpleField()
                )
            )
        );
    }

    /**
     * {@link Right#merge(Board)} with two mergeable fields that are next to
     * each other but on different lines must be a no-op.
     */
    @Test
    public void twoMergeableFieldsDistinctRows() {
        final var num = 3;
        final Board board = new SimpleBoard(
            new SimpleField(), new SimpleField(), new SimpleField(num),
            new SimpleField(num), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField()
        );
        new Right().merge(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard(
                    new SimpleField(), new SimpleField(), new SimpleField(num),
                    new SimpleField(num), new SimpleField(), new SimpleField(),
                    new SimpleField(), new SimpleField(), new SimpleField()
                )
            )
        );
    }

    /**
     * {@link Right#merge(Board)} with three mergeable fields next to each other
     * must result in two fields merged (the fields on the right side) and
     * leaving the left like it is.
     */
    @Test
    public void threeFieldsMerge() {
        final var num = 3;
        final Board board = new SimpleBoard(
            new SimpleField(num), new SimpleField(num), new SimpleField(num),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField()
        );
        new Right().merge(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard(
                    new SimpleField(num), new SimpleField(),
                    new SimpleField(num * 2),
                    new SimpleField(), new SimpleField(),
                    new SimpleField(),
                    new SimpleField(), new SimpleField(),
                    new SimpleField()
                )
            )
        );
    }

    /**
     * {@link Right#merge(Board)} with multiple mergeable lines must be merged
     * correctly.
     */
    @Test
    public void multipleRowMerge() {
        final var num = 3;
        final Board board = new SimpleBoard(
            new SimpleField(num), new SimpleField(num), new SimpleField(),
            new SimpleField(), new SimpleField(num), new SimpleField(num),
            new SimpleField(num), new SimpleField(num), new SimpleField(num)
        );
        new Right().merge(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard(
                    new SimpleField(), new SimpleField(num * 2),
                    new SimpleField(),
                    new SimpleField(), new SimpleField(),
                    new SimpleField(num * 2),
                    new SimpleField(num), new SimpleField(),
                    new SimpleField(num * 2)
                )
            )
        );
    }

    /**
     * {@link Right#push(Board)} on an empty line must be a no-op.
     */
    @Test
    public void emptyLinePush() {
        final Board board = new SimpleBoard();
        new Right().push(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard()
            )
        );
    }

    /**
     * {@link Right#push(Board)} on a board with one filled field must push it
     * correctly to the right side.
     */
    @Test
    public void oneFilledPush() {
        final int num = 4;
        final Board board = new SimpleBoard(
            new SimpleField(num), new SimpleField(),
            new SimpleField(), new SimpleField()
        );
        new Right().push(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard(
                    new SimpleField(), new SimpleField(num),
                    new SimpleField(), new SimpleField()
                )
            )
        );
    }

    /**
     * {@link Right#push(Board)} on a board with one field that has a distance
     * of multiple fields to the right side. This field must be pushed these
     * multiple fields.
     */
    @Test
    public void oneMultiCellPush() {
        final int num = 4;
        final Board board = new SimpleBoard(
            new SimpleField(num), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField()
        );
        new Right().push(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard(
                    new SimpleField(), new SimpleField(), new SimpleField(num),
                    new SimpleField(), new SimpleField(), new SimpleField(),
                    new SimpleField(), new SimpleField(), new SimpleField()
                )
            )
        );
    }

    /**
     * {@link Right#push(Board)} on a board with two filled fields next to each
     * other (without a gap) must push both of them to the right side.
     */
    @Test
    public void twoPushed() {
        final int num = 4;
        final int other = 23;
        final Board board = new SimpleBoard(
            new SimpleField(num), new SimpleField(other), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField()
        );
        new Right().push(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard(
                    new SimpleField(), new SimpleField(num),
                    new SimpleField(other),
                    new SimpleField(), new SimpleField(), new SimpleField(),
                    new SimpleField(), new SimpleField(), new SimpleField()
                )
            )
        );
    }

    /**
     * {@link Right#push(Board)} on a board with two filled fields where one is
     * already at the right side and one at the left side. The left one must be
     * pushed.
     */
    @Test
    public void oneToPushOneToStay() {
        final int num = 534;
        final int other = 41;
        final Board board = new SimpleBoard(
            new SimpleField(num), new SimpleField(), new SimpleField(),
            new SimpleField(other),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField()
        );
        new Right().push(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard(
                    new SimpleField(), new SimpleField(), new SimpleField(num),
                    new SimpleField(other),
                    new SimpleField(), new SimpleField(), new SimpleField(),
                    new SimpleField(),
                    new SimpleField(), new SimpleField(), new SimpleField(),
                    new SimpleField(),
                    new SimpleField(), new SimpleField(), new SimpleField(),
                    new SimpleField()
                )
            )
        );
    }

    /**
     * {@link Right#push(Board)} on a board with two filled fields with a gap in
     * between must push them to the right side. The gap must disappear.
     */
    @Test
    public void twoWithGapPushed() {
        final int num = 12;
        final int other = 75;
        final Board board = new SimpleBoard(
            new SimpleField(num), new SimpleField(), new SimpleField(other),
            new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField()
        );
        new Right().push(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard(
                    new SimpleField(), new SimpleField(), new SimpleField(num),
                    new SimpleField(other),
                    new SimpleField(), new SimpleField(), new SimpleField(),
                    new SimpleField(),
                    new SimpleField(), new SimpleField(), new SimpleField(),
                    new SimpleField(),
                    new SimpleField(), new SimpleField(), new SimpleField(),
                    new SimpleField()
                )
            )
        );
    }

    /**
     * {@link Right#push(Board)} on a board wtih three filled fields, where two
     * of them have a gap in between, must push the to the right side, removing
     * the gap.
     */
    @Test
    public void threeWithGapPushed() {
        final int one = 12;
        final int two = 75;
        final int three = 75;
        final Board board = new SimpleBoard(
            new SimpleField(one), new SimpleField(two), new SimpleField(),
            new SimpleField(three),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField()
        );
        new Right().push(board);
        MatcherAssert.assertThat(
            board,
            Matchers.equalTo(
                new SimpleBoard(
                    new SimpleField(), new SimpleField(one),
                    new SimpleField(two), new SimpleField(three),
                    new SimpleField(), new SimpleField(), new SimpleField(),
                    new SimpleField(),
                    new SimpleField(), new SimpleField(), new SimpleField(),
                    new SimpleField(),
                    new SimpleField(), new SimpleField(), new SimpleField(),
                    new SimpleField()
                )
            )
        );
    }
}
