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
}
