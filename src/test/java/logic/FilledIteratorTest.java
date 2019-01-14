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
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * A test for {@link FilledIterator}.
 * @since 0.16
 */
public final class FilledIteratorTest {
    /**
     * {@link FilledIterator#hasNext()} must return false for a board with no
     * fields.
     */
    @Test
    public void noFields() {
        MatcherAssert.assertThat(
            new SimpleBoard(
                new Field[]{}
            ).filled(
                0, (size, index) -> index
            ).hasNext(),
            Matchers.is(false)
        );
    }

    /**
     * {@link FilledIterator#hasNext()} must return false for a board that
     * consists of empty fields.
     */
    @Test
    public void emptyBoard() {
        MatcherAssert.assertThat(
            new SimpleBoard().filled(
                0, (size, index) -> index
            ).hasNext(),
            Matchers.is(false)
        );
    }

    /**
     * {@link FilledIterator#next()} must return all values of the board (in
     * the chosen row) if there are only filled lines.
     */
    @Test
    public void filledBoard() {
        final var first = 5;
        final var second = 2;
        final var third = 6;
        final var fourth = 64;
        final Iterator<Field> iter = new SimpleBoard(
            new SimpleField(first), new SimpleField(second),
            new SimpleField(third), new SimpleField(fourth),
            new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField()
        ).filled(0, (size, index) -> index);
        MatcherAssert.assertThat(iter.next().number(), Matchers.equalTo(first));
        MatcherAssert.assertThat(
            iter.next().number(), Matchers.equalTo(second)
        );
        MatcherAssert.assertThat(iter.next().number(), Matchers.equalTo(third));
        MatcherAssert.assertThat(
            iter.next().number(), Matchers.equalTo(fourth)
        );
    }

    /**
     * Two filled fields next to each other must be returned in this order
     * by {@link FilledIterator#next()}.
     */
    @Test
    public void filledNextToEachOther() {
        final var first = 5;
        final var second = 12;
        final Iterator<Field> iter = new SimpleBoard(
            new SimpleField(first), new SimpleField(second),
            new SimpleField(), new SimpleField()
        ).filled(0, (size, index) -> index);
        MatcherAssert.assertThat(iter.next().number(), Matchers.equalTo(first));
        MatcherAssert.assertThat(
            iter.next().number(),
            Matchers.equalTo(second)
        );
    }

    /**
     * Two filled fields with an empty field in between must be returned without
     * the empty field by {@link FilledIterator#next()}.
     */
    @Test
    public void filledWithOneGapBetween() {
        final var first = 55;
        final var second = 324;
        final Iterator<Field> iter = new SimpleBoard(
            new SimpleField(first), new SimpleField(), new SimpleField(second),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField()
        ).filled(0, (size, index) -> index);
        MatcherAssert.assertThat(iter.next().number(), Matchers.equalTo(first));
        MatcherAssert.assertThat(
            iter.next().number(), Matchers.equalTo(second)
        );
    }

    /**
     * Two filled fields with multiple empty fields in between must be returned
     * without the empty fields by {@link FilledIterator#next()}.
     */
    @Test
    public void filledWithTwoGapBetween() {
        final var first = 543;
        final var second = 56;
        final Iterator<Field> iter = new SimpleBoard(
            new SimpleField(first), new SimpleField(), new SimpleField(),
            new SimpleField(second), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField()
        ).filled(0, (size, index) -> index);
        MatcherAssert.assertThat(iter.next().number(), Matchers.equalTo(first));
        MatcherAssert.assertThat(
            iter.next().number(), Matchers.equalTo(second)
        );
    }

    /**
     * Multiple calls to {@link FilledIterator#hasNext()} mustn't change the
     * behaviour of the iterator.
     */
    @Test
    public void hasNextDoesntMutate() {
        final var first = 6;
        final var second = 8;
        final Iterator<Field> iter = new SimpleBoard(
            new SimpleField(first), new SimpleField(second),
            new SimpleField(), new SimpleField()
        ).filled(0, (size, index) -> index);
        MatcherAssert.assertThat(iter.hasNext(), Matchers.is(true));
        MatcherAssert.assertThat(iter.next().number(), Matchers.equalTo(first));
        MatcherAssert.assertThat(iter.hasNext(), Matchers.is(true));
        MatcherAssert.assertThat(
            iter.next().number(), Matchers.equalTo(second)
        );
        MatcherAssert.assertThat(iter.hasNext(), Matchers.is(false));
    }

