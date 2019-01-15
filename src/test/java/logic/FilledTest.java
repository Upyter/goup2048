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
 * A test for {@link Filled}.
 * @since 0.16
 */
@SuppressWarnings("PMD.TooManyMethods")
public final class FilledTest {
    /**
     * {@link Filled#hasNext()} must return false for a board with no
     * fields.
     */
    @Test
    public void noFields() {
        MatcherAssert.assertThat(
            new Filled(
                new SimpleBoard(new Field[]{}),
                (size, index) -> index
            ).hasNext(),
            Matchers.is(false)
        );
    }

    /**
     * {@link Filled#hasNext()} must return false for a board that
     * consists of empty fields.
     */
    @Test
    public void emptyBoard() {
        MatcherAssert.assertThat(
            new Filled(
                new SimpleBoard(),
                (size, index) -> index
            ).hasNext(),
            Matchers.is(false)
        );
    }

    /**
     * {@link Filled#next()} must return all values of the board (in
     * the chosen row) if there are only filled lines.
     */
    @Test
    public void filledBoard() {
        final var first = 5;
        final var second = 2;
        final var third = 6;
        final var fourth = 64;
        final Iterator<Field> iter = new Filled(
            new SimpleBoard(
                new SimpleField(first), new SimpleField(second),
                new SimpleField(third), new SimpleField(fourth),
                new SimpleField(), new SimpleField(),
                new SimpleField(), new SimpleField(),
                new SimpleField(), new SimpleField(),
                new SimpleField(), new SimpleField(),
                new SimpleField(), new SimpleField(),
                new SimpleField(), new SimpleField()
            ),
            (size, index) -> index
        );
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
     * by {@link Filled#next()}.
     */
    @Test
    public void filledNextToEachOther() {
        final var first = 5;
        final var second = 12;
        final Iterator<Field> iter = new Filled(
            new SimpleBoard(
                new SimpleField(first), new SimpleField(second),
                new SimpleField(), new SimpleField()
            ),
            (size, index) -> index
        );
        MatcherAssert.assertThat(iter.next().number(), Matchers.equalTo(first));
        MatcherAssert.assertThat(
            iter.next().number(),
            Matchers.equalTo(second)
        );
    }

    /**
     * Two filled fields with an empty field in between must be returned without
     * the empty field by {@link Filled#next()}.
     */
    @Test
    public void filledWithOneGapBetween() {
        final var first = 55;
        final var second = 324;
        final Iterator<Field> iter = new Filled(
            new SimpleBoard(
                new SimpleField(first), new SimpleField(),
                new SimpleField(second),
                new SimpleField(), new SimpleField(), new SimpleField(),
                new SimpleField(), new SimpleField(), new SimpleField()
            ),
            (size, index) -> index
        );
        MatcherAssert.assertThat(iter.next().number(), Matchers.equalTo(first));
        MatcherAssert.assertThat(
            iter.next().number(), Matchers.equalTo(second)
        );
    }

    /**
     * Two filled fields with multiple empty fields in between must be returned
     * without the empty fields by {@link Filled#next()}.
     */
    @Test
    public void filledWithTwoGapBetween() {
        final var first = 543;
        final var second = 56;
        final Iterator<Field> iter = new Filled(
            new SimpleBoard(
                new SimpleField(first), new SimpleField(), new SimpleField(),
                new SimpleField(second), new SimpleField(), new SimpleField(),
                new SimpleField(), new SimpleField(), new SimpleField(),
                new SimpleField(), new SimpleField(), new SimpleField(),
                new SimpleField(), new SimpleField(), new SimpleField(),
                new SimpleField()
            ),
            (size, index) -> index
        );
        MatcherAssert.assertThat(iter.next().number(), Matchers.equalTo(first));
        MatcherAssert.assertThat(
            iter.next().number(), Matchers.equalTo(second)
        );
    }

    /**
     * Multiple calls to {@link Filled#hasNext()} mustn't change the
     * behaviour of the iterator.
     */
    @Test
    public void hasNextDoesntMutate() {
        final var first = 6;
        final var second = 8;
        final Iterator<Field> iter = new Filled(
            new SimpleBoard(
                new SimpleField(first), new SimpleField(second),
                new SimpleField(), new SimpleField()
            ),
            (size, index) -> index
        );
        MatcherAssert.assertThat(iter.hasNext(), Matchers.is(true));
        MatcherAssert.assertThat(iter.next().number(), Matchers.equalTo(first));
        MatcherAssert.assertThat(iter.hasNext(), Matchers.is(true));
        MatcherAssert.assertThat(
            iter.next().number(), Matchers.equalTo(second)
        );
        MatcherAssert.assertThat(iter.hasNext(), Matchers.is(false));
    }

    /**
     * {@link Filled#next()} must through an
     * {@link IllegalStateException} if there aren't any filled fields left.
     */
    @Test(expected = NoSuchElementException.class)
    public void nextThrows() {
        new Filled(
            new SimpleBoard(), (size, index) -> index
        ).next();
    }

    /**
     * With {@link Filled#Filled(Board, int, BiFunction)} one
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
        final var filled = new Filled(
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
     * With {@link Filled#Filled(Board, int, BiFunction)} one
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
        final var filled = new Filled(
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
     * {@link Filled#hasNext()} must return true for a board with
     * multiple fields when
     * {@link Filled#Filled(Board, int, BiFunction)} is used
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
        final var filled = new Filled(
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
     * {@link Filled#hasNext()} must return true for a board with only
     * one field when
     * {@link Filled#Filled(Board, int, BiFunction)} is used
     * to create a reversed iteration.
     */
    @Test
    public void hasNextReversedSingleField() {
        final var num = 4;
        MatcherAssert.assertThat(
            new Filled(
                new SimpleBoard(new SimpleField(num)),
                (size, index) -> size - index - 1
            ).hasNext(),
            Matchers.is(true)
        );
    }

    /**
     * {@link Filled#hasNext()} must return false for a board with only
     * one empty field when
     * {@link Filled#Filled(Board, int, BiFunction)} is used
     * to create a reversed iteration.
     */
    @Test
    public void notHasNextReversedSingleField() {
        MatcherAssert.assertThat(
            new Filled(
                new SimpleBoard(new SimpleField()),
                (size, index) -> size - index - 1
            ).hasNext(),
            Matchers.is(false)
        );
    }

    /**
     * {@link Filled#hasNext()} must return true for a board with
     * multiple fields when
     * {@link Filled#Filled(Board, int, BiFunction)} is used
     * to create a reversed iteration.
     */
    @Test
    public void hasNextReversedMultipleFields() {
        final var first = 4;
        final var second = 52;
        MatcherAssert.assertThat(
            new Filled(
                new SimpleBoard(
                    new SimpleField(first), new SimpleField(second),
                    new SimpleField(), new SimpleField()
                ),
                (size, index) -> size - index - 1
            ).hasNext(),
            Matchers.is(true)
        );
    }

    /**
     * {@link Filled#hasNext()} mustn't throw exceptions, even if
     * {@link Filled#Filled(Board, int, BiFunction)} is used
     * to create an iteration through an unavailable row.
     */
    @Test
    public void hasNextInvalidRow() {
        MatcherAssert.assertThat(
            new Filled(
                new SimpleBoard(
                    new SimpleField(), new SimpleField(),
                    new SimpleField(), new SimpleField()
                ),
                2,
                (index, size) -> size
            ).hasNext(),
            Matchers.is(false)
        );
    }

    /**
     * {@link Filled#hasNext()} mustn't throw exceptions, even if
     * {@link Filled#Filled(Board, int, BiFunction)} is used
     * to create an iteration through unavailable indices.
     */
    @Test
    public void hasNextInvalidIndex() {
        final var fields = new Field[]{
            new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(),
        };
        MatcherAssert.assertThat(
            new Filled(
                new SimpleBoard(fields),
                (index, size) -> fields.length + 1
            ).hasNext(),
            Matchers.is(false)
        );
    }

    /**
     * {@link Filled#hasNext()} must return true even if the next filled
     * field has some empty fields before it.
     */
    @Test
    public void hasNextWithRange() {
        final int num = 34;
        final var fields = new Field[]{
            new SimpleField(), new SimpleField(), new SimpleField(num),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
        };
        MatcherAssert.assertThat(
            new Filled(
                new SimpleBoard(fields),
                (size, index) -> index
            ).hasNext(),
            Matchers.is(true)
        );
    }

    /**
     * {@link Filled#hasNext()} must return true even if it starts from
     * the middle of the board by using
     * {@link Filled#Filled(Board, int, BiFunction)}.
     */
    @Test
    public void hasNextStartedInBetween() {
        final int num = 132;
        final var fields = new Field[]{
            new SimpleField(), new SimpleField(), new SimpleField(num),
            new SimpleField(), new SimpleField(), new SimpleField(),
            new SimpleField(), new SimpleField(), new SimpleField(),
        };
        MatcherAssert.assertThat(
            new Filled(
                new SimpleBoard(fields),
                (size, index) -> index + 1
            ).hasNext(),
            Matchers.is(true)
        );
    }

    /**
     * With {@link Filled#Filled(Board, int, BiFunction)} one
     * can produce a vertical iteration.
     */
    @Test
    public void vertical() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
