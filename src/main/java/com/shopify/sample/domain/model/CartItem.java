/*
 *   The MIT License (MIT)
 *
 *   Copyright (c) 2015 Shopify Inc.
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */

package com.shopify.sample.domain.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.List;

import static com.shopify.sample.util.Util.checkNotEmpty;
import static com.shopify.sample.util.Util.checkNotNull;
import static java.util.Collections.unmodifiableList;

public final class CartItem {
  @NonNull public final String productId;
  @NonNull public final String productVariantId;
  @NonNull public final String productTitle;
  @NonNull public final String variantTitle;
  @NonNull public final BigDecimal price;
  @NonNull public final List<Option> options;
  @Nullable public final String image;
  public final int quantity;

  public CartItem(@NonNull final String productId, @NonNull final String productVariantId, @NonNull final String productTitle,
    @NonNull final String variantTitle, @NonNull final BigDecimal price, @NonNull final List<Option> options,
    @Nullable final String image) {
    this(productId, productVariantId, productTitle, variantTitle, price, options, image, 1);
  }

  private CartItem(@NonNull final String productId, @NonNull final String productVariantId, @NonNull final String productTitle,
    @NonNull final String variantTitle, @NonNull final BigDecimal price, @NonNull final List<Option> options,
    @Nullable final String image, final int quantity) {
    this.productId = checkNotNull(productId, "productId == null");
    this.productVariantId = checkNotNull(productVariantId, "productVariantId == null");
    this.productTitle = checkNotNull(productTitle, "productTitle == null");
    this.variantTitle = checkNotNull(variantTitle, "variantTitle == null");
    this.price = checkNotNull(price, "price == null");
    this.options = unmodifiableList(checkNotEmpty(options, "options is empty"));
    this.image = image;
    this.quantity = Math.max(0, quantity);
  }

  public CartItem incrementQuantity(final int by) {
    return new CartItem(productId, productVariantId, productTitle, variantTitle, price, options, image, Math.max(0, quantity + by));
  }

  public CartItem decrementQuantity(final int by) {
    return new CartItem(productId, productVariantId, productTitle, variantTitle, price, options, image, Math.max(0, quantity - by));
  }

  @Override public String toString() {
    return "CartItem{" +
      "productId='" + productId + '\'' +
      ", productVariantId='" + productVariantId + '\'' +
      ", productTitle='" + productTitle + '\'' +
      ", variantTitle='" + variantTitle + '\'' +
      ", price=" + price +
      ", options=" + options +
      ", image='" + image + '\'' +
      ", quantity=" + quantity +
      '}';
  }

  @Override public boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof CartItem)) return false;

    final CartItem cartItem = (CartItem) o;

    if (!productId.equals(cartItem.productId)) return false;
    return productVariantId.equals(cartItem.productVariantId);
  }

  @Override public int hashCode() {
    int result = productId.hashCode();
    result = 31 * result + productVariantId.hashCode();
    return result;
  }

  public boolean equalsById(final CartItem other) {
    return equals(other);
  }

  public boolean equalsByContent(final CartItem other) {
    return quantity == other.quantity
      && productTitle.equals(other.productTitle)
      && variantTitle.equals(other.variantTitle)
      && price.equals(other.price)
      && options.equals(other.options)
      && (image != null ? image.equals(other.image) : other.image == null);
  }

  public static final class Option {
    @NonNull public final String name;
    @NonNull public final String value;

    public Option(@NonNull final String name, @NonNull final String value) {
      this.name = checkNotNull(name, "name == null");
      this.value = checkNotNull(value, "value == null");
    }

    @Override public String toString() {
      return "SelectedOption{" +
        "name='" + name + '\'' +
        ", value='" + value + '\'' +
        '}';
    }

    @Override public boolean equals(final Object o) {
      if (this == o) return true;
      if (!(o instanceof Option)) return false;

      final Option option = (Option) o;

      if (!name.equals(option.name)) return false;
      return value.equals(option.value);

    }

    @Override public int hashCode() {
      int result = name.hashCode();
      result = 31 * result + value.hashCode();
      return result;
    }
  }
}