    /**
     * {@link FilledIterator#next()} must through an
     * {@link IllegalStateException} if there aren't any filled fields left.
     */
    @Test(expected = NoSuchElementException.class)
    public void nextThrows() {
        new SimpleBoard().filled(
            0, (size, index) -> index
        ).next();
    }

    /**
     * With {@link FilledIterator#FilledIterator(Board, int, BiFunction)} one
     * can produce a reversed iteration.
     */
    @Test
    public void reversed() {
        final var fields = new Field[]{
            new SimpleField(16), new SimpleField(21), new SimpleField(6),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
        };
        final var board = new SimpleBoard(fields);
        final var filled = new FilledIterator(
            board,
            0,
            (size, index) -> size - index - 1
        );
        for (int cell = board.rowSize(); cell <= 0; ++cell) {
            MatcherAssert.assertThat(
                filled.next(),
                Matchers.equalTo(fields[cell])
            );
        }
    }

    /**
     * With {@link FilledIterator#FilledIterator(Board, int, BiFunction)} one
     * can produce an iteration that starts from a row that is not the first.
     */
    @Test
    public void nonFirstRow() {
        final var fields = new Field[]{
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(43), new SimpleField(423), new SimpleField(45),
            new SimpleField(), new SimpleField(), new SimpleField(),
        };
        final var board = new SimpleBoard(fields);
        final var filled = new FilledIterator(
            board,
            1,
            (size, index) -> index
        );
        for (int cell = board.rowSize(); cell < board.rowSize() * 2; ++cell) {
            MatcherAssert.assertThat(
                filled.next(),
                Matchers.equalTo(fields[cell])
            );
        }
    }

    /**
     * {@link FilledIterator#hasNext()} must return true for a board with
     * multiple fields when
     * {@link FilledIterator#FilledIterator(Board, int, BiFunction)} is used
     * to create an iteration that starts from a row that is not the first.
     */
    @Test
    public void nonFirstRowHasNext() {
        final var fields = new Field[]{
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(1), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
        };
        final var board = new SimpleBoard(fields);
        final var filled = new FilledIterator(
            board,
            1,
            (size, index) -> index
        );
        MatcherAssert.assertThat(
            filled.hasNext(),
            Matchers.is(true)
        );
    }

    /**
     * {@link FilledIterator#hasNext()} must return true for a board with only
     * one field when
     * {@link FilledIterator#FilledIterator(Board, int, BiFunction)} is used
     * to create a reversed iteration.
     */
    @Test
    public void hasNextReversedSingleField() {
        MatcherAssert.assertThat(
            new SimpleBoard(
                new SimpleField(4)
            ).filled(
                0, (size, index) -> size - index - 1
            ).hasNext(),
            Matchers.is(true)
        );
    }

    /**
     * {@link FilledIterator#hasNext()} must return false for a board with only
     * one empty field when
     * {@link FilledIterator#FilledIterator(Board, int, BiFunction)} is used
     * to create a reversed iteration.
     */
    @Test
    public void NotHasNextReversedSingleField() {
        MatcherAssert.assertThat(
            new SimpleBoard(
                new SimpleField()
            ).filled(
                0, (size, index) -> size - index - 1
            ).hasNext(),
            Matchers.is(false)
        );
    }

    /**
     * {@link FilledIterator#hasNext()} must return true for a board with
     * multiple fields when
     * {@link FilledIterator#FilledIterator(Board, int, BiFunction)} is used
     * to create a reversed iteration.
     */
    @Test
    public void hasNextReversedMultipleFields() {
        MatcherAssert.assertThat(
            new SimpleBoard(
                new SimpleField(4), new SimpleField(52),
                new SimpleField(), new SimpleField()
            ).filled(
                0, (size, index) -> size - index - 1
            ).hasNext(),
            Matchers.is(true)
        );
    }

    /**
     * With {@link FilledIterator#FilledIterator(Board, int, BiFunction)} one
     * can produce a vertical iteration.
     */
    @Test
    public void vertical() {

    }
}
