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
import java.util.List;
import java.util.ListIterator;
import org.cactoos.Scalar;
import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.scalar.UncheckedScalar;

/**
 * An envelope for {@link List}.
 * <p>This class is mutable and not thread-safe.</p>
 * @since 0.5
 */
public class ListEnvelope<T> extends CollectionEnvelope<T> implements List<T> {
    /**
     * The list for the delegation.
     */
    private final UncheckedScalar<List<T>> list;

    /**
     * Ctor.
     * @param list The list for the delegation
     */
    public ListEnvelope(final Scalar<List<T>> list) {
        super(list::value);
        this.list = new UncheckedScalar<>(list);
    }

    @Override
    public final boolean addAll(final int index,
        final Collection<? extends T> items) {
        throw new UnsupportedOperationException("#addAll()");
    }

    @Override
    public final T get(final int index) {
        return this.list.value().get(index);
    }

    @Override
    public final T set(final int index, final T element) {
        throw new UnsupportedOperationException("#set()");
    }

    @Override
    public final void add(final int index, final T element) {
        throw new UnsupportedOperationException("#add()");
    }

    @Override
    public final T remove(final int index) {
        throw new UnsupportedOperationException("#remove()");
    }

    @Override
    public final int indexOf(final Object item) {
        return this.list.value().indexOf(item);
    }

    @Override
    public final int lastIndexOf(final Object item) {
        return this.list.value().lastIndexOf(item);
    }

    @Override
    public final ListIterator<T> listIterator() {
        return new org.cactoos.list.ListIterator<>(
            this.list.value().listIterator()
        );
    }

    @Override
    public final ListIterator<T> listIterator(final int index) {
        return new org.cactoos.list.ListIterator<>(
            this.list.value().listIterator(index)
        );
    }

    @Override
    public final List<T> subList(final int start, final int end) {
        return this.list.value().subList(start, end);
    }

    @Override
    public final String toString() {
        return this.list.value().toString();
    }
}
