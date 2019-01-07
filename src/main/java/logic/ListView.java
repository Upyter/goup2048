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

import java.util.List;
import org.cactoos.scalar.StickyScalar;

/**
 * A view on a list, similar to {@link List#subList(int, int)} but as a class.
 * In contrast to the subList method, this class doesn't fail on reversed
 * ranges.
 * <p>This class is mutable and not thread-safe.</p>
 * @since 0.5
 */
public class ListView<T> extends ListEnvelope<T> {

    /**
     * Ctor.
     * @param list The list to create the view for.
     * @param from The start index.
     * @param to The end index,
     */
    public ListView(final List<T> list, final int from, final int to) {
        super(
            new StickyScalar<>(
                () -> {
                    if (to < from) {
                        return list.subList(to, from);
                    } else {
                        return list.subList(from, to);
                    }
                }
            )
        );
    }
}
